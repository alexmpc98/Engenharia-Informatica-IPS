"""
 Simple multi-threaded socket server

"""

import socket
import threading
from user import User
from room import Room


class Server:
    """The chat server."""
    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.sock = None
        self.chat = {'rooms': [], 'users': []}
        self.rooms = self.chat['rooms']
        self.users = self.chat['users']

    def start(self):
        """Starts the server."""
        self.sock = socket.socket()
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.sock.bind((self.host, self.port))
        self.sock.listen(1)
        print('Listening on port %s ...' % self.port)
        # Create welcome room
        self.rooms.append(Room("#welcome"))

        try:
            while True:
                # Accept client connections
                conn, _ = self.sock.accept()

                # Starts client thread
                thread = threading.Thread(target=self.handle_client, args=(conn, ))
                thread.start()

        except ConnectionAbortedError:
            pass
        except OSError:
            pass

    def get_room(self, name):
        """Gets the room with the respective name."""
        for obj in self.rooms:
            if obj.get_name() == name:
                return obj
        return False

    def get_user(self, conn):
        """Gets the user with the respective connection."""
        for obj in self.users:
            if obj.get_connection() == conn:
                return obj
        return False

    def get_connection(self, name):
        """Gets the connection with the respective username."""
        for obj in self.users:
            if obj.get_name() == name:
                return obj.get_connection()
        return False

    @classmethod
    def print_collection(cls, collection, name):
        """Prints the given collention."""
        ret = name + ": ["
        for obj in collection:
            ret += obj.to_string() + ', '
        print(ret + ']')

    @classmethod
    def check_if_exists(cls, collection, name):
        """Checks if the name exist in the given collection."""
        for obj in collection:
            if obj.get_name() == name:
                return True
        return False

    def current_room(self, conn):
        """Gets the room where the user is."""
        logged_user = self.get_user(conn)
        for room in self.rooms:
            for user in room.get_active_users():
                if user == logged_user:
                    return room
        return False

    @classmethod
    def close_connection(cls, conn):
        """Closes the given connection."""
        try:
            conn.close()
        except ConnectionAbortedError as error:
            print("Error(Close):", error)

    def stop(self):
        """Stops the server."""
        self.sock.close()

    def handle_client(self, conn):
        """Handles the client."""
        logged_user = self.get_user(conn)
        username = ''
        while True:
            try:
                # Receive message
                msg = conn.recv(1024).decode()
                if logged_user:
                    print("\nReceived(" + username + "):", msg)
                else:
                    print("\nReceived(None):", msg)
                # Message split
                cmds = msg.split()
            except ConnectionAbortedError as error:
                print("Error(Receive):", error)
                self.close_connection(conn)

            if len(cmds) > 0:
                cmd = cmds[0]
                args = cmds[1:]
                if not logged_user:
                    # /USERNAME
                    if cmd == '/username' and len(args) > 0:
                        username = args[0]
                        if not self.check_if_exists(self.users, username):
                            new_user = User(username, conn)
                            self.users.append(new_user)
                            room = self.get_room('#welcome')
                            room.add_user(new_user)
                            logged_user = self.get_user(conn)
                            res = '/username ok'
                        else:
                            res = '/username taken'
                    else:
                        res = '/username required'

                else:
                    # /ROOMS
                    if cmd == '/rooms':
                        res = '/rooms '
                        for i, obj in enumerate(self.rooms):
                            if i > 0:
                                res += ', '
                            res += str(obj.get_name())

                    # /ROOM
                    elif cmd == '/room':
                        res = '/room '
                        for room in self.rooms:
                            for user in room.get_active_users():
                                if self.get_user(conn) == user:
                                    res += str(room.get_name())

                    # /CREATE
                    elif cmd == '/create':
                        if not self.get_room(args[0]) and args[0][0:1] == '#':
                            new_room = Room(args[0])
                            self.rooms.append(new_room)
                            res = '/create ok'
                        else:
                            res = '/create room_exists'

                    # /JOIN
                    elif cmd == '/join':
                        if self.check_if_exists(self.rooms, args[0]):
                            self.current_room(conn).remove_user(logged_user)
                            self.get_room(args[0]).add_user(logged_user)
                            res = '/join ok'
                        else:
                            res = '/join no_room'

                    # /USERS
                    elif cmd == '/users':
                        res = '/users '
                        self.print_collection(self.rooms, 'Rooms')
                        for i, obj in enumerate(self.current_room(conn).get_active_users()):
                            if i > 0:
                                res += ', '
                            res += str(obj.get_name())

                    # /ALLUSERS
                    elif cmd == '/allusers':
                        res = '/allusers '
                        for i_room, obj_room in enumerate(self.rooms):
                            if i_room > 0:
                                res += ', '
                            curr_room_users = obj_room.get_active_users()
                            for i_user, obj_user in enumerate(curr_room_users):
                                if i_user > 0:
                                    res += ', '
                                res += obj_user.get_name() + '@' + obj_room.get_name()

                    # /MSG
                    elif cmd == '/msg':
                        res = '/msg sent'
                        current_room = self.current_room(conn)
                        message = '(' + logged_user.get_name() + ' @' \
                                  + current_room.get_name() + ') ' + args[0]
                        current_room.broadcast(message)

                    # /MSGS
                    elif cmd == '/msgs':
                        res = '/msgs '
                        messages = logged_user.get_received_room_messages()
                        msgs_len = len(messages)
                        if msgs_len > 0:
                            for i in range(msgs_len):
                                res += messages[i]
                                if i < (msgs_len-1):
                                    res += ' \n'
                            logged_user.remove_received_room_messages()
                        else:
                            res += 'none'

                    # /PMSG
                    elif cmd == '/pmsg':
                        connection = self.get_connection(args[0])
                        if connection:
                            res = '/pmsg sent'
                            msg_to_send = '(' + logged_user.get_name() + ' @private) ' + args[1]
                            self.get_user(connection).add_received_private_message(msg_to_send)
                        else:
                            res = '/pmsg no_user'

                    # /PMSGS
                    elif cmd == '/pmsgs':
                        res = '/pmsgs '
                        messages = logged_user.get_received_private_messages()
                        msgs_len = len(messages)
                        if msgs_len > 0:
                            for i in range(msgs_len):
                                res += messages[i]
                                if i < (msgs_len-1):
                                    res += ' \n'
                            logged_user.remove_received_private_messages()
                        else:
                            res += 'none'

                    # /EXIT
                    elif cmd == '/exit':
                        res = '/exit ok'
                        print("Sent(" + username + "):", res)
                        try:
                            conn.sendall(res.encode())
                        except ConnectionAbortedError as error:
                            print("Error(Send):", error)
                            self.close_connection(conn)
                        break

                    # INVALID COMMAND
                    else:
                        res = '/invalid command'
            else:
                res = '/username required'

            # Send response
            if logged_user:
                print("Sent(" + username + "):", res)
            else:
                print("Sent(None):", res)
            try:
                conn.sendall(res.encode())
            except ConnectionAbortedError as error:
                print("Error(Send):", error)
                self.close_connection(conn)

        # Close client connection
        print('Client disconnected...')
        if logged_user:
            current_room = self.current_room(conn)
            current_room.remove_user(logged_user)
            self.users.remove(logged_user)
        self.close_connection(conn)


if __name__ == "__main__":

    # Starts the server
    server = Server('0.0.0.0', 8000)
    server.start()
