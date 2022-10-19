# 멋쟁이 사자  쇼핑몰 프로젝트

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
-project-lion-web　　　　　　　　　　　-project-lion-api
* Spring boot, JPA　　　　　　　　　　* Spring boot, JPA
* Spring security　　　　　　　　　　 * Lombok
* JPQL　　　　　　　　　　　　　　　   * MariaDB
* Paging　　　　　　　　　　　　　　   * H2 database
* Lombok　　　　　　　　　　　　　　   * swagger
* MariaDB　　　　　　　　　　　　　　  * Git ACtions/AWS EC2/CI/CD
* thymeleaf　　　　　　　　　　　　　  * Feign
* validator　　　　　　　　　　　　　  * Kakao Api
* ajax　　　　　　　　　　　　　　　　  * Jwt token
<br>　　　　　　　　　　　　　　　　　　* Interceptor
## Architecture

* MVC

## How to

* 이론 적인 것을 배우고 이런 프로젝트 형식으로 제작하는 것은 처음이라서 배운 것을 잘 활용해보고자 했습니다.
* 가독성을 위해서 읽히기 쉽게 짜려고 했습니다.
* 어떻게 하면 효율적일 지에 대해서 고민했습니다.
* 아키텍쳐 구조를 생각하며 코드 작성을 고민을 했습니다.
* OOP를 생각하며 작성하였습니다.

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

## 화면

- 메인 페이지
<img width="400" alt="image" src="https://user-images.githubusercontent.com/62021242/196667942-e8bdc265-a6d7-40f0-b53e-fd6927f05732.png">

- 구매 이력
<img width="400" alt="image" src="https://user-images.githubusercontent.com/62021242/196670662-e8bb7721-69b4-49c7-9316-18e8c773b976.png">
<img width="400" alt="image" src="https://user-images.githubusercontent.com/62021242/196670782-f138fc99-30eb-424e-8b6b-ca42cf439b18.png">

- 상품 페이지
<img width="500" alt="image" src="https://user-images.githubusercontent.com/62021242/196670952-54cdd883-0460-449e-857b-f6a34b545691.png">

- 상품 등록
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/62021242/196671492-a1a1fdc5-c505-4428-8407-7b1cae49c67d.png">

- 카카오 API 회원 가입

<img width="695" alt="image" src="https://user-images.githubusercontent.com/62021242/196672223-56bc88ca-fa79-4fad-a702-5e5e408fe6e5.png"><img width="1000" alt="image" src="https://user-images.githubusercontent.com/62021242/196672485-4b0b33d9-f13b-48a3-a94a-e720046a5cde.png">

- Postman JWF token 결과

<img width="569" alt="image" src="https://user-images.githubusercontent.com/62021242/196672710-0811c248-2831-4b76-aa94-666385fcb2c3.png">

- 상품 조회 API

<img width="698" alt="image" src="https://user-images.githubusercontent.com/62021242/196673890-94c85864-cae5-48ba-8822-b534354f0f3c.png">

- 상품 수정 API

<img width="709" alt="image" src="https://user-images.githubusercontent.com/62021242/196674318-fec94357-c51c-4b76-803c-f96c6c5e41bf.png">


- Github Actions CI/CD

<img width="1523" alt="image" src="https://user-images.githubusercontent.com/62021242/196673338-96a07629-6d05-48fd-9aa6-c45a476666bc.png">


