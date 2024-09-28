```toml
name = 'sortdesc'
description = '/products'
method = 'GET'
url = 'http://localhost:9090/products?sortDir=asc&sortBy=price'
sortWeight = 7000000
id = '3fcf0224-bfeb-4667-bd39-76bd21012f3a'

[[queryParams]]
key = 'sortDir'
value = 'asc'

[[queryParams]]
key = 'sortBy'
value = 'price'

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
