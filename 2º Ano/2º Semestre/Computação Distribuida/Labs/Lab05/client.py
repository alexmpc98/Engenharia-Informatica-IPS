"""
 Simple JSON-RPC Client

"""

import socket
import json


class JSONRPCClient:
    """The JSON-RPC client."""

    def __init__(self, host, port):
        self.sock = socket.socket()
        self.sock.connect((host, port))

    def send(self, msg):
        """Sends a message to the server."""
        self.sock.sendall(msg.encode())
        return self.sock.recv(1024).decode()

    def invoke(self,method,params):
        jsonString = {"method" : method , "params" : params}
        fnljsonInvoke = json.dumps(jsonString)
        msg = self.send(fnljsonInvoke)
        if msg == 'Error':
            raise AttributeError('Remote method unavailable')
        return msg

    # 8.2
    #def add(self,a,b):
    #    return self.invoke('add',[a,b])

    def __getattr__(self, name):
        def inner(*params):
            return self.invoke(name,params)
        return inner


if __name__ == "__main__":

    # Test the JSONRPCClient class
    client = JSONRPCClient('127.0.0.1', 8000)
    jsonGreet = '{ "method" : "greet", "params" : ["Manuel"]}'
    jsonAdd = '{ "method" : "add", "params" : [2,3]}'
    jsonMul = '{ "method" : "mul", "params" : [2,3]}'
    jsonSub = '{ "method" : "sub", "params" : [4,3]}'
    jsonDiv = '{ "method" : "div", "params" : [6,3]}'
    jsonHello = '{ "method" : "hello", "params": []}'

    # Até Nivel 7, este é o res utilizado:
    #res = client.send(jsonDiv)
    #print(res)

    # res8 = client.invoke('d4',[6,3])
    # print(res8)

    # Nivel 8 - 2)
    #resAdd = client.add(6,3)
    #print(resAdd)

    # Nivel 8-3
    res = client.add(6,3)
    print(res)