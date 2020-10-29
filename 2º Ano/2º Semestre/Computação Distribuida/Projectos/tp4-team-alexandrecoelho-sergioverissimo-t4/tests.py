"""
 Tests the application API

"""

import base64
import unittest

from app import app, db

# pylint: disable=invalid-name

def auth_header(username, password):
    """Returns the authorization header."""
    credentials = f'{username}:{password}'
    b64credentials = base64.b64encode(credentials.encode()).decode('utf-8')
    return {'Authorization': f'Basic {b64credentials}'}


class TestBase(unittest.TestCase):
    """Base for all tests."""

    def setUp(self):
        app.config['TESTING'] = True
        self.client = app.test_client()
        self.db = db
        self.db.recreate()

    def tearDown(self):
        pass


class TestUsers(TestBase):
    """Tests for the user endpoints."""

    def setUp(self):
        super().setUp()
        self.data_get = {
            'id': 1,
            'name': 'Homer Simpson',
            'email': 'homer@simpsons.org',
            'username': 'homer',
            'password': '1234'
        }
        self.data_post = {
            'name': 'Marge Simpson',
            'email': 'marge@simpsons.org',
            'username': 'marge',
            'password': '1234'
        }
        self.data_put = {
            'name': 'Homer Simpson',
            'email': 'homer@simpsons.org',
            'username': 'homer',
            'password': '123456'
        }

    def test_correct_credentials(self):
        """Tests the user with correct credentials."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/user/', headers=credentials)
        self.assertEqual(res.status_code, 200)

    def test_wrong_credentials(self):
        """Tests the user with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.get('/api/user/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_user_detail(self):
        """Tests to get the user with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.get('/api/user/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_user_get(self):
        """Tests getting user data."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/user/', headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 200)
        self.assertEqual(json_res, self.data_get)

    def test_user_create(self):
        """Tests creating a new user."""
        res = self.client.post('/api/user/register/', json=self.data_post)
        json_res = res.get_json()
        json_res.pop('id')
        self.assertEqual(json_res, self.data_post)

    def test_user_update(self):
        """Tests updating a user."""
        credentials = auth_header('homer', '1234')
        res = self.client.put('/api/user/', json=self.data_put, headers=credentials)
        json_res = res.get_json()
        json_res.pop('id')
        self.assertEqual(json_res, self.data_put)

    def test_wrong_user_get(self):
        """Tests getting a wrong user."""
        credentials = auth_header('homer2', '1234')
        res = self.client.get('/api/user/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_user_update(self):
        """Tests updating a wrong user."""
        credentials = auth_header('homer2', '1234')
        res = self.client.put('/api/user/', json=self.data_put, headers=credentials)
        self.assertEqual(res.status_code, 403)


class TestProjects(TestBase):
    """Tests for the project endpoints."""

    def setUp(self):
        super().setUp()
        self.data_list = [
            {
                'id': 1,
                'user_id': 1,
                'title': 'Doughnuts',
                'creation_date': '2020-05-01',
                'last_updated': '2020-06-01'
            },
            {
                'id': 2,
                'user_id': 1,
                'title': 'Eat well',
                'creation_date': '2020-05-01',
                'last_updated': '2020-05-02'
            },
        ]
        self.data_get = {
            'id': 1,
            'user_id': 1,
            'title': 'Doughnuts',
            'creation_date': '2020-05-01',
            'last_updated': '2020-06-01'
        }
        self.data_post = {
            'title': 'Make dinner'
        }
        self.data_put = {
            'user_id': 1,
            'title': 'Big doughnuts',
            'creation_date': '2020-05-01',
            'last_updated': '2020-06-01'
        }

    def test_wrong_credentials_project_list_get(self):
        """Tests to get the projects with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.get('/api/projects/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_project_list_post(self):
        """Tests to create a project with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.post('/api/projects/', json=self.data_post, headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_project_detail_get(self):
        """Tests to manage a project with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.get('/api/projects/1/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_project_detail_put(self):
        """Tests to manage a project with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.put('/api/projects/1/', json=self.data_put, headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_project_detail_delete(self):
        """Tests to manage a project with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.delete('/api/projects/1/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_project_list(self):
        """Tests getting the projects list."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/projects/', headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 200)
        self.assertListEqual(json_res, self.data_list)

    def test_project_get(self):
        """Tests getting a project."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/projects/1/', headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 200)
        self.assertEqual(json_res, self.data_get)

    def test_project_create(self):
        """Tests creating a new project."""
        credentials = auth_header('homer', '1234')
        res = self.client.post('/api/projects/', json=self.data_post, headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 201)
        self.assertEqual(json_res['title'], self.data_post['title'])

    def test_project_update(self):
        """Tests updating a project."""
        credentials = auth_header('homer', '1234')
        res = self.client.put('/api/projects/1/', json=self.data_put, headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 200)
        self.assertEqual(json_res['title'], self.data_put['title'])

    def test_project_delete(self):
        """Tests deleting a project."""
        credentials = auth_header('homer', '1234')
        res = self.client.delete('/api/projects/1/', headers=credentials)
        self.assertEqual(res.status_code, 200)

    def test_wrong_project_get(self):
        """Tests getting a wrong project."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/projects/6/', headers=credentials)
        self.assertEqual(res.status_code, 404)

    def test_wrong_project_update(self):
        """Tests updating a wrong project."""
        credentials = auth_header('homer', '1234')
        res = self.client.put('/api/projects/6/', json=self.data_put, headers=credentials)
        self.assertEqual(res.status_code, 404)

    def test_wrong_project_delete(self):
        """Tests deleting a wrong project."""
        credentials = auth_header('homer', '1234')
        res = self.client.delete('/api/projects/6/', headers=credentials)
        self.assertEqual(res.status_code, 404)


class TestTasks(TestBase):
    """Tests for the tasks endpoints."""

    def setUp(self):
        super().setUp()
        self.data_list = [
            {
                'id': 1,
                'project_id': 1,
                'title': 'Search for doughnuts',
                'creation_date': '2020-05-05',
                'completed': 1
            },
            {
                'id': 2,
                'project_id': 1,
                'title': 'Eat cream',
                'creation_date': '2020-05-05',
                'completed': 0
            },
        ]
        self.data_get = {
            'id': 1,
            'project_id': 1,
            'title': 'Search for doughnuts',
            'creation_date': '2020-05-05',
            'completed': 1
        }
        self.data_post = {
            'project_id': 1,
            'title': 'Get the car key',
            'completed': 0
        }
        self.data_put = {
            'project_id': 1,
            'title': 'Search in the supermarket for doughnuts',
            'creation_date': '2020-05-05',
            'completed': 0
        }

    def test_wrong_credentials_task_list_get(self):
        """Tests to get the tasks with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.get('/api/projects/1/tasks/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_task_list_post(self):
        """Tests to create a task with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.post('/api/projects/1/tasks/', json=self.data_post, headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_task_detail_get(self):
        """Tests to get a task with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.get('/api/projects/1/tasks/1/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_task_detail_put(self):
        """Tests to update a task with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.put('/api/projects/1/tasks/1/', json=self.data_put, headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_wrong_credentials_task_detail_delete(self):
        """Tests to delete a task with incorrect credentials."""
        credentials = auth_header('no-user', 'no-password')
        res = self.client.delete('/api/projects/1/tasks/1/', headers=credentials)
        self.assertEqual(res.status_code, 403)

    def test_task_list(self):
        """Tests getting the tasks list."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/projects/1/tasks/', headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 200)
        self.assertListEqual(json_res, self.data_list)

    def test_task_get(self):
        """Tests getting a task."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/projects/1/tasks/1/', headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 200)
        self.assertEqual(json_res, self.data_get)

    def test_task_create(self):
        """Tests creating a new task."""
        credentials = auth_header('homer', '1234')
        res = self.client.post('/api/projects/1/tasks/', json=self.data_post, headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 201)
        self.assertEqual(json_res['title'], self.data_post['title'])
        self.assertEqual(json_res['completed'], self.data_post['completed'])

    def test_task_update(self):
        """Tests updating a task."""
        credentials = auth_header('homer', '1234')
        res = self.client.put('/api/projects/1/tasks/1/', json=self.data_put, headers=credentials)
        json_res = res.get_json()
        self.assertEqual(res.status_code, 200)
        self.assertEqual(json_res['title'], self.data_put['title'])

    def test_task_delete(self):
        """Tests deleting a task."""
        credentials = auth_header('homer', '1234')
        res = self.client.delete('/api/projects/1/tasks/1/', headers=credentials)
        self.assertEqual(res.status_code, 200)

    def test_wrong_project_task_get(self):
        """Tests getting a task from a wrong project."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/projects/6/tasks/1/', headers=credentials)
        self.assertEqual(res.status_code, 404)

    def test_wrong_project_task_create(self):
        """Tests creating a new task for a wrong project."""
        credentials = auth_header('homer', '1234')
        res = self.client.post('/api/projects/6/tasks/', json=self.data_post, headers=credentials)
        self.assertEqual(res.status_code, 404)

    def test_wrong_project_task_update(self):
        """Tests updating a task for a wrong project."""
        credentials = auth_header('homer', '1234')
        res = self.client.put('/api/projects/6/tasks/1/', json=self.data_put, headers=credentials)
        self.assertEqual(res.status_code, 404)

    def test_wrong_project_task_delete(self):
        """Tests deleting a task for a wrong project."""
        credentials = auth_header('homer', '1234')
        res = self.client.delete('/api/projects/6/tasks/1/', headers=credentials)
        self.assertEqual(res.status_code, 404)

    def test_wrong_task_get(self):
        """Tests getting a wrong task."""
        credentials = auth_header('homer', '1234')
        res = self.client.get('/api/projects/1/tasks/6/', headers=credentials)
        self.assertEqual(res.status_code, 404)

    def test_wrong_task_update(self):
        """Tests updating a wrong task."""
        credentials = auth_header('homer', '1234')
        res = self.client.put('/api/projects/1/tasks/6/', json=self.data_put, headers=credentials)
        self.assertEqual(res.status_code, 404)

    def test_wrong_task_delete(self):
        """Tests deleting a wrong task."""
        credentials = auth_header('homer', '1234')
        res = self.client.delete('/api/projects/1/tasks/6/', headers=credentials)
        self.assertEqual(res.status_code, 404)
