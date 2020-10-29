"""
 Provides utilities for parsing http requests and responses

"""


def parse_http_request(request):
    """
    Parses an http request string into a dict.

    :param request: the http request string
    :return: dict
    """

    # Get the headers and body lines
    lines = request.splitlines()
    last_header = lines.index('')
    headers = lines[:last_header]
    body = lines[last_header:]

    req = {}

    # Parse method and url
    head = headers[0].split()
    req['method'] = head[0]
    req['url'] = head[1]
    req['version'] = head[2]

    # Parse headers
    req['headers'] = {}
    for header in headers[1:]:
        key, value = header.split(':', 1)
        req['headers'][key] = value.strip()

    # Parse body
    req['body'] = "\n".join(body).strip()

    return req


def parse_http_response(response):
    """
    Parses an response dict into an http string.

    :param response: the http response dict
    :return: string
    """

    res = 'HTTP/1.0 %s\n' % response['status']

    for header, value in response['headers'].items():
        res += '%s: %s\n' % (header, value)

    res += '\n' + response['body']

    return res


def get_request(client_connection):
    """
    Returns a dict with the parsed http request.

    :param client_connection: the client connection socket
    :return: dict
    """

    client_request = client_connection.recv(10240).decode()
    return parse_http_request(client_request)


def send_response(client_connection, response):
    """
    Sends an http response to a client connection.

    :param client_connection: the client connection socket
    :param response: the response dict
    :return: None
    """

    client_response = parse_http_response(response)
    client_connection.sendall(client_response.encode())
