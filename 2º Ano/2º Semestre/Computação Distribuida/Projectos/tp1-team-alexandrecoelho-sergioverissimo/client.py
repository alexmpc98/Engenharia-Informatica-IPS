"""
 Simple JSON-RPC Client

"""

import json
import socket
from shared_memory import SharedMemory


class JSONRPCClient:
    """The JSON-RPC client."""

    def __init__(self, host, port):
        self.sock = socket.socket()
        self.sock.connect((host, port))
        self.ID = 0
        self.shared_memory = SharedMemory()

    def close(self):
        """Closes the connection."""
        self.sock.close()

    def send(self, msg):
        """Sends a message to the server."""
        self.sock.sendall(msg.encode())
        return self.sock.recv(1024).decode()

    def invoke(self, method, *params):
        """Invokes a remote function."""
        self.shared_memory.increment_id()
        req = {
            'id': self.shared_memory.get_id(),
            'jsonrpc': '2.0',
            'method': method
        }
        if len(params) > 0:
            req['params'] = params
        req_json = json.dumps(req)
        msg = self.send(req_json)
        resp = json.loads(msg)
        switcher = {
            -32601: AttributeError('Method not found'),
            -32602: TypeError('Invalid params')
        }
        if 'error' not in msg:
            resp = resp['result']
        else:
            raise switcher.get(resp['error']['code'])
        return resp

    def __getattr__(self, name):
        """Invokes a generic function."""
        def inner(*params):
            return self.invoke(name, *params)
        return inner


if __name__ == "__main__":
    # Test the JSONRPCClient class
    client = JSONRPCClient('127.0.0.1', 8000)
    res = client.mul(2, 3)
    client.close()
