"""
 Simple socket client

"""

import socket
import threading
from shared_memory import SharedMemory
from client_timer import ClientTimer


def listening_thread(client_socket1):
    """Listens to the server responses"""
    while True:
        res1 = client_socket1.recv(1024).decode()
        server_res = res1.split()
        print(server_res[0], server_res[1])

        if check_response(server_res, '/username', 'ok'):
            shared_memory.set_authenticated(True)
        elif check_response(server_res, '/exit', 'ok'):
            break


def check_response(server_res, first_check, second_check):
    """Checks the server response"""
    if server_res[0] == first_check and server_res[1] == second_check:
        return True
    return False


def callback_msg_timer(params):
    """Sends the command /msgs to the server"""
    try:
        params[0].sendall('/msgs'.encode())
    except ConnectionAbortedError:
        print('Aborted')


def callback_pmsg_timer(params):
    """Sends the command /pmsgs to the server"""
    try:
        params[0].sendall('/pmsgs'.encode())
    except ConnectionAbortedError:
        print('Aborted')


# Define socket host and port
SERVER_HOST = '127.0.0.1'
SERVER_PORT = 8000

# Initializes the shared memory
shared_memory = SharedMemory()

# Create socket
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect to server
client_socket.connect((SERVER_HOST, SERVER_PORT))

username = input('> ')
client_socket.sendall(username.encode())

res = client_socket.recv(1024).decode()
print(res)

if check_response(res.split(), '/username', 'ok'):
    shared_memory.set_authenticated(True)

# Creates the server response thread
thread = threading.Thread(target=listening_thread, args=[client_socket])
thread.start()

# Sets MSG thread timer
t_msg = ClientTimer(10, callback_msg_timer, [client_socket])

# Sets PMSG thread timer
t_pmsg = ClientTimer(10, callback_pmsg_timer, [client_socket])


if __name__ == '__main__':

    while True:
        if shared_memory.get_authenticated() and not shared_memory.get_msg_checker():
            t_msg.start()
            t_pmsg.start()
            shared_memory.set_msg_checker(True)

        # Send message
        msg = input('> ')
        client_socket.sendall(msg.encode())

        # Check for exit
        if msg == '/exit':
            t_msg.cancel()
            t_pmsg.cancel()
            thread.join()
            break

    # Close socket
    client_socket.close()
