"""
 Implements a simple socket server

"""

import socket
import threading


# Nivel 2.2
def handle_client(client_connection1):
    # Nivel 1.2
    username = client_connection.recv(1024).decode()
    print('Received:', username)
    msg = "You are now connected " + username + "!"
    client_connection1.sendall(msg.encode())

    # Nivel 4.3
    msg = "New Client: " + username + "\n"
    broadcast(msg)

    while True:
        # Print message from client
        msg = client_connection1.recv(1024).decode()
        print('Received:', msg)

        # Terminate connection
        if msg == 'exit':
            # Nivel 1.3
            msg = "Goodbye " + username + "!"
            client_connection1.sendall(msg.encode())
            break

        # Send response to client
        # Nivel 3.3
        # client_connection1.sendall(msg.encode())
        msg1 = "(" + username + ") " + msg
        broadcast(msg1)

    # Close client connection
    print('Client disconnected...')
    client_connection1.close()

    # Nivel 3.2
    connections.remove(client_connection1)

    # Nivel 4.3
    broadcast('Client disconnected: ' + username)


# Nivel 3.3
def broadcast(msg):
    print(msg)
    for conn in connections:
        conn.sendall(msg.encode())


# Define socket host and port
SERVER_HOST = '0.0.0.0'
SERVER_PORT = 8000

# Create socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
server_socket.bind((SERVER_HOST, SERVER_PORT))
server_socket.listen(1)
print('Listening on port %s ...' % SERVER_PORT)

# Nivel 3.1
connections = []

while True:
    # Wait for client connections
    client_connection, client_address = server_socket.accept()

    # Nivel 3.1
    connections.append(client_connection)

    # Nivel 2.2
    # Cria thread
    thread = threading.Thread(target=handle_client, args=[client_connection])
    thread.start()

    # # Nivel 1.2
    # username = client_connection.recv(1024).decode()
    # print('Received:', username)
    # msg = "You are now connected " + username + "!"
    # client_connection.sendall(msg.encode())
    #
    # while True:
    #     # Print message from client
    #     msg = client_connection.recv(1024).decode()
    #     print('Received:', msg)
    #
    #     # Terminate connection
    #     if msg == 'exit':
    #         # Nivel 1.3
    #         msg = "Goodbye " + username + "!"
    #         client_connection.sendall(msg.encode())
    #         break
    #
    #     # Send response to client
    #     client_connection.sendall(msg.encode())
    #
    # # Close client connection
    # print('Client disconnected...')
    # client_connection.close()

# Close socket
server_socket.close()
