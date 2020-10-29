"""
 Implements a simple webservice

"""

import socket

import app
import httputils


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
    # Wait for client connections
    client_connection, client_address = server_socket.accept()

    # Get client request
    request = httputils.get_request(client_connection)

    # Handle request on app controller
    response = app.controller(request)

    # Return HTTP response
    httputils.send_response(client_connection, response)
    client_connection.close()

# Close socket
server_socket.close()
