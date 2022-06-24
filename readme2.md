# 멋쟁이 사자 X EstSoft 익스턴쉽 Backend 4~5주차

## Package

```
main 
│─────api
│     ├───── health
│     ├───── interceptor
│     ├───── item
│     └───── login
│─────domin
│     ├───── base
│     ├───── member
│     ├───── delivery
│     ├───── item
│     ├───── itemImage
│     ├───── jwt
│     ├───── order
│     └───── orderitem         
│──── global
│     ├───── config
│     ├───── util
│     └───── error
│           └───── exception
│──── infra
│     └───── file
│──── web
│     ├───── health
│     └───── kakaologin
└──── application.yml

```

## Tech Stack

* Spring boot, JPA
* Lombok
* MariaDB
* H2 database
* swagger
* Git ACtions/AWS EC2/CI/CD
* Feign
* Kakao Api
* Jwt token
* Interceptor

## Architecture

* MVC

## How to

* 이론 적인 부분을 공부해서 이해하고 활용하려 했습니다.
* 놓치는 부분 없이 코드를 작성하려고 했습니다.
* OCP를 생각하며 작성하였습니다.
* 가독성을 향상시키려고 고민하였습니다.
* 기존에 작성했던 코드를 재사용해 보고자 했습니다.
 
 ## Learned
 
 * Feign을 활용한 외부 api 사용에 대해서 배울 수 있었습니다.
 * Interceptor를 활용한 데이터 전처리에 대해서 배웠습니다.
 * 코드 리뷰를 통해 resolver를 활용한 공통 파라미터 처리에 대해서 배웠습니다.
 * Jwt token을 활용한 인증 방식에 대해서 배웠습니다.
 * 팩토리 패턴에 대해서 배웠습니다.
 * swagger를 활용해서 api를 문서화하는 방법에 대해 알게되었습니다.
 * Git/AWS를 이용한 CI/CD 구축에 대해서 알게되었습니다.
 * 효율적인 api 작성에 대해서 고민해보았습니다.
 * 꼼꼼한 예외처리에 대해서 고민하게 되었습니다.
 * 앞에서 작성했던 코드를 재사용하면서 설계의 중요성에 대해서 알게되었습니다.
