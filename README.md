# 멋쟁이 사자 X EstSoft 익스턴쉽 Backend 1~3주차

## Package

```
main 
│─────domin
│     ├───── base
│     ├───── member
│     ├───── delivery
│     ├───── item
│     ├───── itemImage
│     ├───── order
│     └───── orderitem         
│──── global
│     ├───── config
│     └───── error
│           └───── exception
│──── infra
│──── web
│     ├───── adminitem
│     ├───── itemdtl
│     ├───── login
│     ├───── main
│     └───── orderhist
└──── application.yml

```

## Tech Stack

* Spring boot, JPA
* Spring security
* JPQL
* Paging
* Lombok
* MariaDB
* thymeleaf
* validator
* ajax

## Architecture

* MVC

## How to

* 이론 적인 것을 배우고 이런 프로젝트 형식으로 제작하는 것은 처음이라서 배운 것을 잘 활용해보고자 했습니다.
* 가독성을 위해서 읽히기 쉽게 짜려고 했습니다.
* 어떻게 하면 효율적일 지에 대해서 고민했습니다.
 
 ## Learned
 
 * Spring security를 활용하는 방법에 대해서 배웠습니다.
 * 패키지 구조 설계에 대해서 배웠습니다.
 * JPA를 활용하면서 사용되는 트랜잭션에 대해서 고민해보게 되었습니다.
 * 재사용성을 위한 종속적이지 않은 로직 설계에 대해서 알게되었습니다.
 * 효율적인 Lombok 활용 방법에 대해서 알게되었습니다.
 * 재사용성 향상을 휘해 추상 클래스를 활용한 AuditorAware의 활용에 대해서 알게되었습니다.
 * Jasypt에 대해서 알게되었습니다.
