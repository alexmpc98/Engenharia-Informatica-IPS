class User:
    def __init__(self, name, conn):
        self.name = name
        self.connection = conn
        self.received_private_messages = []
        self.received_room_messages = []

    def get_name(self):
        return self.name

    def get_connection(self):
        return self.connection

    def add_received_private_message(self, priv_msg):
        self.received_private_messages.append(priv_msg)

    def add_received_room_message(self, room_msg):
        self.received_room_messages.append(room_msg)

    def get_received_private_messages(self):
        return self.received_private_messages

    def get_received_room_messages(self):
        return self.received_room_messages

    def remove_received_private_messages(self):
        for message in self.received_private_messages:
            self.received_private_messages.remove(message)

    def remove_received_room_messages(self):
        for message in self.received_room_messages:
            self.received_room_messages.remove(message)

    def to_string(self):
        return 'Name: ' + self.name