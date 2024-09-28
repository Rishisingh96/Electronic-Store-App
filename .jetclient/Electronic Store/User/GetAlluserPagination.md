```toml
name = 'GetAlluserPagination'
description = '/users'
method = 'GET'
url = 'http://localhost:9090/users?pageNumber=1&pageSize=10'
sortWeight = 5000000
id = '1056a0db-2ad4-45ee-a4d3-918e816a4d62'

[[queryParams]]
key = 'pageNumber'
value = '1'

[[queryParams]]
key = 'pageSize'
value = '10'

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
