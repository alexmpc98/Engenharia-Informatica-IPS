"""
 Simple JSON-RPC Server

"""

import json
import socket
import functions


class JSONRPCServer:
    """The JSON-RPC server."""

    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.sock = None
        self.funcs = {}

    def register(self, name, function):
        """Registers a function."""
        self.funcs[name] = function

    def start(self):
        """Starts the server."""
        self.sock = socket.socket()
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.sock.bind((self.host, self.port))
        self.sock.listen(1)
        print('Listening on port %s ...' % self.port)

        try:
            while True:
                # Accepts and handles client
                conn, _ = self.sock.accept()
                self.handle_client(conn)

                # Close client connection
                conn.close()

        except ConnectionAbortedError:
            pass
        except OSError:
            pass

    def stop(self):
        """Stops the server."""
        self.sock.close()

    def handle_client(self, conn):
        """Handles the client connection."""

        res_json = {
            'jsonrpc': '2.0'
        }

        # Receive message
        msg = conn.recv(1024).decode()
        print('Received:', msg)

        error_switch = {
            1: {'code': -32600, 'message': 'Invalid Request'},
            2: {'code': -32601, 'message': 'Method not found'},
            3: {'code': -32602, 'message': 'Invalid params'},
            4: {'code': -32700, 'message': 'Parse error'}
        }

        # *** PARSE ERROR *** #
        try:
            msg = json.loads(msg)

            if 'id' in msg.keys() or 'error' in msg.keys():
                # *** INVALID REQUEST *** #
                try:
                    res_json['id'] = msg['id']
                    if 'jsonrpc' not in msg.keys():
                        raise KeyError
                    method = msg['method']

                    # *** METHOD NOT FOUND *** #
                    try:
                        func = self.funcs[method]
                        if 'params' not in msg.keys():
                            try:
                                res_json['result'] = str(func())
                            except TypeError:
                                res_json['error'] = error_switch.get(3)
                        else:
                            # *** INVALID PARAMS *** #
                            params = msg['params']
                            if len(params) > 0:
                                try:
                                    res_json['result'] = func(*params)
                                except KeyError:
                                    res_json['error'] = error_switch.get(3)
                            else:
                                # *** INVALID PARAMS *** #
                                try:
                                    res_json['result'] = func()
                                except TypeError:
                                    res_json['error'] = error_switch.get(3)
                    except KeyError:
                        res_json['error'] = error_switch.get(2)
                except (KeyError, TypeError):
                    res_json['error'] = error_switch.get(1)
                conn.send(json.dumps(res_json).encode())
                conn.close()
            else:
                # *** NOTIFICATION *** #
                try:
                    method = msg['method']
                    func = self.funcs[method]
                except (KeyError, TypeError):
                    print("Notification Error!")
        except ValueError:
            res_json['error'] = error_switch.get(4)
            conn.send(json.dumps(res_json).encode())
            conn.close()


if __name__ == "__main__":
    # Test the JSONRPCServer class
    server = JSONRPCServer('0.0.0.0', 8000)

    # Register functions
    server.register('hello', functions.hello)
    server.register('greet', functions.greet)
    server.register('add', functions.add)
    server.register('sub', functions.sub)
    server.register('mul', functions.mul)
    server.register('div', functions.div)

    # Start the server
    server.start()
