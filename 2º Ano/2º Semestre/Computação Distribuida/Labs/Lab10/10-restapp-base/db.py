"""
 Implements a simple database of users.

"""

import sqlite3

conn = sqlite3.connect('database.db', check_same_thread=False)


def dict_factory(cursor, row):
    """Converts table row to dictionary."""
    res = {}
    for idx, col in enumerate(cursor.description):
        res[col[0]] = row[idx]
    return res


conn.row_factory = dict_factory


def recreate_db():
    """Recreates the database."""
    c = conn.cursor()
    c.execute("DROP TABLE IF EXISTS user")
    c.execute("""
        CREATE TABLE user (
            id INTEGER PRIMARY KEY,
            name TEXT,
            age INTEGER
        )
    """)
    c.execute("INSERT INTO user VALUES (null, 'Ana', '22')")
    c.execute("INSERT INTO user VALUES (null, 'Paulo', '25')")
    conn.commit()


def get_users():
    """Returns all users."""
    res = conn.cursor().execute('SELECT * FROM user')
    return res.fetchall()


def get_user(pk):
    """Returns a single user."""
    res = conn.cursor().execute('SELECT * FROM user WHERE id=%s' % pk)
    return res.fetchone()


def add_user(user):
    """Adds a new user."""
    stmt = "INSERT INTO user VALUES (null, '%s', '%s')" % (user['name'], user['age'])
    c = conn.cursor()
    c.execute(stmt)
    conn.commit()
    return get_user(c.lastrowid)


def update_user(user, data):
    """Updates a user with the given data."""
    stmt = "UPDATE user SET name='%s', age='%s' WHERE id=%s" % (
        data['name'], data['age'], user['id'])
    conn.cursor().execute(stmt)
    conn.commit()
    return get_user(user['id'])


def remove_user(user):
    """Deletes a user."""
    stmt = "DELETE FROM user WHERE id=%s" % user['id']
    conn.cursor().execute(stmt)
    conn.commit()
