"""
 Flask REST application

"""
from datetime import datetime
from flask import Flask, request, jsonify, make_response
from models import Database

# pylint: disable=invalid-name

# ==========
#  Settings
# ==========

app = Flask(__name__)
app.config['STATIC_URL_PATH'] = '/static'
app.config['DEBUG'] = True


# ==========
#  Database
# ==========

# Creates an sqlite database in memory
db = Database(filename=':memory:', schema='schema.sql')
db.recreate()


# ===========
#  Web views
# ===========

@app.route('/')
def index():
    """
    Returns the static html file index.html
    """
    return app.send_static_file('index.html')


# ===========
#  API views
# ===========

@app.route('/api/user/register/', methods=['POST'])
def user_register():
    """
    Registers a new user.
    Does not require authorization.
    """
    data = request.get_json()
    user = db.get_user(data)
    if user is None:
        created_user = db.add_user(data)
        if created_user is not None:
            response = make_response(jsonify(created_user), 201)
        else:
            response = make_response(jsonify(), 400)
    else:
        response = make_response(jsonify(), 400)
    return response


@app.route('/api/user/', methods=['GET', 'PUT'])
def user_detail():
    """
    Returns or updates current user.
    Requires authorization.
    """
    auth = db.user_authentication(request.authorization.username, request.authorization.password)
    if auth:
        user = db.get_user({
            "username": request.authorization.username,
            "password": request.authorization.password
        })
        if request.method == 'GET':
            # Returns user data
            response = make_response(jsonify(user), 200)
        else:
            # Updates user data
            data = request.get_json()
            updated_user = db.update_user(user, data)
            if updated_user is not None:
                response = make_response(jsonify(updated_user), 200)
            else:
                response = make_response(jsonify(), 400)
    else:
        response = make_response(jsonify(), 403)
    return response


@app.route('/api/projects/', methods=['GET', 'POST'])
def project_list():
    """
    Project list.
    Requires authorization.
    """
    auth = db.user_authentication(request.authorization.username, request.authorization.password)
    if auth:
        user_id = db.get_user({
            "username": request.authorization.username,
            "password": request.authorization.password
        })['id']
        if request.method == 'GET':
            # Returns the list of projects of a user
            projects = db.get_projects(user_id)
            if projects is not None:
                response = make_response(jsonify(projects), 200)
            else:
                response = make_response(jsonify(), 404)
        else:
            # Adds a project to the list
            data = request.get_json()
            data['user_id'] = user_id
            data['creation_date'] = datetime.now().strftime("%Y-%m-%d")
            created_project = db.add_project(data)
            if created_project is not None:
                response = make_response(jsonify(created_project), 201)
            else:
                response = make_response(jsonify(), 400)
    else:
        response = make_response(jsonify(), 403)
    return response


@app.route('/api/projects/<int:pk>/', methods=['GET', 'PUT', 'DELETE'])
def project_detail(pk):
    """
    Project detail.
    Requires authorization.
    """
    auth = db.user_authentication(request.authorization.username, request.authorization.password)
    if auth:
        project = db.get_project(pk)
        if project is None:
            response = make_response(jsonify(), 404)
        elif request.method == 'GET':
            # Returns a project
            response = make_response(jsonify(project), 200)
        elif request.method == 'PUT':
            # Updates a project
            data = request.get_json()
            data['last_updated'] = datetime.now().strftime("%Y-%m-%d")
            updated_project = db.update_project(project, data)
            if updated_project is not None:
                response = make_response(jsonify(updated_project), 200)
            else:
                response = make_response(jsonify(), 400)
        else:
            # Deletes a project
            delete_status = db.delete_project(pk)
            if delete_status:
                response = make_response(jsonify(), 200)
            else:
                response = make_response(jsonify(), 400)
    else:
        response = make_response(jsonify(), 403)
    return response


@app.route('/api/projects/<int:pk>/tasks/', methods=['GET', 'POST'])
def task_list(pk):
    """
    Task list.
    Requires authorization.
    """
    auth = db.user_authentication(request.authorization.username, request.authorization.password)
    if auth:
        if request.method == 'GET':
            # Returns the list of tasks of a project
            tasks = db.get_tasks(pk)
            if tasks is not None:
                response = make_response(jsonify(tasks), 200)
            else:
                response = make_response(jsonify(), 404)
        else:
            # Adds a task to project
            data = request.get_json()
            creation_task_date = datetime.now().strftime("%Y-%m-%d")
            data['creation_date'] = creation_task_date
            project = db.get_project(pk)
            if project is not None:
                data_project = project
                data_project['last_updated'] = creation_task_date
                created_task = db.add_task(data)
                db.update_project(project, data_project)
                if created_task is not None:
                    response = make_response(jsonify(created_task), 201)
                else:
                    response = make_response(jsonify(), 400)
            else:
                response = make_response(jsonify(), 404)
    else:
        response = make_response(jsonify(), 403)
    return response


@app.route('/api/projects/<int:pk>/tasks/<int:task_pk>/', methods=['GET', 'PUT', 'DELETE'])
def task_detail(pk, task_pk):
    """
    Task detail.
    Requires authorization.
    """
    auth = db.user_authentication(request.authorization.username, request.authorization.password)
    if auth:
        project = db.get_project(pk)
        task = db.get_task(pk, task_pk)
        if project is None or task is None:
            response = make_response(jsonify(), 404)
        elif request.method == 'GET':
            # Returns a task
            response = make_response(jsonify(task), 200)
        elif request.method == 'PUT':
            # Updates a task
            data = request.get_json()
            data_project = project
            data_project['last_updated'] = datetime.now().strftime("%Y-%m-%d")
            db.update_project(project, data_project)
            updated_task = db.update_task(task, project, data)
            if updated_task is not None:
                response = make_response(jsonify(updated_task), 200)
            else:
                response = make_response(jsonify(), 400)
        else:
            # Deletes a task
            delete_status = db.delete_task(task_pk)
            if delete_status:
                response = make_response(jsonify(), 200)
            else:
                response = make_response(jsonify(), 400)
    else:
        response = make_response(jsonify(), 403)
    return response


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=8000)
