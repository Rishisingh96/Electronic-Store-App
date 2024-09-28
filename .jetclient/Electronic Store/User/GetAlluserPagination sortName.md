```toml
name = 'GetAlluserPagination sortName'
description = '/users'
method = 'GET'
url = 'http://localhost:9090/users?pagenNumber=0&sortBy =name&sortDir = asc'
sortWeight = 6000000
id = '3450804d-1622-4c00-8476-66fbad29ccd2'

[[queryParams]]
key = 'pagenNumber'
value = '0'

[[queryParams]]
key = 'sortBy '
value = 'name'

[[queryParams]]
key = 'sortDir '
value = ' asc'

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
