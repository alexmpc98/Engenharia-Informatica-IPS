"""
 Simple JSON-RPC Server

"""

import socket
import functions
import json

class JSONRPCServer:
    """The JSON-RPC server."""
    funcs = {
        'hello': functions.hello,
        'hello2': functions.hello2,
        'greet': functions.greet,
        'add': functions.add,
    }

    def __init__(self, host, port):
        self.host = host
        self.port = port

    def start(self):
        """Starts the server."""
        sock = socket.socket()
        sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        sock.bind((self.host, self.port))
        sock.listen(1)
        print('Listening on port %s ...' % self.port)

        while True:
            # Accept client
            conn, _ = sock.accept()

            # Receive message
            msg = conn.recv(1024).decode()
            print('Received:', msg)

            # Send response
            # Nivel 1- Invocação de funções
            res1 = functions.hello()
            # Nivel 2 - Nomes das funções
            if msg == "hello":
                res2 = functions.hello()
            elif msg == "hello2":
                res2 = functions.hello2()
            else:
                res2 = 'Error'

            # Nivel 3 - Parametros / Nivel 4 - Multiplos Parametros
            if msg.startswith('greet'):
                values = msg.split()
                name = values[1]
                res3and4 = functions.greet(name)
                #conn.send(res3and4.encode())
            elif msg.startswith('add'):
                 values = msg.split()
                 a = values[1]
                 b = values[2]
                 res3and4 = functions.add(eval(a),eval(b))
                 #conn.send(str(res3and4).encode())

            # Nivel 5 - JSON
            # Convert json message to dict
            msg = json.loads(msg)
            method = msg['method']
            params = msg['params']

            if method == 'greet':
               res5 = functions.greet(*params)
               result = {"result": str(res5)}
               jsonResult = json.dumps(result)
               #conn.send(jsonResult.encode())
            elif method == 'add':
                res5 = functions.add(*params)
                result = {"result" : str(res5)}
                jsonResult = json.dumps(result)
                #conn.send(jsonResult.encode())
            else:
                result = {"error" : "Method not found"}
                jsonResult = json.dumps(result)
                #conn.send(jsonResult.encode())

            # Nivel 7 - Funções como objetos

            try:
                func = self.funcs[method]
                res = str(func(*params))
            except KeyError:
                res = 'Error'
                conn.send(res.encode())

            conn.send(res.encode())


            # Close client connection
            conn.close()

    # Nivel 7 - Funções como objetos
    def register(self, name, function):
        newValue = {name : function}
        self.funcs.update(newValue)


if __name__ == "__main__":

    # Test the JSONRPCServer class
    server = JSONRPCServer('0.0.0.0', 8000)
    server.register('mul',functions.mul)
    server.register('sub', functions.sub)
    server.register('div', functions.div)
    server.start()
