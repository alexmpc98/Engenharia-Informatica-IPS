"""
 Server tests

"""

import socket
import threading
import time
import unittest

from server import Server


# Define server host and port
SERVER_HOST = '127.0.0.1'
SERVER_PORT = 8000


class SimpleClient:
    """Mocks a client."""

    def __init__(self, host, port):
        self.sock = socket.socket()
        self.sock.connect((host, port))

    def command(self, msg):
        """Sends a command and returns response."""
        self.sock.sendall(msg.encode())
        return self.sock.recv(1024).decode()

    def close(self):
        """Closes the client socket."""
        self.sock.close()


class TestBase(unittest.TestCase):
    """Base for all tests."""

    def setUp(self):
        # Start the server in a thread
        self.server = Server(SERVER_HOST, SERVER_PORT)
        self.server_thread = threading.Thread(target=self.server.start)
        self.server_thread.start()

        # Starts a client socket
        time.sleep(0.001)
        self.client = SimpleClient(SERVER_HOST, SERVER_PORT)

    def tearDown(self):
        # Stops the server thread
        self.server.stop()
        self.server_thread.join()

        # Stops client
        self.client.close()


class TestChatProtocolStart(TestBase):
    """Tests the protocol for starting the chat."""

    def testProvideUsername(self):
        """Clients must provide a username."""
        res = self.client.command('/username bob')
        self.client.command('/exit')
        self.assertEqual(res, '/username ok')

    def testUniqueUsername(self):
        """Clients must provide an unique username."""
        # Create a second client
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username bob')

        res = self.client.command('/username bob')
        other.command('/exit')
        self.client.command('/exit')
        self.assertEqual(res, '/username taken')

    def testMustProvideUsername(self):
        """Clients must provide username before using other commands."""
        res = self.client.command('/exit')
        self.client.command('/username none')
        self.client.command('/exit')
        self.assertEqual(res, '/username required')


class TestChatProtocolEnd(TestBase):
    """Tests the protocol for ending the chat."""

    def testExit(self):
        """Clients must receive the exit confirmation."""
        self.client.command('/username bob')
        res = self.client.command('/exit')
        self.assertEqual(res, '/exit ok')


class TestChatRooms(TestBase):
    """Tests the chat rooms."""

    def setUp(self):
        super().setUp()
        self.client.command('/username bob')

    def testWelcomeRoom(self):
        """Welcome room exists by default."""
        res = self.client.command('/rooms')
        self.client.command('/exit')
        self.assertEqual(res, '/rooms #welcome')

    def testDefaultRoom(self):
        """Users enter the welcome room by default."""
        res = self.client.command('/room')
        self.client.command('/exit')
        self.assertEqual(res, '/room #welcome')

    def testCreateRoom(self):
        """Users can create rooms."""
        res = self.client.command('/create #teste')
        self.client.command('/exit')
        self.assertEqual(res, '/create ok')

    def testCreateExistingRoom(self):
        """Users cannot create rooms that exist."""
        self.client.command('/create #teste')
        res = self.client.command('/create #teste')
        self.client.command('/exit')
        self.assertEqual(res, '/create room_exists')

    def testCreateRoomList(self):
        """Created rooms must appear on the list."""
        self.client.command('/create #teste')
        res = self.client.command('/rooms')
        self.client.command('/exit')
        self.assertIn('/rooms', res)
        self.assertIn('#welcome', res)
        self.assertIn('#teste', res)

    def testJoinExistingRoom(self):
        """Users can join existing rooms."""
        self.client.command('/create #teste')
        res = self.client.command('/join #teste')
        self.client.command('/exit')
        self.assertEqual(res, '/join ok')

    def testJoinNonExistentRoom(self):
        """Users cannot join non-existent rooms."""
        res = self.client.command('/join #noroom')
        self.client.command('/exit')
        self.assertEqual(res, '/join no_room')

    def testCurrentRoom(self):
        """Users may know which room they are."""
        self.client.command('/create #teste')
        self.client.command('/join #teste')
        res = self.client.command('/room')
        self.client.command('/exit')
        self.assertEqual(res, '/room #teste')


class TestUsers(TestBase):
    """Tests the users."""

    def setUp(self):
        super().setUp()
        self.client.command('/username bob')

    def testUserInRoom(self):
        """The user must be in the room list."""
        res = self.client.command('/users')
        self.client.command('/exit')
        self.assertEqual(res, '/users bob')

    def testAllUsersInRoom(self):
        """Returns all the users in the room."""
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')

        res = self.client.command('/users')
        other.command('/exit')
        self.client.command('/exit')
        self.assertIn('/users', res)
        self.assertIn('bob', res)
        self.assertIn('ted', res)

    def testOnlyTheUsersInRoom(self):
        """Must return only the users in the same room."""
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')

        self.client.command('/create #teste')
        self.client.command('/join #teste')
        res = self.client.command('/users')
        other.command('/exit')
        self.client.command('/exit')
        self.assertEqual(res, '/users bob')

    def testSystemUsers(self):
        """List users in the system."""
        # Create a second client @#welcome
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')

        self.client.command('/create #teste')
        self.client.command('/join #teste')
        res = self.client.command('/allusers')
        other.command('/exit')
        self.client.command('/exit')
        self.assertIn('/allusers', res)
        self.assertIn('bob@#teste', res)
        self.assertIn('ted@#welcome', res)

    def testExitUser(self):
        """A user that exits must be removed from the user list."""
        # Create a second client
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')
        other.command('/exit')
        other.close()

        res = self.client.command('/allusers')
        self.client.command('/exit')
        self.assertNotIn('ted', res)
        self.assertEqual(res, '/allusers bob@#welcome')


class TestRoomMessages(TestBase):
    """Tests the room messages."""

    def setUp(self):
        super().setUp()
        self.client.command('/username bob')

    def testRetrieveEmptyMessages(self):
        """Users can retrieve empty list of messages."""
        res = self.client.command('/msgs')
        self.client.command('/exit')
        self.assertEqual(res, '/msgs none')

    def testSendMessage(self):
        """Users must receive message sent ack."""
        res = self.client.command('/msg Hello!')
        self.client.command('/exit')
        self.assertEqual(res, '/msg sent')

    def testMessageInSameRoom(self):
        """Users in same room must receive room messages."""
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')
        other.command('/msg hello')

        res = self.client.command('/msgs')
        other.command('/exit')
        self.client.command('/exit')
        self.assertEqual(res, '/msgs (ted @#welcome) hello')

    def testMessageOtherRoom(self):
        """Users in different rooms must not receive messages."""
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')
        other.command('/create #teste')
        other.command('/join #teste')
        other.command('/msg hello')

        res = self.client.command('/msgs')
        other.command('/exit')
        self.client.command('/exit')
        self.assertEqual(res, '/msgs none')

    def testSeveralMessages(self):
        """Several messages arrive in room."""
        ted = SimpleClient(SERVER_HOST, SERVER_PORT)
        ted.command('/username ted')
        ted.command('/msg hello')

        sid = SimpleClient(SERVER_HOST, SERVER_PORT)
        sid.command('/username sid')
        sid.command('/msg hi')

        res = self.client.command('/msgs')
        ted.command('/exit')
        sid.command('/exit')
        self.client.command('/exit')
        self.assertIn('(ted @#welcome) hello', res)
        self.assertIn('(sid @#welcome) hi', res)

    def testMessageCleared(self):
        """Messages must be cleared after being retrieved"""
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')
        other.command('/msg hello')

        res = self.client.command('/msgs')
        self.assertEqual(res, '/msgs (ted @#welcome) hello')
        res = self.client.command('/msgs')
        other.command('/exit')
        self.client.command('/exit')
        self.assertEqual(res, '/msgs none')


class TestPrivateMessages(TestBase):
    """Tests the private messages."""

    def setUp(self):
        super().setUp()
        self.client.command('/username bob')

    def testSendPM(self):
        """Users must receive private message sent ack."""
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')
        other.command('/msg hello')

        res = self.client.command('/pmsg ted hi!')
        other.command('/exit')
        self.client.command('/exit')
        self.assertEqual(res, '/pmsg sent')

    def testPMNoUser(self):
        """Destination user must exist"""
        res = self.client.command('/pmsg ted hi!')
        self.client.command('/exit')
        self.assertEqual(res, '/pmsg no_user')

    def testRetrievePM(self):
        """Users can retrieve empty list of private messages."""
        res = self.client.command('/pmsgs')
        self.client.command('/exit')
        self.assertEqual(res, '/pmsgs none')

    def testPMArrives(self):
        """Private messages arrive to the target user."""
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')
        other.command('/pmsg bob hello')

        res = self.client.command('/pmsgs')
        other.command('/exit')
        self.client.command('/exit')
        self.assertEqual(res, '/pmsgs (ted @private) hello')

    def testSeveralPMArrives(self):
        """Several private messages arrive to the target user."""
        ted = SimpleClient(SERVER_HOST, SERVER_PORT)
        ted.command('/username ted')
        ted.command('/pmsg bob hello')

        sid = SimpleClient(SERVER_HOST, SERVER_PORT)
        sid.command('/username sid')
        sid.command('/pmsg bob hi')

        res = self.client.command('/pmsgs')
        ted.command('/exit')
        sid.command('/exit')
        self.client.command('/exit')
        self.assertIn('(ted @private) hello', res)
        self.assertIn('(sid @private) hi', res)

    def testPMQueueCleared(self):
        """Private messages must be cleared after being retrieved"""
        other = SimpleClient(SERVER_HOST, SERVER_PORT)
        other.command('/username ted')
        other.command('/pmsg bob hello')

        res = self.client.command('/pmsgs')
        self.assertEqual(res, '/pmsgs (ted @private) hello')
        res = self.client.command('/pmsgs')
        other.command('/exit')
        self.client.command('/exit')
        self.assertEqual(res, '/pmsgs none')
