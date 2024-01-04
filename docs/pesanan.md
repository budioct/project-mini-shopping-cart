# Pesanan API Spec

## Create Pesanan
Endpoint : POST /api/v1/pesanans/create

Request Body :
```json
{
  "customerId" : "1",
  "keranjangId" : "1"
}
```

Response Body (Success) :
```json
{
  "status_code": 200,
  "message": "Pesanan telah diterima dengan nomor register ... 7a7a99fc-debf-4615-a316-090336b65ea6",
  "data": {
    "id": 1,
    "noRegister": "7a7a99fc-debf-4615-a316-090336b65ea6",
    "tanggalPemesanan": "04/01/2024",
    "keranjang": {
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
      "keterangan": "mas minta gelas dan es batunya yaa!"
    },
    "customer": {
      "id": 1,
      "name": "Budhi Octaviansyah",
      "address": "Tangerang, Indonesia"
    },
    "createAt": "2024-01-04T19:57:42.9173792",
    "updateAt": "2024-01-04T19:57:42.9173792"
  },
  "errors": null
}
```

Response Body (Failed) :
```json
{
  "status_code": 400,
  "message": "Validation errors in your request",
  "errors": "keranjangId: must not be null, customerId: must not be null"
}
```

## Detail Pesanan
Endpoint : POST /api/v1/pesanans/detail

Request Body :
```json
{
  "noregister" : "7a7a99fc-debf-4615-a316-090336b65ea6"
}
```

Response Body (Success) :
```json
{
  "status_code": 200,
  "message": "The item exist",
  "data": {
    "id": 1,
    "noRegister": "7a7a99fc-debf-4615-a316-090336b65ea6",
    "tanggalPemesanan": "04/01/2024",
    "keranjang": {
      "id": 1,
      "items": [
        {
          "id": 4,
          "name": "mie ayam",
          "category": "makanan"
        },
        {
          "id": 5,
          "name": "mie yamin",
          "category": "makanan"
        },
        {
          "id": 10,
          "name": "aqua",
          "category": "minuman"
        }
      ],
      "jumlah_unit_pemesanan": 11,
      "keterangan": "mas minta gelas dan es batunya yaa!"
    },
    "customer": {
      "id": 1,
      "name": "Budhi Octaviansyah",
      "address": "Tangerang, Indonesia"
    },
    "createAt": "2024-01-04T19:57:42.917379",
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
  "errors": "pesanan not found"
}
```

## Update Pesanan
Endpoint : POST /api/v1/pesanans/update

Request Body :
```json
{
  "noRegister": "7a7a99fc-debf-4615-a316-090336b65ea6",
  "updateKeranjang": {
    "keranjangId": "1",
    "keterangan": "mas minta gelas dan es batunya yaa!, tambah mangkuk satu",
    "updateItem": [
      {
        "itemId": "5",
        "countItem": "2"
      },
      {
        "itemId": "4",
        "countItem": "3"
      },
      {
        "itemId": "10",
        "countItem": "7"
      }
    ]
  }
}
```

Response Body (Success) :
```json
{
  "status_code": 200,
  "message": "The item exist",
  "data": {
    "id": 1,
    "noRegister": "7a7a99fc-debf-4615-a316-090336b65ea6",
    "tanggalPemesanan": "04/01/2024",
    "keranjang": {
      "id": 2,
      "items": [
        {
          "id": 4,
          "name": "mie ayam",
          "category": "makanan"
        },
        {
          "id": 5,
          "name": "mie yamin",
          "category": "makanan"
        },
        {
          "id": 10,
          "name": "aqua",
          "category": "minuman"
        }
      ],
      "jumlah_unit_pemesanan": 12,
      "keterangan": "mas minta gelas dan es batunya yaa!, tambah mangkuk satu"
    },
    "customer": {
      "id": 1,
      "name": "Budhi Octaviansyah",
      "address": "Tangerang, Indonesia"
    },
    "createAt": "2024-01-04T19:57:42.917379",
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
  "errors": "pesanan not found, keranjang not found, keterangan: must not be blank, countItem: must not be null, itemId: must not be null"
}
```