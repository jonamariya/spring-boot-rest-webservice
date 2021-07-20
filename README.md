# spring-boot-rest-webservice
RESTful Web Services with Spring and Spring Boot


Example Requests

GET All users http://localhost:8080/users
```json
[{"id":10001,"firstName":"John","lastName":"Doe","links":[{"rel":"self","href":"http://localhost:8080/users/10001"}]},{"id":10002,"firstName":"Jack","lastName":"New","links":[{"rel":"self","href":"http://localhost:8080/users/10002"}]}]
```

GET User http://localhost:8080/users/10001
```json
{
  "id": 10001,
  "firstName": "John",
  "lastName": "Doe",
  "_links": {
  "all-accounts": {
    "href": "http://localhost:8080/users/10001/accounts"
    }
  }
}
```

Get User's all Accounts http://localhost:8080/users/10001/accounts

```json
[
  {
  "accountNumber": 11001,
  "accountName": "SGSavings",
  "accountType": "SAVINGS",
  "currency": "AUD",
  "amount": 48.01,
  "links": [
    {
      "rel": "self",
      "href": "http://localhost:8080/users/10001/accounts/11001"
    }
  ]
  }
]
```

Get User's Account by number http://localhost:8080/users/10001/accounts/11001
```json
{
  "accountNumber": 11001,
  "accountName": "SGSavings",
  "accountType": "SAVINGS",
  "currency": "AUD",
  "amount": 48.01,
  "_links": {
    "all-accounts": {
      "href": "http://localhost:8080/users/10001/accounts"
    },
    "all-transactions": {
      "href": "http://localhost:8080/users/10001/accounts/11001/transactions"
    }
  }
}
```

Get Account's transactions Get User's Account by number http://localhost:8080/users/10001/accounts/11001
```json
[
  {
    "id": 12001,
    "type": "DEBIT",
    "amount": 47.01,
    "description": "food expense",
    "createdDate": "2021-07-19T14:30:00.000+00:00",
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/users/10001/accounts/11001"
      }
  ]
  },
  {
    "id": 12002,
    "type": "CREDIT",
    "amount": 1,
    "description": "food expense",
    "createdDate": "2021-07-19T14:30:00.000+00:00",
    "links": [
      {
        "rel": "self",
        "href": "http://localhost:8080/users/10001/accounts/11001"
      }
    ]
  }
]
```
