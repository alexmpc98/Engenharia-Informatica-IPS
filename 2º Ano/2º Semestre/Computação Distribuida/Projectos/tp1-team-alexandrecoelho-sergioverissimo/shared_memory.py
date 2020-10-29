class SharedMemory:
    def __init__(self):
        self._id = 0

    def get_id(self):
        return self._id

    def set_id(self, new_id):
        self._id = new_id

    def increment_id(self):
        self._id += 1
