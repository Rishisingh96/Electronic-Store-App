```toml
name = 'GetAlluser Pagination'
description = '/users'
method = 'GET'
url = 'http://localhost:9090/users?pageNumber=2&pageSize=2'
sortWeight = 12000000
id = '346ad4fc-8ffb-4360-abf4-117da00347a0'

[[queryParams]]
key = 'pageNumber'
value = '2'

[[queryParams]]
key = 'pageSize'
value = '2'

[body]
type = 'JSON'
raw = '''
{
  "name": "Mohit ",
  "email": "mohit@example.com",
  "password": "mohit12",
  "about": "Done BCA trening",
  "gender": "Male",
  "imageName": "rishi_doe.jpg"
}
'''
```
