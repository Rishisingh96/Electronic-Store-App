```toml
name = 'Get All user Pagination sort method'
description = '/users'
method = 'GET'
url = 'http://localhost:9090/users?pagenNumber=2&pageSize=3'
sortWeight = 8000000
id = '6c39e012-0628-4c41-9027-6b8fc61660c6'

[[queryParams]]
key = 'pagenNumber'
value = '2'

[[queryParams]]
key = 'pageSize'
value = '3'

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
