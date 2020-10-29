"""
 Simple socket client

"""

import socket
import threading
from shared_memory import SharedMemory
from client_timer import ClientTimer
from tkinter import *

class GraphicInterface:

    def __init__(self,root):
        self.root = root
        self.command = ""
        self.entryVar = StringVar()
        self.button1 = Button(self.root)
        self.entry = Entry(self.root, textvariable=self.entryVar)
        self.menu = Menu(root)
        root.config(menu=self.menu)
        self.label = Label(root)
        self.label.pack()
        self.subMenu = Menu(self.menu)
        self.subMenu2 = Menu(self.menu)
        self.subMenu3 = Menu(self.menu)
        self.subMenu4 = Menu(self.menu)
        self.menu.add_cascade(label="Autenticação",menu=self.subMenu)
        self.menu.add_cascade(label="Salas",menu=self.subMenu2)
        self.menu.add_cascade(label="Utilizadores", menu=self.subMenu3)
        self.menu.add_cascade(label="Mensagens", menu=self.subMenu4)
        self.subMenu.add_command(label="Autenticação no Servidor",command=self.authentication)
        self.subMenu.add_command(label="Exit", command=self.exit)
        self.subMenu2.add_command(label="Sala Actual", command=self.actual_room)
        self.subMenu2.add_command(label="Salas Disponiveis", command=self.avaliable_rooms)
        self.subMenu2.add_command(label="Criar salas", command=self.create_rooms)
        self.subMenu2.add_command(label="Entrar numa sala", command=self.join_room)
        self.subMenu3.add_command(label="Utilizadores na sala", command=self.users_in_room)
        self.subMenu3.add_command(label="Todos os utilizadores", command=self.all_users)
        self.subMenu4.add_command(label="Enviar mensagem", command=self.send_msg)
        self.subMenu4.add_command(label="Mensagens privadas", command=self.check_pm)

    def authentication(self):
        self.label['text'] = "Insert your Username:"
        self.entry.pack()
        self.button1['command'] = self.set_click_auth
        self.button1['text'] = "Enter"
        self.button1.pack()

    def set_click_auth(self):
        self.command = "/username " + self.entryVar.get()
        print(self.command)
        self.entry.pack_forget()
        self.button1.pack_forget()

    def actual_room(self):
        self.label['text'] = "Room you´re at:"
        self.command = "/room "
        print(self.command)

    def avaliable_rooms(self):
        self.label['text'] = "Avaliable rooms:"
        self.command = "/rooms"
        print(self.command)

    def exit(self):
        self.command = "/exit"
        print(self.command)

    def create_rooms(self):
        self.label['text'] = "Insert the name of the room:"
        self.entry.pack()
        self.button1['command'] = self.set_click_create_rooms
        self.button1.pack()

    def set_click_create_rooms(self):
        self.command = "/create " + self.entryVar.get()
        print(self.command)
        self.entry.pack_forget()
        self.button1.pack_forget()

    def join_room(self):
        self.label['text'] = "Whats is the name of the room you want to enter?"
        self.entry.pack()
        self.button1['command'] = self.set_click_join_room
        self.button1['text'] = "Insert"
        self.button1.pack()

    def set_click_join_room(self):
        self.command = "/join " + self.entryVar.get()
        print(self.command)
        self.entry.pack_forget()
        self.button1.pack_forget()

    def users_in_room(self):
        self.label['text'] = "Users in your room:"
        self.command = "/users"
        print(self.command)

    def all_users(self):
        self.label['text'] = "All users in this chat app"
        self.command = "/allusers"
        print(self.command)

    def send_msg(self):
        self.label['text'] = "What message you want to send?"
        self.entry.pack()
        self.button1['command'] = self.set_click_send_msg
        self.button1['text'] = "Send"
        self.button1.pack()

    def set_click_send_msg(self):
        self.command = "/msg " + self.entryVar.get()
        print(self.command)
        self.entry.pack_forget()
        self.button1.pack_forget()

    def check_pm(self):
        self.label['text'] = "All your private messages:"
        self.command = "/pmsgs"
        print(self.command)

root = Tk()
b = GraphicInterface(root)
root.mainloop()


def listening_thread(client_socket1):
    """Listens to the server responses"""
    while True:
        res1 = client_socket1.recv(1024).decode()
        server_res = res1.split()
        print(server_res[0], server_res[1])

        if check_response(server_res, '/username', 'ok'):
            shared_memory.set_authenticated(True)
        elif check_response(server_res, '/exit', 'ok'):
            break


def check_response(server_res, first_check, second_check):
    """Checks the server response"""
    if server_res[0] == first_check and server_res[1] == second_check:
        return True
    return False


def callback_msg_timer(params):
    """Sends the command /msgs to the server"""
    try:
        params[0].sendall('/msgs'.encode())
    except ConnectionAbortedError:
        print('Aborted')


def callback_pmsg_timer(params):
    """Sends the command /pmsgs to the server"""
    try:
        params[0].sendall('/pmsgs'.encode())
    except ConnectionAbortedError:
        print('Aborted')


# Define socket host and port
SERVER_HOST = '127.0.0.1'
SERVER_PORT = 8000

# Initializes the shared memory
shared_memory = SharedMemory()

# Create socket
client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# Connect to server
client_socket.connect((SERVER_HOST, SERVER_PORT))

username = input('> ')
client_socket.sendall(username.encode())

res = client_socket.recv(1024).decode()
print(res)

if check_response(res.split(), '/username', 'ok'):
    shared_memory.set_authenticated(True)

# Creates the server response thread
thread = threading.Thread(target=listening_thread, args=[client_socket])
thread.start()

# Sets MSG thread timer
t_msg = ClientTimer(10, callback_msg_timer, [client_socket])

# Sets PMSG thread timer
t_pmsg = ClientTimer(10, callback_pmsg_timer, [client_socket])


if __name__ == '__main__':

    while True:
        if shared_memory.get_authenticated() and not shared_memory.get_msg_checker():
            t_msg.start()
            t_pmsg.start()
            shared_memory.set_msg_checker(True)

        # Send message
        msg = input('> ')
        client_socket.sendall(msg.encode())

        # Check for exit
        if msg == '/exit':
            t_msg.cancel()
            t_pmsg.cancel()
            thread.join()
            break

    # Close socket
    client_socket.close()
