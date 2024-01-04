# Item API Spec

## Create Item
Endpoint : POST /api/v1/items/create

Request Body :
```json
{
  "name" : "aqua",
  "category" : "minuman"
}
```

Response Body (Success) :
```json
{
  "status_code": 200,
  "message": "The item was created successfully",
  "data": {
    "id": 10,
    "name": "aqua",
    "category": "minuman",
    "createAt": "2024-01-04T19:54:06.2364055",
    "updateAt": "2024-01-04T19:54:06.2364055"
  },
  "errors": null
}
```

Response Body (Failed) :
```json
{
  "status_code": 400,
  "message": "Validation errors in your request",
  "errors": "category: must not be blank, name: must not be blank"
}
```

## List Item
Endpoint : POST /api/v1/items/fetch

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
      "id": 10,
      "name": "aqua",
      "category": "minuman",
      "createAt": "2024-01-04T19:54:06.236406",
      "updateAt": null
    },
    {
      "id": 9,
      "name": "es teh tawar",
      "category": "minuman",
      "createAt": "2024-01-04T19:53:54.889396",
      "updateAt": null
    },
    {
      "id": 8,
      "name": "es teh manis",
      "category": "minuman",
      "createAt": "2024-01-04T19:53:48.990161",
      "updateAt": null
    },
    {
      "id": 7,
      "name": "teh tawar",
      "category": "minuman",
      "createAt": "2024-01-04T19:53:35.706208",
      "updateAt": null
    },
    {
      "id": 6,
      "name": "teh manis",
      "category": "minuman",
      "createAt": "2024-01-04T19:53:24.667515",
      "updateAt": null
    },
    {
      "id": 5,
      "name": "mie yamin",
      "category": "makanan",
      "createAt": "2024-01-04T19:53:12.668764",
      "updateAt": null
    },
    {
      "id": 4,
      "name": "mie ayam",
      "category": "makanan",
      "createAt": "2024-01-04T19:53:06.477205",
      "updateAt": null
    },
    {
      "id": 3,
      "name": "bakso urat jumbo",
      "category": "makanan",
      "createAt": "2024-01-04T19:52:58.286192",
      "updateAt": null
    },
    {
      "id": 2,
      "name": "bakso keju",
      "category": "makanan",
      "createAt": "2024-01-04T19:52:42.673287",
      "updateAt": null
    },
    {
      "id": 1,
      "name": "bakso urat",
      "category": "makanan",
      "createAt": "2024-01-04T19:52:30.401123",
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
  "errors": "list items not found!"
}
```

## Remove Item
Endpoint : POST /api/v1/items/remove

Request Body :
```json
{
  "id" : "2"
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
  "errors": "items not found!"
}
```