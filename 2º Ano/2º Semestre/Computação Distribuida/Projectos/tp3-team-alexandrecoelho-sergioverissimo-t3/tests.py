"""
 HTTPServer tests

"""

import json
import socket
import time
import threading
import unittest
import urllib.error
import urllib.request

from httpserver import HTTPServer


# Define server host and port
SERVER_HOST = '127.0.0.1'
SERVER_PORT = 8000


class TestBase(unittest.TestCase):
    """Base for all tests."""

    def setUp(self):
        # Start the server in a thread
        self.server = HTTPServer(SERVER_HOST, SERVER_PORT)
        self.server_thread = threading.Thread(target=self.server.start)
        self.server_thread.start()

        # Defines the base url
        self.url = 'http://127.0.0.1:8000/'

    def tearDown(self):
        # Stops the server thread
        self.server.stop()
        self.server_thread.join()
        time.sleep(0.1)


class TestHTMLFiles(TestBase):
    """Tests the html files."""

    def test_index_status(self):
        """Index document should return 200 Ok."""
        res = urllib.request.urlopen(self.url)
        self.assertEqual(res.status, 200)

    def test_index_content(self):
        """Index document should return correct body."""
        res = urllib.request.urlopen(self.url)
        body = res.read().decode()
        self.assertIn('<title>Hello World</title>', body)

    def test_index_content_type(self):
        """Server should return correct content-type."""
        res = urllib.request.urlopen(self.url)
        headers = dict(res.info())
        self.assertIn('text/html', headers['Content-Type'])

    def test_index_content_length(self):
        """Server should return correct content-length in bytes."""
        res = urllib.request.urlopen(self.url)
        headers = dict(res.info())
        body = res.read()
        self.assertEqual(headers['Content-Length'], str(len(body)))

    def test_ipsum_status(self):
        """Server should return other files status."""
        res = urllib.request.urlopen(self.url + 'public/ipsum.html')
        self.assertEqual(res.status, 200)

    def test_ipsum_content(self):
        """Server should return other files content."""
        res = urllib.request.urlopen(self.url + 'public/ipsum.html')
        headers = dict(res.info())
        self.assertEqual(headers['Content-Type'], 'text/html')

    def test_ipsum_content_type(self):
        """Server should return correct content-type for other files."""
        res = urllib.request.urlopen(self.url + 'public/ipsum.html')
        headers = dict(res.info())
        self.assertEqual(headers['Content-Type'], 'text/html')

    def test_ipsum_content_length(self):
        """Server should return correct content-length for other files."""
        res = urllib.request.urlopen(self.url + 'public/ipsum.html')
        headers = dict(res.info())
        body = res.read()
        self.assertEqual(headers['Content-Length'], str(len(body)))

    def test_not_found_status(self):
        """Non-existing public documents should return 404."""
        try:
            urllib.request.urlopen(self.url + 'error.html')
            self.assertEqual(True, False)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 404)

    def test_private_pages(self):
        """Forbidden urls should return 403."""
        try:
            urllib.request.urlopen(self.url + 'private/file.html')
            self.assertEqual(True, False)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 403)


class TestJPEGFiles(TestBase):
    """Tests the jpeg files."""

    def test_jpeg_status(self):
        """Server should fetch jpg files."""
        res = urllib.request.urlopen(self.url + 'assets/image.jpg')
        self.assertEqual(res.status, 200)

    def test_jpeg_content(self):
        """Server should fetch jpg content."""
        res = urllib.request.urlopen(self.url + 'assets/image.jpg')
        body = res.read()
        self.assertNotEqual(len(body), 0)

    def test_jpeg_content_type(self):
        """Server should return content-type."""
        res = urllib.request.urlopen(self.url + 'assets/image.jpg')
        headers = dict(res.info())
        self.assertIn('image/jpeg', headers['Content-Type'])

    def test_jpeg_content_length(self):
        """Server should return content-length."""
        res = urllib.request.urlopen(self.url + 'assets/image.jpg')
        headers = dict(res.info())
        body = res.read()
        self.assertEqual(headers['Content-Length'], str(len(body)))
        # self.assertEqual(headers['Content-Length'], '81447')

    def test_jpeg_not_found(self):
        """Server should return 404 for non-existent jpg images."""
        self.assertRaises(
            urllib.error.HTTPError,
            urllib.request.urlopen, self.url + 'assets/non-image.jpg'
        )


class TestPNGFiles(TestBase):
    """Tests the png files."""

    def test_png_status(self):
        """Server should fetch png files."""
        res = urllib.request.urlopen(self.url + 'assets/image.png')
        self.assertEqual(res.status, 200)

    def test_png_content(self):
        """Server should fetch png content."""
        res = urllib.request.urlopen(self.url + 'assets/image.png')
        body = res.read()
        self.assertNotEqual(len(body), 0)

    def test_png_content_type(self):
        """Server should return content-type."""
        res = urllib.request.urlopen(self.url + 'assets/image.png')
        headers = dict(res.info())
        self.assertIn('image/png', headers['Content-Type'])

    def test_png_content_length(self):
        """Server should return content-length."""
        res = urllib.request.urlopen(self.url + 'assets/image.png')
        headers = dict(res.info())
        body = res.read()
        self.assertEqual(headers['Content-Length'], str(len(body)))

    def test_png_not_found(self):
        """Server should return 404 for non-existent png images."""
        try:
            urllib.request.urlopen(self.url + 'assets/new_image.png')
            self.assertEqual(True, False)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 404)


class TestMP3Files(TestBase):
    """Tests the mp3 files."""

    def test_mp3_status(self):
        """Server should fetch mp3 files."""
        res = urllib.request.urlopen(self.url + 'assets/audio.mp3')
        self.assertEqual(res.status, 200)

    def test_mp3_content(self):
        """Server should fetch mp3 content."""
        res = urllib.request.urlopen(self.url + 'assets/audio.mp3')
        body = res.read()
        self.assertNotEqual(len(body), 0)

    def test_mp3_content_type(self):
        """Server should return content-type."""
        res = urllib.request.urlopen(self.url + 'assets/audio.mp3')
        headers = dict(res.info())
        self.assertIn('audio/mpeg', headers['Content-Type'])

    def test_mp3_content_length(self):
        """Server should return content-length."""
        res = urllib.request.urlopen(self.url + 'assets/audio.mp3')
        headers = dict(res.info())
        body = res.read()
        self.assertEqual(headers['Content-Length'], str(len(body)))

    def test_mp3_not_found(self):
        """Server should return 404 for non-existent mp3."""
        try:
            urllib.request.urlopen(self.url + 'assets/new_audio.mp3')
            self.assertEqual(True, False)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 404)


class TestMP4Files(TestBase):
    """Tests the mp4 files."""

    def test_mp4_status(self):
        """Server should fetch mp4 files."""
        res = urllib.request.urlopen(self.url + 'assets/video.mp4')
        self.assertEqual(res.status, 200)

    def test_mp4_content(self):
        """Server should fetch mp4 content."""
        res = urllib.request.urlopen(self.url + 'assets/video.mp4')
        body = res.read()
        self.assertNotEqual(len(body), 0)

    def test_mp4_content_type(self):
        """Server should return content-type."""
        res = urllib.request.urlopen(self.url + 'assets/video.mp4')
        headers = dict(res.info())
        self.assertIn('video/mp4', headers['Content-Type'])

    def test_mp4_content_length(self):
        """Server should return content-length."""
        res = urllib.request.urlopen(self.url + 'assets/video.mp4')
        headers = dict(res.info())
        body = res.read()
        self.assertEqual(headers['Content-Length'], str(len(body)))

    def test_mp4_not_found(self):
        """Server should return 404 for non-existent mp4."""
        try:
            urllib.request.urlopen(self.url + 'assets/new_video.mp4')
            self.assertEqual(True, False)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 404)


class TestHTTPConnections(TestBase):
    """Tests HTTP connections to a server."""

    def setUp(self):
        super().setUp()
        time.sleep(0.1)
        self.sock = socket.socket()
        self.sock.connect((SERVER_HOST, SERVER_PORT))

    def tearDown(self):
        super().tearDown()
        self.sock.close()

    def test_bad_request(self):
        """Malformed HTTP requests should return 400."""
        self.sock.sendall('GET\n\n'.encode())
        res = self.sock.recv(1024).decode()
        self.assertIn('400', res)

    def test_connection_close(self):
        """Http connections must be closed if header is sent."""
        self.sock.sendall('GET / HTTP/1.1\nConnection: close\n\n'.encode())
        #print(self.sock.recv(1024).decode())
        try:
            for _ in range(3):
                self.sock.sendall('GET / HTTP/1.1\nConnection: close\n\n'.encode())
                res = self.sock.recv(1024).decode()
                print(res)
                time.sleep(0.1)
            self.assertEqual(len(res), 0)
        except OSError or BrokenPipeError or ConnectionResetError:
            print('Connection closed as intended')
            self.assertEqual(True, True)

    def test_connection_keep_alive(self):
        """Http connections must be kept open if header is sent."""
        self.sock.sendall('GET / HTTP/1.1\nConnection: keep-alive\n\n'.encode())
        self.sock.recv(1024).decode()
        try:
            for _ in range(3):
                self.sock.sendall('GET / HTTP/1.1\nConnection: keep-alive\n\n'.encode())
                res = self.sock.recv(12000).decode()
                time.sleep(0.1)
                self.assertNotEqual(len(res), 0)
        except BrokenPipeError or ConnectionResetError:
            print('Connection should have closed')
            self.assertEqual(True, False)


class TestFormRequests(TestBase):
    """Tests form POST requests to a server."""

    def setUp(self):
        super().setUp()
        self.data = {'a': 'x', 'b': 'y', 'c': 'z'}
        self.url_data = "&".join(['%s=%s' % (k, v) for k, v in self.data.items()])

    def test_form_status(self):
        """POST to /form with url encoded data should return 200."""
        req = urllib.request.Request(self.url + 'form', self.url_data.encode())
        res = urllib.request.urlopen(req)
        self.assertEqual(res.status, 200)

    def test_form_content(self):
        """POST to /form with url encoded data should return json content."""
        req = urllib.request.Request(self.url + 'form', self.url_data.encode())
        res = urllib.request.urlopen(req)
        json_res = json.loads(res.read())
        self.assertEqual(json_res, self.data)

    def test_form_content_type(self):
        """POST to /form with url encoded data should return json content type."""
        req = urllib.request.Request(self.url + 'form', self.url_data.encode())
        res = urllib.request.urlopen(req)
        headers = dict(res.info())
        self.assertIn('application/json', headers['Content-Type'])

    def test_form_content_length(self):
        """POST to /form with url encoded data should return json size."""
        req = urllib.request.Request(self.url + 'form', self.url_data.encode())
        res = urllib.request.urlopen(req)
        headers = dict(res.info())
        size = len(json.dumps(self.data))
        self.assertEqual(size, int(headers['Content-Length']))

    def test_form_get_method(self):
        """GET requests to /form must return 404."""
        self.assertRaises(
            urllib.error.HTTPError,
            urllib.request.urlopen, self.url + 'form'
        )

    def test_form_wrong_content_type(self):
        """POST to /form with other content type must return bad request."""
        req = urllib.request.Request(self.url + 'form', json.dumps(self.data).encode())
        req.add_header('Content-Type', 'application/json')
        try:
            urllib.request.urlopen(req)
            self.assertEqual(True, False)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 400)


class TestJsonRequests(TestBase):
    """Tests POST requests to /json urls."""

    def setUp(self):
        super().setUp()
        self.data = {'a': 'x', 'b': 'y', 'c': 'z'}
        self.json = json.dumps(self.data)

    def test_json_status(self):
        """POST to /json with json data should return 200."""
        req = urllib.request.Request(self.url + 'json', self.json.encode())
        res = urllib.request.urlopen(req)
        self.assertEqual(res.status, 200)

    def test_json_content(self):
        """POST to /json with json data should return the same json content."""
        req = urllib.request.Request(self.url + 'json', self.json.encode())
        res = urllib.request.urlopen(req)
        json_res = json.loads(res.read())
        print(self.data, '|', json_res)
        self.assertEqual(self.data, json_res)

    def test_json_content_type(self):
        """POST to /json with json data should return json content type."""
        req = urllib.request.Request(self.url + 'json', self.json.encode())
        res = urllib.request.urlopen(req)
        headers = dict(res.info())
        self.assertIn('application/json', headers['Content-Type'])

    def test_json_content_length(self):
        """POST to /json with json data should return json size."""
        req = urllib.request.Request(self.url + 'json', self.json.encode())
        res = urllib.request.urlopen(req)
        headers = dict(res.info())
        body = res.read()
        self.assertEqual(headers['Content-Length'], str(len(body)))

    def test_json_get_method(self):
        """GET requests to /json must return 404."""
        self.assertRaises(
            urllib.error.HTTPError,
            urllib.request.urlopen, self.url + 'json'
        )

    def test_json_invalid_json(self):
        """POST to /json with invalid json must return bad request."""
        req = urllib.request.Request(self.url + 'form', '123'.encode())
        req.add_header('Content-Type', 'application/json')
        try:
            urllib.request.urlopen(req)
            self.assertEqual(True, False)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 400)


class TestInsecureRequests(TestBase):
    """Tests insecure requests to a server."""

    def test_python_files_status(self):
        """Server must return 403 for files outside htdocs."""
        try:
            res = urllib.request.urlopen(self.url + '../httpserver.py')
            self.assertNotEqual(res.status, 200)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 403)

    def test_python_files_content(self):
        """Server must return 403 for files outside htdocs."""
        try:
            res = urllib.request.urlopen(self.url + '../httpserver.py')
            body = res.read().decode()
            self.assertNotIn('class HTTPServer', body)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 403)

    def test_python_files_status_2(self):
        """Server must return 403 for files outside htdocs."""
        try:
            res = urllib.request.urlopen(self.url + 'public/../../httpserver.py')
            self.assertNotEqual(res.status, 200)
        except urllib.error.HTTPError as error:
            self.assertEqual(error.code, 403)


class TestLogs(TestBase):
    """Tests server logs."""

    def test_logs_ok(self):
        """Request method, URI and response status must be in the log file."""
        urllib.request.urlopen(self.url + 'public/ipsum.html')
        time.sleep(0.1)
        with open('log.txt') as fin:
            lines = fin.readlines()
            last_line = lines[-1]
            self.assertIn('GET /public/ipsum.html - 200 Ok', last_line)

    def test_logs_ok_fields(self):
        """Log file must contain <date> <time> <method> <uri> - <status>"""
        urllib.request.urlopen(self.url)
        time.sleep(0.1)
        with open('log.txt') as fin:
            lines = fin.readlines()
            last_line = lines[-1]
            self.assertEqual(len(last_line.split()), 7)

    def test_logs_not_found(self):
        """Not found must be in the log file."""
        try:
            res = urllib.request.urlopen(self.url + 'public/file.html')
            self.assertEqual(res.status, 404)
        except urllib.error.HTTPError:
            time.sleep(0.1)
            with open('log.txt') as fin:
                lines = fin.readlines()
                last_line = lines[-1]
                self.assertIn('GET /public/file.html - 404 Not Found', last_line)

    def test_logs_forbidden(self):
        """Forbidden must be in the log file."""
        try:
            res = urllib.request.urlopen(self.url + 'private/file.html')
            self.assertNotEqual(res.status, 200)
        except urllib.error.HTTPError:
            time.sleep(0.1)
            with open('log.txt') as fin:
                lines = fin.readlines()
                last_line = lines[-1]
                self.assertIn('GET /private/file.html - 403 Forbidden', last_line)
