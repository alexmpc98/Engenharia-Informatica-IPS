class SharedMemory:
    def __init__(self):
        self.authenticated = False
        self.msg_checker = False

    def get_authenticated(self):
        return self.authenticated

    def set_authenticated(self, state):
        self.authenticated = state

    def get_msg_checker(self):
        return self.msg_checker

    def set_msg_checker(self, state):
        self.msg_checker = state

