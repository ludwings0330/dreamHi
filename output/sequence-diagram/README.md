# 시퀀스 다이어그램

> [Notion 링크](https://ludwings.notion.site/4-96a91de3002d49d5bf22ee30f91ebd0c)

---

## 회원 관리

---

### 소셜 로그인

<img src="../images/EQD_social.png" width="700">

---

### 소셜 회원가입

<img src="../images/EQD_join.png" width="700">

---

### 로그아웃

<img src="../images/EQD_logout.png" width="700">

---

## 배우

---

### 배우 목록 조회

<img src="../images/EQD_actor_list.png" width="700">

---

### 배우 프로필

**상세 요청**

<img src="../images/EQD_actor_detail.png" width="700">

**수정 요청**

<img src="../images/EQD_actor_update.png" width="700">

---

## 공고

---

### 공고 리스트 조회

<img src="../images/EQD_announcement_list.png" width="700">

---

### 공고 기본 정보 조회

<img src="../images/EQD_announcement_basic.png" width="700">

---

### 공고 캐스팅 배역 정보

<img src="../images/EQD_announcement_casting.png" width="700">

---

### 공고 모집 현황 / 지원 현황

<img src="../images/EQD_announcement_state.png" width="700">

---

### 공고 상세 조회

<img src="../images/EQD_announcement_detail.png" width="700">

---

### 공고 등록

<img src="../images/EQD_announcement_save.png" width="700">

---

## 제작사

---

### 제작사 리스트 조회

<img src="../images/EQD_producer_list.png" width="700">

---

### 제작진 리스트 조회

<img src="../images/EQD_producer_detail.png" width="700">

---

### 제작진 추가

<img src="../images/EQD_producer_join.png" width="700">

---

## 채용 관리

---

### 지원자 목록 조회

<img src="../images/EQD_volunteer_list.png" width="700">

---

### 지원자 합격/보류/불합격 선택

---

<img src="../images/EQD_volunteer_judge.png" width="700">

---

### 다음 프로세스 진행 시 결과 통보

<img src="../images/EQD_process_next.png" width="700">

---

## 등록 & 수정

---

### 사진, 동영상 등록

<img src="../images/EQD_actor_firebase.png" width="700">

---

## 팔로우 기능

---

### 배우 팔로우/언팔로우

```mermaid
sequenceDiagram
	autonumber

	actor C as Client(제작사)
	participant B as Browser
	participant FE as FrontEnd
	participant BE as BackEnd
	participant DB as RDBMS

	C-)+B: 팔로우 버튼 클릭
	B-)+FE: 팔로우 요청
	FE-)+BE: 배우/공고/제작사 팔로우 요청
	BE->>+DB: 팔로우 정보 조회
	DB-->>BE: 팔로우 정보 반환
	alt 팔로우 O
		BE->>DB: 언팔로우로 변경
	else 팔로우 X
		BE->>DB: 팔로우로 변경
	end
	DB-->>-BE: 팔로우 결과 반환
	BE--)-FE: 팔로우 결과 응답
	FE--)-B: 팔로우 결과 출력
	B--)-C: 팔로우 결과 확인
```

## 공고, 제작사 신고

```mermaid
sequenceDiagram
	autonumber

	actor C as Client(제작사)
	participant B as Browser
	participant FE as FrontEnd
	participant BE as BackEnd
	participant DB as RDBMS
	
	C->>+B: 신고 버튼 클릭
	B->>+FE: 신고 페이지 요청
	FE->>+BE: 신고 카테고리 요청
	BE-->>-FE: 신고 카테고리 전달
	FE-->>-B: 신고 페이지 전달
	B-->>-C: 내용 작성 요청
	C-)B: 내용 작성 완료
	B-)FE: 신고 요청
	FE-)BE: 신고 요청(공고, 제작사)
	BE->>DB: 신고 이력 추가
	DB-->>BE: 신고 결과 반환
	BE--xFE: 신고 결과 응답
```

# 내 정보 관리

## 내 이력서 조회 : 기본 정보 / 필모그래피 / 배우프로필사진+영상 / 팔로워 수

- 팔로워 수 확인
- 기본 정보 조회
- 필모그래피 정보 조회
- 프로필 미디어 조회

```mermaid
sequenceDiagram
	autonumber

	actor C as Client(제작사)
	participant B as Browser
	participant FE as FrontEnd
	participant FB as FireBase
	participant BE as BackEnd
	participant DB as RDBMS
	
	C-)+B: 내 이력서 클릭
	B-)FE: 내 이력서 조회 요청

	FE-)+BE: 기본 정보 조회
	BE->>+DB: 유저 정보 조회
	note right of BE: 유저, 배우 기본 정보
	DB-->>-BE: 기본 정보 조회 결과
	BE--)-FE: 기본 정보
	FE--)B: 기본 정보

	FE-)+BE: 팔로워수 조회
	BE->>+DB: 팔로워 수 조회
	DB-->>-BE: 팔로워 수
	BE--)-FE: 팔로워 수
	FE--)B: 팔로워 수

	FE-)+BE: 필모그래피 정보 조회

	BE->>+DB: 필모그래피 목록 조회
	DB-->>-BE: 필모그래미 목록
	BE--)-FE: 필모그래피 목록
	FE--)B: 필모그래피 정보
	B-)FB: 필모그래피 이미지 요청
	FB--)B: 사진

	FE-)BE: 프로필 미디어 조회(사진, 동영상)
	BE--)FE: 프로필 미디어 목록(사진, 동영상)
	FE--)B: 프로필 미디어 목록 반환
	B-)FB: 프로필 사진, 동영상 요청
	FB--)B: 사진, 동영상
	B--)-C: 내 이력서 페이지

	
```

## 이력서 수정

- 기본정보 DB 에 업데이트
- 파일은 FireBase 저장 - 저장 URL 을 DB에 저장

```mermaid
sequenceDiagram
	autonumber

	actor C as Client(제작사)
	participant B as Browser
	participant FE as FrontEnd
	participant FB as FireBase
	participant BE as BackEnd
	participant DB as RDBMS
	C-)+B: 사진, 영상 추가/삭제	
	B-)+FE: 사진, 영상 추가/삭제 요청
	FE-)FB: 사진, 영상 저장/삭제
	FB--)FE: 저장 URL
	
	C-)+B: 이력서 수정 완료

	B-)+FE: 이력서 수정 요청

	FE-)+BE: 기본정보 수정 요청
	BE->>+DB: 기본정보 수정
	DB-->>-BE: 수정 결과
	BE--)-FE: OK

	FE-)+BE: 필모그래피 목록 수정 요청
	BE->>+DB: 필포그래피 정보 수정 요청
	DB-->>-BE: 수정 결과
	BE--)-FE: OK
	
	FE-)+BE: 사진, 영상 수정 요청
	BE->>+DB: 사진, 영상 정보 수정 요청
	DB-->>-BE: 수정 결과
	BE--)-FE: OK

	FE--)-B: 이력서 페이지 

	B-)-C: 이력서 확인
```

---

## 알림 및 메일 기능

### 메일 보내기

**사용되는 로직**

- 다음 프로세스 진행 시 합격 지원자 대상으로 메일 발송
- 이메일 인증
- 비밀번호 찾기

```mermaid
sequenceDiagram
	autonumber
	actor C as Client
	participant B as Browser
	participant FE as FrontEnd
	participant BE as BackEnd(WAS Server)
	participant DB as RDBMS
  participant M as SMTP
	C->>+B:이메일 발송이 필요한 로직 
	B->>+FE: 이메일 발송이 필요한 로직 호출
	note right of B: MEM-SIGN-002, MEM-LOST-001, CS-EMAIL-005
	note right of B: CS-EMAIL-007, CS-EMAIL-009
	FE-)+BE: 이메일 발송이 필요한 로직 호출
	BE->>+DB: sender, receiver email 조회
	DB--)-BE: return email
	BE->>+M: request send email with content
	M--)-C: send email
	BE--)-FE: 로직 수행 결과 반환
	FE--)-B: 로직 수행 결과 출력
	B--)-C: 로직 수행 결과 확인
  
```

### 알림 보내기

**알림 필요 로직**

- 오디션 합격/불합격 알림 (CS-PUSH-009)
- 팔로워 추가 (CS-PUSH-008) - ⏬
- 팔로워에게 공고 등록 알림 (CS-PUSH-010) - ⏬
- 캐스팅 제의 알림 (CS-PUSH-011) - ⏬
- 오디션 제의 알림 (CS-PUSH-012) - ⏬

```mermaid
sequenceDiagram
	autonumber
	actor C as Client
	participant B as Browser
	participant FE as FrontEnd
	participant BE as BackEnd(WAS Server)
	participant RD as Redis
	participant DB as RDBMS
	C->>+B:알림 발송이 필요한 로직 
	B->>+FE: 알림 발송이 필요한 로직 호출
	note right of B: CS-PUSH-008~012
	FE-)+BE: 알림 발송이 필요한 로직 호출
	loop Do Logic 
		BE->>+DB: request에 대한 로직 수행
		DB--)-BE: 로직에 대한 response
	end
	BE--)-FE: 로직 수행 결과 반환
	FE--)-B: 로직 수행 결과 출력
	B--)-C: 로직 수행 결과 확인
	alt SSE 수행
		BE->>+DB: sender, receiver 정보 조회
		DB--)-BE: return sender, receiver 정보
		BE->>+RD: 알림 메세지 저장
		RD--)-BE: 저장 성공
		BE--)FE: ServerSentEvent 발생
		note left of BE: 읽지 않을 알림 있다고 알림
		loop EventSource: Event 감시
			FE--)FE: EventSource에서 SSE Event 감시
		end
		FE--)B: 읽지 않은 알림 표시 출력
		B--)C: 미수신 알림 확인
	end	
	

  
```

---

## 화상 오디션

```mermaid
sequenceDiagram
	autonumber
	actor P as PD	
	participant BE as BackEnd (Signaling)
	actor A as Actor
	participant V as OpenVidu

	P-)+BE: 화상 오디션 대상자 조회
	BE--)-P: return TargetActor

	P-)+BE: 정해진 오디션 시각에 화상 오디션 콜 요청
	note right of P: 미디어 전송 설정 정보 전달 (Offer SDP)
	
	BE-)+A: 화상 오디션 콜 요청 from PD
	note right of BE : Offer SDP

	A--)-BE: 배우가 화상 오디션 콜 수락
	note left of A: Answer SDP

	BE--)P: 배우의 화상 오디션 콜 수락 전달
	note left of BE: Answer SDP

	P-)BE: PD의 Public IP 등 연결에 필요한 정보 전달
	note right of P: PD의 ICE Candidate 전달

	BE-)A: PD의 Public IP 등 연결에 필요한 정보 전달
	note right of BE: PD의 ICE Candidate 전달

	A--)BE: Actor의 Public IP 등 연결에 필요한 정보 전달
	note left of A: Actor의 ICE Candidate 전달

	BE--)P: Actor의 Public IP 등 연결에 필요한 정보 전달
	note left of BE: Actor의 ICE Candidate 전달

	note over P, V: 화상 오디션 진행

	P-)V: 화상 오디션 종료
	V--)A: 화상 오디션 종료
```