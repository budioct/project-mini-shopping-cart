# Customer API Spec

## Create Customer
Endpoint : POST /api/v1/customers/create

Request Body :
```json
{
  "name" : "Budhi Octaviansyah",
  "address" : "Tangerang, Indonesia"
}
```

Response Body (Success) :
```json
{
  "status_code": 200,
  "message": "The item was created successfully",
  "data": {
    "id": 1,
    "name": "Budhi Octaviansyah",
    "address": "Tangerang, Indonesia",
    "createAt": "2024-01-04T19:51:05.1842337",
    "updateAt": "2024-01-04T19:51:05.1842337"
  },
  "errors": null
}
```

Response Body (Failed) :
```json
{
  "status_code": 400,
  "message": "Validation errors in your request",
  "errors": "address: must not be blank, name: must not be blank"
}
```

## List Customer
Endpoint : POST /api/v1/customers/fetch

Request Body :
```json
{
  "page" : "",
  "size" : "",
  "sort" : ""
}
```

Response Body (Success) :
```json
{
  "status_code": 200,
  "message": "The item exist",
  "paging": {
    "currentPage": 0,
    "totalPage": 1,
    "sizePage": 10
  },
  "list": [
    {
      "id": 2,
      "name": "Achmad Husein",
      "address": "Jakarta, Indonesia",
      "createAt": "2024-01-04T19:51:05.184234",
      "updateAt": null
    },
    {
      "id": 1,
      "name": "Budhi Octaviansyah",
      "address": "Tangerang, Indonesia",
      "createAt": "2024-01-04T19:50:38.748834",
      "updateAt": null
    }
  ],
  "errors": null
}
```

Response Body (Failed) :

```json
{
  "status_code": 404,
  "message": "Validation errors in your request",
  "errors": "list customers not found!"
}
```

## Remove Customer
Endpoint : POST /api/v1/customers/remove

Request Body :
```json
{
  "id" : "1"
}
```

Response Body (Success) :
```json
{
  "status_code": 200,
  "message": "The item was deleted successfully",
  "data": "",
  "errors": null
}
```

Response Body (Failed) :
```json
{
  "status_code": 404,
  "message": "The request was not valid",
  "errors": "customer not found!"
}
```