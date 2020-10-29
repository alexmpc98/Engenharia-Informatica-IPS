"""
 Implements a simple database of users.

"""

import sqlite3


class Database:
    """Database connectivity."""

    def __init__(self, filename, schema):
        self.filename = filename
        self.schema = schema
        self.conn = sqlite3.connect(filename, check_same_thread=False)

        def dict_factory(cursor, row):
            """Converts table row to dictionary."""
            res = {}
            for idx, col in enumerate(cursor.description):
                res[col[0]] = row[idx]
            return res

        self.conn.row_factory = dict_factory

    def recreate(self):
        """Recreates the database from the schema file."""
        with open(self.schema) as fin:
            self.conn.cursor().executescript(fin.read())

    def execute_query(self, stmt, args=()):
        """Executes a query."""
        res = self.conn.cursor().execute(stmt, args)
        return res

    def execute_update(self, stmt, args=()):
        """Executes an insert or update and returns the last row id."""
        cursor = self.conn.cursor().execute(stmt, args)
        return cursor.lastrowid

    # -------------- USERS --------------
    def get_user(self, data):
        """Returns a single user."""
        result = self.execute_query(
            'SELECT * FROM user WHERE username=? AND password=?', (
                data['username'],
                data['password'])
        )
        return result.fetchone()

    def add_user(self, data):
        """Adds a new user."""
        self.execute_query(
            'INSERT INTO user VALUES (null, ?, ?, ?, ?)', (
                data['name'],
                data['email'],
                data['username'],
                data['password'])
        )
        return self.get_user(data)

    def update_user(self, user, data):
        """Updates a user with the given data."""
        self.execute_query(
            'UPDATE user SET name=?, email=?, username=?, password=? WHERE id=?', (
                data['name'],
                data['email'],
                data['username'],
                data['password'],
                user['id'])
        )
        return self.get_user(data)

    def user_authentication(self, username, password):
        """Updates a user with the given data."""
        data = {
            "username": username,
            "password": password
        }
        user = self.get_user(data)
        return bool(user)

    # -------------- PROJECTS --------------
    def get_projects(self, user_pk):
        """Returns all projects by user."""
        result = self.execute_query(
            'SELECT * FROM project WHERE user_id=? ORDER BY last_updated desc', (
                user_pk,)
        )
        return result.fetchall()

    def get_project(self, project_pk):
        """Returns a single project."""
        result = self.execute_query(
            'SELECT * FROM project WHERE id=?', (
                project_pk,)
        )
        return result.fetchone()

    def add_project(self, data):
        """Adds a new project."""
        result = self.execute_query(
            'INSERT INTO project VALUES (null, ?, ?, ?, ?)', (
                data['user_id'],
                data['title'],
                data['creation_date'],
                data['creation_date'])
        )
        return self.get_project(result.lastrowid)

    def update_project(self, project, data):
        """Updates a project with the given data."""
        self.execute_query(
            'UPDATE project SET user_id=?, title=?, creation_date=?, last_updated=? WHERE id=?', (
                data['user_id'],
                data['title'],
                data['creation_date'],
                data['last_updated'],
                project['id'])
        )
        return self.get_project(project['id'])

    def delete_project(self, project_pk):
        """Deletes a project."""
        result_project = self.execute_query('DELETE FROM project WHERE id=?', (project_pk,))
        result_tasks = self.execute_query('DELETE FROM task WHERE project_id=?', (project_pk,))
        return bool(result_project) and bool(result_tasks)

    # -------------- TASKS --------------
    def get_tasks(self, task_pk):
        """Returns all tasks."""
        result = self.execute_query(
            'SELECT * FROM task WHERE project_id=?', (
                task_pk,)
        )
        return result.fetchall()

    def get_task(self, project_pk, task_pk):
        """Returns a single task."""
        result = self.execute_query(
            'SELECT * FROM task WHERE project_id=? and id=?', (
                project_pk,
                task_pk)
        )
        return result.fetchone()

    def add_task(self, data):
        """Adds a new task."""
        result = self.execute_query(
            'INSERT INTO task VALUES (null, ?, ?, ?, ?)', (
                data['project_id'],
                data['title'],
                data['creation_date'],
                data['completed'])
        )
        return self.get_task(data['project_id'], result.lastrowid)

    def update_task(self, task, project, data):
        """Updates a task with the given data."""
        self.execute_query(
            'UPDATE task SET project_id=?, title=?, creation_date=?, completed=? WHERE id=?', (
                project['id'],
                data['title'],
                data['creation_date'],
                data['completed'],
                task['id'])
        )
        return self.get_task(project['id'], task['id'])

    def delete_task(self, task_pk):
        """Deletes a task."""
        result_task = self.execute_query(
            'DELETE FROM task WHERE id=?', (
                task_pk,)
        )
        return bool(result_task)
