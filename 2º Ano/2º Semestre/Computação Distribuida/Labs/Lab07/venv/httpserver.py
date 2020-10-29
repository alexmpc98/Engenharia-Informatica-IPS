"""
 Implements a simple HTTP/1.0 Server

"""

import socket


# nivel 2
# def read_file(filename):
#     try:
#         fin = open('htdocs' + filename)
#         content = fin.read()
#         fin.close()
#         return content
#     except FileNotFoundError:
#         return None

# nivel 3
def handle_request(request):
    # Get the first line of the request (Ex: GET /ipsum.html HTTP/1.1)
    request_split = request.split('\n')
    command = request_split[0]

    # Split the content of first line (Ex: [‘GET’, ‘/ipsum.html’, ‘HTTP..’])
    command_split = command.split(' ')
    filename = command_split[1]

    if filename == '/':
        filename = '/index.html'

    try:
        # Open the file
        fin = open('htdocs' + filename)
        # Read contents
        content = fin.read()
        # Close file
        fin.close()
        # Return contents
        return content
    except FileNotFoundError:
        return None
        # return 'File not found'

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
    print(request)

    # nivel 1.1
    # response = 'HTTP/1.0 200 OK\n\n<h1>Hello World</h1>'

    # nivel 1.2
    # request_split = request.split("\n")
    # str1 = ''
    # for s in request_split:
    #     str1 += s + '<br>'

    # Send HTTP response
    # nivel 1.2
    # response = 'HTTP/1.0 200 OK\n\n<h1>Hello World</h1>' + str1

    # nivel 2
    # response = 'HTTP/1.0 200 OK\n\n' + read_file("\index.html")

    # nivel 4.1
    content = handle_request(request)
    if content:
        response = 'HTTP/1.0 200 OK\n\n' + str(content)
    else:
        response = 'HTTP/1.0 404 NOT FOUND\n\nFile not found'

    # nivel 3
    # response = 'HTTP/1.0 200 OK\n\n' + str(content)

    client_connection.sendall(response.encode())
    client_connection.close()

# Close socket
server_socket.close()
