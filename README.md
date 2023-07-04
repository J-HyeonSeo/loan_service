# 대출 서비스 실습 프로젝트

---

## 프로젝트 개요

- 대출 상품을 수신하고, 조회할 수 있습니다.
- 유저 정보를 수신하고, 조회할 수 있습니다.
- 상품은, 기관및 상품 코드로 관리됩니다.
- 중요한 데이터를 DB저장 할 때는 암호화를 통해 저장합니다.

## PRODUCT-API

- (POST) /fintech/v1/product : 외부에서 상품정보를 수신합니다.
```
(example)
Request Body :
{
    "organizationCode": "00001",
    "productCode": "001",
    "productMaximumInterest": 1.1,
    "productMinimumInterest": 9.9,
    "productName": "상품1"
}

Response Body :
{
    "responseCode": "200",
    "responseMessage": "success"
}
```

- (GET) /fintech/v1/product/{organizationCode} : 기관별 상품을 조회합니다.
```
(example)
url : /fintech/v1/product/ORGANIZATION_ONE

Response Body :
{
    "responseCode": "200",
    "responseMessage": "success"
}

Response Body :
{
    "data": [
    {
        "organizationCode": "00001",
        "productCode": "001",
        "productMaximumInterest": 1.1,
        "productMinimumInterest": 9.9,
        "productName": "상품1"
    }
    ],
    "responseCode": "200",
    "responseMessage": "success"
}
```

## USER-API

- (POST) /fintech/v1/user/information : 외부에서 유저정보를 수신합니다.

```
(example)

Resquest Body :
{
    "userIncomeAmount": 1000000,
    "userName": "JEON",
    "userRegistrationNumber": "111111-1111111"
}

Response Body :
{
    "data": {
        "userKey": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
    },
    "responseCode": "200",
    "responseMessage": "success"
}
```

- (GET) /fintech/v1/user/private-info/{userKey} : 유저 정보를 조회합니다.

```
(example)

Response Body :
{
    "data": {
        "userKey": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
        "userRegistrationNumber": "111111-1111111"
    },
    "responseCode": "200",
    "responseMessage": "success"
}
```

## 프로젝트 적용 기술

- ENUM-CONVERTER : 기관 및 품목을 ENUM으로 취급하여 휴먼에러를 줄이고, DB에 사용하기 위해 CONVERTER를 사용하였습니다.
- AOP : Controller로 향하는 모든 Request를 캐치하여, Log를 남깁니다.
- AOP : @Encrypt 어노테이션이 붙은, 칼럼을 save()시 암호화, find~()시 복호화를 수행합니다.
- Caching : 품목을 조회할 때, 캐싱을 수행하고, 해당 기관의 품목에 변동이 발생할 경우, 캐싱을 Evict시킵니다.
- Multi-Module-Project : 유지 보수를 위해, Multi-Module-Project로 구성함.
- Nginx : NGINX를 통해, 리버스 프록시와, 분산 환경을 구축하였습니다.
- Docker : Dockerfile 및 Docker-Compose.yml을 구성하여, Docker환경에서 배포가 가능하게 구성하였습니다.


