# :elephant: 아프리카 코끼리

# 🌟 DreamHi - 배우 오디션 플랫폼

---

[<img src="./output/images/dreamhi-banner.png" height="140">](https://dreamhi.p-e.kr)  
[DreamHi 페이지로 이동](https://dreamhi.p-e.kr)

## <img src="./output/images/elephant.png" height="40"> 팀소개 - 아프리카 코끼리
> 삼성 청년 SW 아카데미 8기 2학기 공통 프로젝트팀

---


:crown: Team Leader : 배창민 <br>
:muscle: FrontEnd Leader : 정지은 <br>
:clown_face: BackEnd : 이다운 <br>
:clown_face: BackEnd : 황준현 <br>
:smiley_cat: FrontEnd : 이여민 <br>
:smiley_cat: FrontEnd : 정효상 <br>

## 📅 프로젝트 기간
> 2023.01.09 (월) ~ 2023.02.17(금)
---

- 2023.01.05(목) ~ 2023.01.09(월) 
  - 아이디어 선정
- 2023.01.10(화) ~ 2023.01.13(금)
  - [기능명세서 작성](output/기능명세서/README.md)
  - [와이어프레임 제작](output/prototype/README.md)
- 2023.01.16(월) ~ 2023.01.27(금)
  - [프로토타입 제작](output/prototype/README.md)
  - [ERD 작성](output/erd/README.md)
  - [시퀀스 다이어그램 작성](output/sequence-diagram/README.md)
- 2023.01.30(월) ~ 2023.02.10(금)
  - [API 명세서 작성](output/api/README.md)
  - [배포 환경 구성](output/architecture/README.md)
  - [BackEnd 구현](#-기술-스택) 
  - [FrontEnd 구현](#-기술-스택)
  - OpenVidu 적용
- 2023.02.13(월) ~ 2023.02.15(금)
  - [FrontEnd 구현](#-기술-스택)
  - [E2E 테스트 및 버그 수정](https://docs.google.com/spreadsheets/d/16IVVm1V1fmuVT7mTiPPC1_Gx9Uwhbqojm7hqa-oafSk/edit#gid=0)
  - 산출물 문서화

## 📝 DreamHi - 배경

---

우리가 극장에서 흔히 볼 수 있는 영화의 경우 막대한 자본을 이용하여 주연 배우를 캐스팅하고, 인맥을 동원한 오디션으로 단역을 뽑으면 되기 때문에 배우를 캐스팅하는 작업에서 별다른 어려움이 없어보일 수 있다.

하지만, 대부분의 저예산 영화 또는 독립 영화는 배우를 모집하는 데 제작자(제작팀)의 상당한 에너지 소모를 필요로 한다. 오디션을 통한 캐스팅 프로세스를 **아직도 이메일과 오프라인 면접으로 진행**하고 있기 때문이다.

게다가, 배우의 상황도 녹록치 않다. 영화 오디션 공고가 **각종 커뮤니티에 중구난방으로 게시**되고 있기 때문에, 단순히 오디션 공고를 찾는 작업 조차도 그리 간단치 못하기 때문이다.

결과적으로 우리는 소규모 제작팀과 배우를 위한 영화 오디션 플랫폼이 부재하다는 것을 문제의 원인으로 꼽았다.

## 📝 DreamHi - 개요

---

영화 오디션 플랫폼을 통해

> `제작자`가 자신의 작품에 어울리는 배우를 체계적으로, 간편하게 찾을 수 있고,
>
>`배우`가 더 많은 오디션에 간편하게 지원하여 기회를 얻도록  

돕는다.

## 👍 Benefit

---

`제작자(제작팀)`

- 배우 모집 공고를 쉽게 등록하고, 관리할 수 있다.
- 오디션 프로세스를 체계적으로 진행할 수 있다.
- 화상 오디션을 이용하면 짧은 시간 동안 더 많은 지원자를 만날 수 있다.

`배우`

- 배우 모집 공고를 한 곳에서 확인하고, 지원할 수 있다.
- 자기소개를 위한 이력서, 자료 등을 간편하게 관리할 수 있다.

## 🏗️ 시스템 아키텍처

---

![system architecture](output/images/architecture.PNG)

## ✅ 기술 스택

---

### Front-End

- HTML/CSS/JavaScript
- React 18.2.0
- Recoil 0.7.6
- React-Bootstrap 2.7.0
- Tailwind 3.0.2
- MaterialUI 4.12.4
- OpenVidu 2.25.0

### Back-End

- JAVA 11
- Spring Boot (Gradle groovy) 2.7.7
- Spring Security 
- Spring Data JPA
- QueryDsl
- MySQL 8.0.31

### Dev-ops
- Docker
- Jenkins
- NGINX

## ✅ 협업 툴

---

- Jira
- Gitlab
- Mattermost
- Notion

## 📝 프로젝트 파일 구조

---

### back-end

```
└── Dream-Hi
    └── src
        └── main
            ├── java
            │   └── com
            │       └── elephant
            │           └── dreamhi
            │               ├── configuration
            │               │   └── converter
            │               ├── controller
            │               ├── exception
            │               ├── model
            │               │   ├── dto
            │               │   ├── entity
            │               │   └── statics
            │               ├── repository
            │               ├── security
            │               │   ├── jwt
            │               │   └── oauth
            │               │       └── provider
            │               ├── service
            │               └── utils
            └── resources
```

### front-end

```
|-- components
|   |-- Actor
|   |   |-- filmo
|   |   |-- info
|   |   |-- photo
|   |   `-- video
|   |-- Announcement
|   |-- Audition
|   |-- Calendar
|   |-- Casting
|   |-- Collection
|   |-- Common
|   |   |-- CommonComponent
|   |   `-- MainLayout
|   |-- Live
|   |-- Main
|   `-- Maker
|       |-- AnnouncementList
|       |-- Filmo
|       |-- Info
|       `-- MakerMembers
|-- constants
|-- imageup
|-- img
|   `-- Icons
|-- lib
|   `-- styles
|-- pages
|   `-- Login
|-- recoil
|   |-- actor
|   |-- announcement
|   |-- book
|   |-- maker
|   |-- process
|   |-- user
|   `-- volunteer
|-- service
|   `-- audition
|-- user
|   |-- login
|   `-- oauth2
`-- util

```

## 📝 프로젝트 산출물

---

- [회의록](output/meeting-log)
- [컨벤션](output/docs/README.md)
- [기능 명세서](output/기능명세서/README.md)
- [프로토타입](output/prototype/README.md)
- [시퀀스 다이어그램](output/sequence-diagram/README.md)
- [API](output/api/README.md)
- [ERD](output/erd/README.md)
- [시스템 아키텍처](output/architecture/README.md)
- [E2E 테스트 케이스](https://docs.google.com/spreadsheets/d/16IVVm1V1fmuVT7mTiPPC1_Gx9Uwhbqojm7hqa-oafSk/edit#gid=0)
- [발표 자료](output/presentation/README.md)

## 🔥 트러블 슈팅

---

1. 낮은 조회 성능 1 : JPA를 사용할 때 발생하는 N + 1 문제 해결
   - Fetch Join
   - Batch Size, Bulk Select
2. 낮은 조회 성능 2 : 페이징 처리 시 Total Count로 인한 성능 저하
   - 사실 해결하지 못함, 두 번째 프로젝트를 기대하시라!
   - 해결책 1. 페이지 건너뛰는 개념을 없애고, 무한 스크롤 방식으로 변경 - 커서 기반 페이지네이션
   - 해결책 2. Total Count를 읽지 않고, 페이지 크기를 고정 - 커버링 인덱스 페이지네이션
   - 해결책 3. Total Count를 처음에만 계산하고, 캐싱하여 계산하지 않음 - 커버링 인덱스 페이지네이션
3. JWT를 백엔드에서 프론트엔드로 전달할 때, Refresh Token에 대한 정책
   - Access Token만 프론트엔드로 전달하는 것을 선택
   - 혹여나 Access Token이 탈취된 사실을 알게 되었을 때, DB에서 Refresh Token을 삭제하는 식으로 더 빠른 대응이 가능하기 때문
4. 인증과 인가의 모호함
   - 게스트 유저를 서버에서 강제로 로그인 시킴으로서 모든 유저는 사실상 항상 인증된 상태
   - 이러한 문제를 해결하기 위해 인가를 위해 사용하는 `@PreAuthorize`를 인증에도 함께 활용
   - 최종적으로는 인증과 인가를 담당하는 빈을 등록하여 책임을 위임하였으나 다소 아쉬움이 있음
5. 오픈비두 서버와 기존 서버의 충돌
   - 기존의 NGINX가 80 포트를 사용 중
   - 오픈비두 관련 이미지들을 컨테이너로 올릴 때 NGINX 컨테이너가 포함되어 있었고, 해당 컨테이너도 80 포트를 사용
   - 80 포트의 충돌로 인해 제대로 작동하지 않음
   - 기존에 사용하던 NGINX를 삭제하고 새로운 NGINX 컨테이너에 오픈비두 서버, 백엔드 서버, 프론트엔드 관련 설정을 추가하여 해결
6. 설계가 매우 복잡했다.
   - 이유 1. 확장성을 지나치게 고려했다.
   - 이유 2. 프론트 컴포넌트 통합, API 통합 등 너무 많은 것을 한 곳에 담으려고 했다.
7. 엔티티 연관관계!
   - 양방향 연관관계는 DTO 매핑을 사용하면 사실상 필요 없는 수준이다.
   - 엔티티로 조회한 후 DTO 매핑을 하려면 양방향 연관관계가 필요하다.
   - 필요할 때 양방향 연관관계를 적용해도 늦지 않는다. 단방향 연관관계로 시작하자.
8. 각 레이어의 역할을 명확하게!
   - 서비스에서 다른 서비스를 호출하는 게 타당한가? 상황에 따라 주관적으로 판단해야 하는걸까?
   - 예외는 어디서 수행해야 했는가? 인가 과정에서 DB에 접근하고 던져지는 예외는 서비스의 로직이 아니였을까?