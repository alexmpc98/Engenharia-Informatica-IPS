"""
 Simple multi-threaded HTTP Server

"""

import socket
import threading
import mimetypes
import json
from datetime import datetime


class HTTPServer:
    """The HTTP/1.1 server."""

    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.sock = None
        self.threads = []

    def start(self):
        """Starts the server."""
        self.sock = socket.socket()
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.sock.bind((self.host, self.port))
        self.sock.listen(1)
        print('Listening on port %s ...' % self.port)

        try:
            while True:
                # Accept client connections
                conn, address = self.sock.accept()

                # Starts the client connection thread
                thread = HTTPConnection(conn, address)
                thread.start()

                # Add to list
                self.threads.append(thread)

        except ConnectionAbortedError:
            pass
        except OSError:
            pass

    def stop(self):
        """Stops the server."""
        self.sock.close()
        for thread in self.threads:
            thread.stop()


class HTTPConnection(threading.Thread):
    """The HTTP/1.1 client connection."""

    def __init__(self, conn, address):
        super().__init__()
        self.conn = conn
        self.address = address
        self.active = True
        self.MAIN_DIR = 'htdocs'
        self.HTTP_PROTOCOL = "HTTP/1.1"
        self.NO_PERMISSION = "NO_PERMISSION"
        self.NOT_FOUND = "NOT_FOUND"
        self.BAD_REQUEST = "BAD_REQUEST"
        self.error_codes = {
            200: {
                'id': '200 Ok'
            },
            400: {
                'id': '400 Bad Request',
                'info': 'Your Browser(or Proxy) Sent a Request That This Server Could Not Understand'
            },
            403: {
                'id': '403 Forbidden',
                'info': 'Access Denied You Dont Have Permission to Access'
            },
            404: {
                'id': '404 Not Found',
                'info': 'File Not Found'
            }
        }

    def stop(self):
        """Stops the client thread."""
        self.active = False
        self.conn.close()

    @classmethod
    def last_index(cls, collection):
        """Returns the last index"""
        return len(collection) - 1

    def parse_post_to_json(self, content):
        """Receives a POST request and parses it and returns it"""
        output = {}
        if len(content) > 0:
            fields = content.split('&')
            if len(fields) > 1:
                for field in fields:
                    field_split = field.split('=')
                    output.update({field_split[0]: field_split[1]})
            else:
                return self.BAD_REQUEST
        return output

    @classmethod
    def save_log(cls, headers, status):
        """Receives the headers and status and writes to the log to file"""
        if len(headers) > 0:
            logs_file = open("log.txt", "a")
            date_string = datetime.now().strftime("%Y/%m/%d %H:%M:%S")
            if len(headers) > 1:
                path = headers[1]
            else:
                path = ''
            log_string = date_string + ' ' + headers[0] + ' ' + path + ' - ' + status + '\n'
            print("\n", log_string)
            logs_file.write(log_string)
            logs_file.close()

    @classmethod
    def parse_json_request(cls, request):
        """Receives a request returns the json object in the request"""
        json_obj = '{' + request.split('{')[1]
        return json.loads(json_obj)

    @classmethod
    def set_json_output(cls, method, content_type, content_length, content):
        """Receives a method, content_type, content_length and
           content and returns a json object"""
        return {
            'method': method,
            'content_type': content_type,
            'content_length': content_length,
            'content': content
        }

    def read_file(self, filename):
        """Receives a filename and returns the file content if exists"""
        try:
            if (
                    filename.endswith('jpg')
                    or filename.endswith('png')
                    or filename.endswith('mp3')
                    or filename.endswith('mp4')
            ):
                with open(self.MAIN_DIR + filename, 'rb') as fin:
                    read = fin.read()
            elif filename.endswith('py'):
                return self.NO_PERMISSION
            else:
                with open(self.MAIN_DIR + filename) as fin:
                    read = fin.read()
            return read
        except FileNotFoundError:
            return self.NOT_FOUND

    def handle_get(self, get_content):
        """Receives a GET request and returns a JSON formatted return"""
        request_type = 'GET'
        # Get filename
        if len(get_content) < 2:
            json_return = self.set_json_output(
                request_type,
                'text/plain',
                len(self.BAD_REQUEST),
                self.BAD_REQUEST
            )
        else:
            filename = get_content[1]
            if filename == '/':
                filename = '/index.html'
            elif '/private' in filename or '../' in filename:
                return self.set_json_output(
                    request_type,
                    'text/plain',
                    len(self.NO_PERMISSION),
                    self.NO_PERMISSION
                )

            # Return file contents
            file_content = self.read_file(filename)
            if file_content != self.NOT_FOUND:
                json_return = self.set_json_output(
                    request_type,
                    mimetypes.guess_type(filename)[0],
                    len(file_content),
                    file_content
                )
            elif file_content == self.NO_PERMISSION:
                json_return = self.set_json_output(
                    request_type,
                    'text/plain',
                    len(self.NO_PERMISSION),
                    self.NO_PERMISSION
                )
            else:
                json_return = self.set_json_output(
                    request_type,
                    'text/plain',
                    len(self.NOT_FOUND),
                    self.NOT_FOUND
                )
        return json_return

    def handle_post(self, get_content, request, headers):
        """Receives a POST request and returns a JSON formatted return"""
        request_type = 'POST'
        path = get_content[1]
        if path == '/json':
            json_obj = self.parse_json_request(request)
            json_return = self.set_json_output(
                request_type,
                'application/json',
                len(json.dumps(json_obj)),
                json_obj
            )
        elif path == '/form':
            json_obj = self.parse_post_to_json(headers[len(headers) - 1])
            if json_obj != self.BAD_REQUEST:
                json_return = self.set_json_output(
                    request_type,
                    'application/json',
                    len(json.dumps(json_obj)),
                    json_obj
                )
            else:
                json_return = self.set_json_output(
                    request_type,
                    'text/plain',
                    len(self.BAD_REQUEST),
                    self.BAD_REQUEST
                )
        return json_return

    def handle_request(self, request):
        """Returns the content for a client request"""
        # Parse headers
        headers = request.split('\n')
        get_content = headers[0].split()
        request_type = get_content[0]

        # GET REQUEST
        if request_type == 'GET':
            json_return = self.handle_get(get_content)
        # POST REQUEST
        elif request_type == 'POST':
            json_return = self.handle_post(get_content, request, headers)
        else:
            json_return = self.set_json_output(
                request_type,
                'text/plain',
                len(self.BAD_REQUEST),
                self.BAD_REQUEST
            )
        return json_return

    def handle_response(self, request, content):
        """Returns byte-encoded HTTP response."""
        # Build HTTP response
        # STATUS 400
        if content['content'] == self.BAD_REQUEST:
            status = self.error_codes[400]['id']
            response = f'{self.HTTP_PROTOCOL} {status}\n\n' \
                       f'{self.error_codes[400]["info"]}'.encode()
        # STATUS 403
        elif content['content'] == self.NO_PERMISSION:
            status = self.error_codes[403]['id']
            response = f'{self.HTTP_PROTOCOL} {status}\n\n' \
                       f'{self.error_codes[403]["info"]}'.encode()
        # STATUS 404
        elif content['content'] == self.NOT_FOUND:
            status = self.error_codes[404]['id']
            response = f'{self.HTTP_PROTOCOL} {status}\n\n' \
                       f'{self.error_codes[404]["info"]}'.encode()
        # STATUS 200
        elif content['content']:
            status = self.error_codes[200]['id']
            response = f'{self.HTTP_PROTOCOL} {status}\n'.encode()
            response += f'Content-Length: {content["content_length"]}\n'.encode()
            response += f'Content-Type: {content["content_type"]}\n\n'.encode()
            if isinstance(content['content'], bytes):
                response += content['content']
            elif content['content_type'] == 'application/json':
                response += json.dumps(content['content']).encode()
            else:
                response += content['content'].encode()
        # DEFAULT STATUS
        else:
            status = self.error_codes[404]['id']
            response = f'{self.HTTP_PROTOCOL} {status}\n\n' \
                       f'{self.error_codes[404]["info"]}'.encode()

        # Save log
        headers = request.split('\n')
        self.save_log(headers[0].split(), status)

        # Return encoded response
        return response

    def run(self):
        """Handles the client connection."""

        while self.active:
            try:
                # Handle client request
                msg = self.conn.recv(1024).decode()
                msg_array = msg.split('\n')

                # Get the connection (close or keep-alive)
                if 'Connection:' in msg:
                    indices = [i for i, elem in enumerate(msg_array) if 'Connection:' in elem]
                    connection_mode = msg_array[indices[0]].split()[1]
                else:
                    # Default connection mode
                    connection_mode = 'close'

                if len(msg) > 0:
                    # Handle client request
                    content = self.handle_request(msg)

                    # Prepare byte-encoded HTTP response
                    response = self.handle_response(msg, content)

                    # Return HTTP response
                    self.conn.sendall(response)

            except ConnectionAbortedError as error:
                print("Error(Receive):", error)
                connection_mode = 'close'

            # Closes the client connection
            if connection_mode == 'close':
                self.active = False

        # Close client connection
        print('Client disconnected...')
        self.stop()
        self.conn.close()


if __name__ == '__main__':
    # Starts the http server
    server = HTTPServer('0.0.0.0', 8000)
    server.start()
