# 멋쟁이 사자 X EstSoft 익스턴쉽 Backend 1~3주차

## Package
          
```
# project-lion-web                 #project-lion-api
main                                main 
│─────domin                         │─────api
│     ├───── base                   │     ├───── health
│     ├───── member                 │     ├───── interceptor
│     ├───── delivery               │     ├───── item
│     ├───── item                   │     └───── login
│     ├───── itemImage              │─────domin
│     ├───── order                  │     ├───── base
│     └───── orderitem              │     ├───── member
│──── global                        │     ├───── delivery
│     ├───── config                 │     ├───── item
│     └───── error                  │     ├───── itemImage
│           └───── exception        │     ├───── jwt
│──── infra                         │     ├───── order
│──── web                           │     └───── orderitem        
│     ├───── adminitem              │──── global
│     ├───── itemdtl                │     ├───── config
│     ├───── login                  │     ├───── util
│     ├───── main                   │     └───── error
│     └───── orderhist              │           └───── exception
└──── application.yml               │──── infra
                                    │     └───── file
                                    │──── web
                                    │     ├───── health
                                    │     └───── kakaologin
                                    └──── application.yml

```

## Tech Stack
-project-lion-web　　　　　　　　　　　-project-lion-web
* Spring boot, JPA　　　　　　　　　　* Spring boot, JPA
* Spring security　　　　　　　　　　　* Lombok
* JPQL　　　　　　　　　　　　　　　* MariaDB
* Paging　　　　　　　　　　　　　　* H2 database
* Lombok　　　　　　　　　　　　　　* swagger
* MariaDB　　　　　　　　　　　　　　* Git ACtions/AWS EC2/CI/CD
* thymeleaf　　　　　　　　　　　　　* Feign
* validator　　　　　　　　　　　　　* Kakao Api
* ajax　　　　　　　　　　　　　　　　* Jwt token
<br>　　　　　　　　　　　　　　　　　　* Interceptor
## Architecture

* MVC

## How to

* 이론 적인 것을 배우고 이런 프로젝트 형식으로 제작하는 것은 처음이라서 배운 것을 잘 활용해보고자 했습니다.
* 가독성을 위해서 읽히기 쉽게 짜려고 했습니다.
* 어떻게 하면 효율적일 지에 대해서 고민했습니다.
* 기존에 작성했던 코드를 재사용해 보고자 했습니다.
* OCP를 생각하며 작성하였습니다.

 ## Learned
 -project-lion-web
 * Spring security를 활용하는 방법에 대해서 배웠습니다.
 * 패키지 구조 설계에 대해서 배웠습니다.
 * JPA를 활용하면서 사용되는 트랜잭션에 대해서 고민해보게 되었습니다.
 * 재사용성을 위한 종속적이지 않은 로직 설계에 대해서 알게되었습니다.
 * 효율적인 Lombok 활용 방법에 대해서 알게되었습니다.
 * 재사용성 향상을 휘해 추상 클래스를 활용한 AuditorAware의 활용에 대해서 알게되었습니다.
 * Jasypt에 대해서 알게되었습니다.

 -project-lion-api
 
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


