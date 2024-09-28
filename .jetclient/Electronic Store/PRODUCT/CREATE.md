```toml
name = 'CREATE'
description = '/products'
method = 'POST'
url = 'http://localhost:9090/products'
sortWeight = 1000000
id = '24256f9b-43e5-4f71-b817-2c39da768b39'

[body]
type = 'JSON'
raw = '''
{
    "title":"Mi S12",
    "description":"This is very chip Phone launched in 2017",
    "price":5000,
    "discountedPrice":1000,
    "quantity":50,
    "live": false,
    "stock":false
}'''
```
