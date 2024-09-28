```toml
name = 'Get All user Pagination sort Gender'
description = '/users'
method = 'GET'
url = 'http://localhost:9090/users?pagenNumber=0&sortBy =gender&sortDir = asc'
sortWeight = 7000000
id = '245698c3-87ae-4d94-8bf2-dbc1577110c6'

[[queryParams]]
key = 'pagenNumber'
value = '0'

[[queryParams]]
key = 'sortBy '
value = 'gender'

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
