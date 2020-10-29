"""
 Implements a simple HTTP/1.0 Server

"""

import socket
import time


def handle_request(request):
    """Returns file content for client request"""
    # Parse headers
    print(request)
    headers = request.split('\n')
    get_content = headers[0].split()

    # Get filename
    if len(get_content) < 2:
        filename = '/index.html'
    else:
        filename = get_content[1]
        if filename == '/':
            filename = '/index.html'

    try:
        if filename.endswith('jpg'):
            with open('htdocs' + filename, 'rb') as fin:
                return fin.read()
        # Return file contents
        else:
            with open('htdocs' + filename) as fin:
                return fin.read()
    except FileNotFoundError:
        return None


def handle_response(content):
    """Returns byte-encoded HTTP response."""

    # Build HTTP response
    if content:
        response = 'HTTP/1.0 200 OK\n'.encode()
        response += f'Content-Length: {len(content)}\n'.encode()
        if isinstance(content,bytes):
            response += 'Content-type: image/jpeg\n\n'.encode()
            response += content
        else:
            response += 'Content-type: text/html\n\n'.encode()
            response += content.encode()
    else:
        response = 'HTTP/1.0 404 NOT FOUND\n\nFile Not Found'.encode()

    # Return encoded response
    return response


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

    # Handle client request
    request = client_connection.recv(1024).decode()
    content = handle_request(request)

    # Prepare byte-encoded HTTP response
    response = handle_response(content)

    # Return HTTP response
    print(response)
    client_connection.sendall(response)
    time.sleep(5)
    client_connection.close()

# Close socket
server_socket.close()
