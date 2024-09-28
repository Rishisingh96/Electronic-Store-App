```toml
name = 'UpdateUser'
description = '/users/679f0c0c-68bc-41f5-856b-cd5efa7c7af0'
method = 'PUT'
url = 'http://localhost:9090/users/679f0c0c-68bc-41f5-856b-cd5efa7c7af0'
sortWeight = 15000000
id = '37838e9e-8a2a-4c80-9f1a-430f98f8fc89'

[body]
type = 'JSON'
raw = '''
{
    "userId": "c7268e12-626c-4379-95f2-1142c9897802",
    "name": "Rohan singh ",
    "email": "rohan@example.com",
    "password": "rohan12",
    "gender": "male",
    "about": "Doing D Pharma ",
    "imageName": "rohan_doe.jpg"
}'''
```
