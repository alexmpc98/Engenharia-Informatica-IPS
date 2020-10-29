"""
 Basic webservice application

"""

import json


USERS = [
    {
        "id": 1,
        "name": "Ana",
        "age": 22,
    },
    {
        "id": 2,
        "name": "Paulo",
        "age": 25,
    }
]


def controller(request):
    """Handles a request and returns a response."""

    print(request)
    return {
        'status': '200 OK',
        'headers': {
            'Content-Type': 'application/json'
        },
        'body': json.dumps(USERS),
    }
