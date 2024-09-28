```toml
name = 'create user'
description = '/users'
method = 'POST'
url = 'http://localhost:9090/users'
sortWeight = 1000000
id = '3566611d-f51c-44af-85f7-62ee4228ebbb'

[body]
type = 'JSON'
raw = '''
{
  "name": "D ",
  "email": "rishiexample.com",
  "password": "rishi12",
  "about": "Done BCA trening",
  "gender": "Male",
  "imageName": "rishi_doe.jpg"
}
'''
```
