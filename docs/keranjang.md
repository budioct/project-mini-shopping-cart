# Keranjang API Spec

## Create Keranjang
Endpoint : POST /api/v1/keranjangs/create

Request Body :
```json
{
  "keterangan" : "mas minta gelas dan es batunya yaa!",
  "items" : [
    {
      "itemId" : "5",
      "countItem" : "2"
    },
    {
      "itemId" : "4",
      "countItem" : "3"
    },
    {
      "itemId" : "10",
      "countItem" : "6"
    }
  ]
}
```

Response Body (Success) :
```json
{
  "status_code": 200,
  "message": "Items telah masuk ke dalam keranjang",
  "data": {
    "id": 1,
    "items": [
      {
        "id": 10,
        "name": "aqua",
        "category": "minuman"
      },
      {
        "id": 4,
        "name": "mie ayam",
        "category": "makanan"
      },
      {
        "id": 5,
        "name": "mie yamin",
        "category": "makanan"
      }
    ],
    "jumlah_unit_pemesanan": 11,
    "keterangan": "mas minta gelas dan es batunya yaa!",
    "createAt": "2024-01-04T19:56:49.5699006",
    "updateAt": "2024-01-04T19:56:49.5699006"
  },
  "errors": null
}
```

Response Body (Failed) :
```json
{
  "status_code": 400,
  "message": "Validation errors in your request",
  "errors": "keterangan: must not be blank, itemId: must not be null, countItem: must not be null"
}
```

## List Keranjang
Endpoint : POST /api/v1/keranjangs/fetch

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
      "id": 1,
      "items": [
        {
          "id": 5,
          "name": "mie yamin",
          "category": "makanan"
        },
        {
          "id": 4,
          "name": "mie ayam",
          "category": "makanan"
        },
        {
          "id": 10,
          "name": "aqua",
          "category": "minuman"
        }
      ],
      "jumlah_unit_pemesanan": 11,
      "keterangan": "mas minta gelas dan es batunya yaa!",
      "createAt": "2024-01-04T19:56:49.569901",
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
  "errors": "list keranjang not found!"
}
```

## Detail Keranjang
Endpoint : POST /api/v1/keranjangs/detail

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
  "message": "Detail Items yang ada di keranjang anda!",
  "data": {
    "id": 1,
    "items": [
      {
        "id": 5,
        "name": "mie yamin",
        "category": "makanan"
      },
      {
        "id": 4,
        "name": "mie ayam",
        "category": "makanan"
      },
      {
        "id": 10,
        "name": "aqua",
        "category": "minuman"
      }
    ],
    "jumlah_unit_pemesanan": 11,
    "keterangan": "mas minta gelas dan es batunya yaa!",
    "createAt": "2024-01-04T19:56:49.569901",
    "updateAt": null
  },
  "errors": null
}
```

Response Body (Failed) :
```json
{
  "status_code": 404,
  "message": "The request was not valid",
  "errors": "keranjangs not found!"
}
```

## Remove Keranjang
Endpoint : POST /api/v1/keranjangs/remove

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
  "message": "yahhh items yang ada di keranjang anda telah di hapus!!",
  "data": "",
  "errors": null
}
```

Response Body (Failed) :
```json
{
  "status_code": 404,
  "message": "The request was not valid",
  "errors": "keranjangs not found!"
}
```