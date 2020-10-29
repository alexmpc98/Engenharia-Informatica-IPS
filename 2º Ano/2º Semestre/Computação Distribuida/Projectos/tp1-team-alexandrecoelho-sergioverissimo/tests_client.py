"""
 JSON-RPC Tests

"""

import json
import socket
import threading
import unittest
import concurrent.futures

from client import JSONRPCClient


# Define server host and port
SERVER_HOST = '127.0.0.1'
SERVER_PORT = 8000


class TestBase(unittest.TestCase):
    """Base for all tests."""

    def setUp(self):
        # Initiate the mock server socket
        self.sock = socket.socket()
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.sock.bind((SERVER_HOST, SERVER_PORT))
        self.sock.listen(1)

        # Creates the client
        self.client = JSONRPCClient(SERVER_HOST, SERVER_PORT)

        # Receives the client connection
        self.conn, _ = self.sock.accept()

    def tearDown(self):
        self.conn.close()
        self.sock.close()
        self.client.close()

    def send(self, msg):
        """Sends a message to the socket"""
        self.conn.sendall(msg.encode())

    def recv(self):
        """Receives a message from a socket."""
        return self.conn.recv(1024).decode()

    def send_json(self, payload):
        """Sends json message to the socket."""
        msg = json.dumps(payload)
        self.send(msg)

    def recv_json(self):
        """Receives a json message from the socket."""
        msg = self.recv()
        return json.loads(msg)

    def jsonrpc_res(self, rpcid, result):
        """Sends a JSON-RPC response."""
        self.send_json({
            'id': rpcid,
            'jsonrpc': '2.0',
            'result': result
        })


class TestProtocol(TestBase):
    """Tests JSON-RPC Protocol."""

    def testVersion(self):
        """JSON-RPC clients must send the protocol version."""
        thread = threading.Thread(target=self.client.hello)
        thread.start()

        req = self.recv_json()
        self.jsonrpc_res(1, result='Ok')
        self.assertIn('jsonrpc', req)

        thread.join()

    def testID(self):
        """JSON-RPC clients must send the ID."""
        thread = threading.Thread(target=self.client.hello)
        thread.start()

        req = self.recv_json()
        self.jsonrpc_res(1, result='Ok')
        self.assertIn('id', req)

        thread.join()

    def testNoReuseID(self):
        """
        JSON-RPC clients must not reuse the same ID on
        subsequent invocations.
        """
        # First invocation
        thread = threading.Thread(target=self.client.hello)
        thread.start()

        req = self.recv_json()
        first_id = req['id']
        self.jsonrpc_res(first_id, result='Ok')

        thread.join()

        # Second invocation
        thread = threading.Thread(target=self.client.hello)
        thread.start()

        req = self.recv_json()
        self.jsonrpc_res(req['id'], result='Ok')
        self.assertNotEqual(first_id, req['id'])

        thread.join()

    def testMethod(self):
        """JSON-RPC clients must send the method."""
        thread = threading.Thread(target=self.client.hello)
        thread.start()

        req = self.recv_json()
        self.jsonrpc_res(1, result='Ok')
        self.assertIn('method', req)

        thread.join()

    def testParams(self):
        """JSON-RPC clients must send parameters."""
        thread = threading.Thread(target=self.client.hello, args=(1, 2))
        thread.start()

        req = self.recv_json()
        self.jsonrpc_res(1, result='Ok')
        self.assertIn('params', req)
        self.assertEqual(req['params'], [1, 2])

        thread.join()


class TestResults(TestBase):
    """Tests JSON-RPC results."""

    def testResults(self):
        """JSON-RPC Client should return the result."""
        with concurrent.futures.ThreadPoolExecutor() as executor:
            future = executor.submit(self.client.hello)

            self.recv_json()
            self.jsonrpc_res(1, result='Ok')

            self.assertEqual(future.result(), 'Ok')


class TestErrors(TestBase):
    """Tests JSON-RPC errors."""

    def testMethodNotFound(self):
        """Client should throw AttributeError"""

        with concurrent.futures.ThreadPoolExecutor() as executor:
            future = executor.submit(self.client.hello)

            self.recv_json()
            self.send_json({
                'id': None,
                'jsonrpc': '2.0',
                'error': {
                    'code': -32601,
                    'message': 'Method not found'
                }
            })

            self.assertRaises(AttributeError, future.result)

    def testInvalidParams(self):
        """Client should throw TypeError"""

        with concurrent.futures.ThreadPoolExecutor() as executor:
            future = executor.submit(self.client.hello)

            self.recv_json()
            self.send_json({
                'id': None,
                'jsonrpc': '2.0',
                'error': {
                    'code': -32602,
                    'message': 'Invalid params'
                }
            })

            self.assertRaises(TypeError, future.result)
