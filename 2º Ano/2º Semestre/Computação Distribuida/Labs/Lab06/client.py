"""
 Implements a simple socket client

"""

import socket
import threading


# Nivel 4.1
def listening_thread(client_socket1):
    while True:
        # Recebe mensagem do servidor
        # Imprime a mensagem do servidor
        res1 = client_socket1.recv(1024).decode()
        print(res1)

        # Nivel  4.2
        if 'Goodbye' in res1:
            break


# Define socket host and port
SERVER_HOST = '127.0.0.1'
SERVER_PORT = 8000

# Create socket
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect to server
client_socket.connect((SERVER_HOST, SERVER_PORT))

# Nivel 1.1
print("User? ")
usernameInput = input("")
client_socket.sendall(usernameInput.encode())

# Nivel 1.2
res = client_socket.recv(1024).decode()
print(res)

# Nivel 4.1
# Cria thread
thread = threading.Thread(target=listening_thread, args=[client_socket])
thread.start()

while True:
    # Send message
    msg = input('> ')
    client_socket.sendall(msg.encode())

    # Check for exit
    if msg == 'exit':
        # Nivel 1.3
        # res = client_socket.recv(1024).decode()
        # print(res)

        # Nivel 4.2
        thread.join()
        break

    # # Read response
    # res = client_socket.recv(1024).decode()
    # print(res)

# Close socket
client_socket.close()
