class Room:
    def __init__(self, name):
        self.name = name
        self.active_users = []

    def add_user(self, user):
        self.active_users.append(user)

    def add_message(self, message):
        self.messages.append(message)

    def user_exist(self, name):
        for user in self.active_users:
            if user.get_name() == name:
                return True
        return False

    def get_name(self):
        return self.name

    def get_active_users(self):
        return self.active_users

    def remove_user(self, user):
        self.active_users.remove(user)

    def broadcast(self, msg):
        for user in self.active_users:
            user.add_received_room_message(msg)

    def to_string(self):
        ret = 'Room: ['
        ret += 'Name: ' + self.name + ', Active Users: ['
        for user in self.active_users:
            ret += user.to_string() + ', '
        ret += ']]'
        return ret
