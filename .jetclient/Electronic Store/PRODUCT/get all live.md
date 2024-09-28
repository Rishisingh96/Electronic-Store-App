```toml
name = 'get all live'
description = '/products/live'
method = 'GET'
url = 'http://localhost:9090/products/live'
sortWeight = 8000000
id = '24efe4c9-00e3-48a7-8a8b-495104a24ff6'

[body]
type = 'JSON'
raw = '''
{
    "title":"Iphone 14 max pro",
    "description":"This is very good Iphone launched in 2024",
    "price":90000,
    "discountaedPrice":85000,
    "quantity":100,
    "live": true,
    "stock":false
}'''
```
