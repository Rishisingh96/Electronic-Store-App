```toml
name = 'Create Order'
description = '/orders'
method = 'POST'
url = 'http://localhost:9090/orders'
sortWeight = 1000000
id = 'f3325ad7-1eed-4052-a1c7-e5fb1e2f5e23'

[body]
type = 'JSON'
raw = '''
{
    "cartId":"fc17182a-99a6-437f-92cb-a90fffedf6bb",
    "userId":"c2f4e643-ae78-42de-9dad-469222ea3292",
    "billingAddress":"04 Lavkush Hostal Indoe M.P ",
    "billingPhone":"9838630282",
    "billingName":"Rishi singh"
}'''
```
