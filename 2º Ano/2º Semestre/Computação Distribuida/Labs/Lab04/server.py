"""
 Implements a simple socket server

"""

import socket

# Define socket host and port
SERVER_HOST = '0.0.0.0'
SERVER_PORT = 8000

# Create socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server_socket.bind((SERVER_HOST, SERVER_PORT))
server_socket.listen(1)
print('Listening on port %s ...' % SERVER_PORT)

while True:
    client_connection, client_address = server_socket.accept()
    while True:
        msg = client_connection.recv(1024).decode()
        try:
            print('Received:', eval(msg))
            client_connection.send(str(eval(msg)).encode())
            if msg == "exit":
                break
        except SyntaxError:
            print('Mensagem recebida invalida!')
            client_connection.send("Mensagem recebida invalida!".encode())

    client_connection.close()

# Close socket
server_socket.close()
