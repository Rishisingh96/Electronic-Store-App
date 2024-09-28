```toml
name = 'Get all Product'
description = '/products'
method = 'GET'
url = 'http://localhost:9090/products'
sortWeight = 2000000
id = '4cd147a7-6a7c-47db-821a-37d6d1213023'

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
