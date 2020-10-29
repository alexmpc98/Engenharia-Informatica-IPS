"""
 Flask REST application

"""

from flask import Flask, request, jsonify, make_response
import db

app = Flask(__name__, static_url_path='/static')


@app.route('/')
def index():
    return app.send_static_file('index.html')


@app.route("/api/users/", methods=['GET'])
def all_users():
    users = db.get_users()
    return make_response(jsonify(users))


db.recreate_db()
app.run(host='0.0.0.0', port=8000, debug=True)
