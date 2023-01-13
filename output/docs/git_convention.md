# <div style='background-color: #f5f0ff'> :pushpin: Team Rules</div>

<br>

## <div style='background-color: #ddffe4'>깃 컨벤션</div>

<br>

### <div style='background-color: #f1f8ff'>커밋 컨벤션</div>

<br>

1.  모든 커밋은 다음의 포맷을 지켜야 한다. 이때, 본문과 footer는 생략할 수 있다.

```bash
Type: Subject

[Body]

[Footer]
```

2.  Type은 다음의 종류를 확인하여 작성한다.

| Type | Objective |
| --- | --- |
| :sparkles: Feat: | ✨ 새로운 기능 추가 |
| :bug: Fix: | 🐛 버그 수정, 로직 수정 |
| :poop: Fix: | 💩 똥 싼 코드 |
| :lipstick: Design: | 💄 CSS 등 사용자 UI 디자인 수정 |
| :hammer: Refactor: | 🔨 코드 리팩토링 |
| :art: Style: | 🎨 코드 포맷, 세미 콜론 누락 수정 |
| :memo: Comment:  | 📝 주석의 추가 또는 수정 |
| :umbrella_with_rain_drops: Test: | ☔ 테스트 코드 추가 :document |
| :rocket: Chore:  | 🚀 빌드 설정 수정, 패키지 매니저 수정 등 코드가 아닌 설정 변경 |
| :books: Docs: | 📚 문서 작성 또는 수정 |
| :tractor: Rename: | 🚜 파일 또는 폴더명을 수정하거나, 파일 또는 폴더를 이동시킨 경우 |
| :fire: Remove: | 🔥 파일을 삭제한 경우 |
|  :tada: Init: | 🎉 프로젝트 초기 설정 |
| :card_file_box: Resource: | 🗃️ DB, Image 등의 추가 또는 삭제  |

3.  Subject는 무슨 작업을 수행했는지 기입합니다. 영어로 하지 않아도 괜찮습니다.
    - 영어로 작성 할 경우에는 명령문 형태로 작성하며, 첫 글자는 대문자로 작성합니다.  
      ⇒  Add, Fix, Modify …

4.  Body는 왜 그 작업을 수행했는지 등의 부연 설명을 기입합니다. 영어로 하지 않아도 괜찮습니다.
    - 어떻게 구현했는지는 굳이 부연하지 않습니다. 어떻게 구현했는지가 궁금할 때는 코드를 읽으면 되기 때문입니다.
5.  Footer는 issue tracker ID를 명시하는 용도로 사용합니다. 저희 프로젝트에서는 사용하지 않겠습니다.

<br>

### <div style='background-color: #f1f8ff'>PR 컨벤션</div>

<br>

1. Branch 생성
- 이름은 `type/분야/Subject`와 같이 생성합니다. 예를 들어, `feat/be/validate-join-user`입니다.
- 기능 단위로 Branch를 만들어서 작업한다.

    <aside>
    💡 Commit도 기능 단위로, Branch로 기능 단위로?

  기능도 ‘대분류 - 중분류 - 소분류’로 구분될 수 있고, 대분류와 중분류에 따라서 Branch를 생성하고, 소분류에 해당되는 기능을 구현하여 Branch 내에서 Commit을 수행합니다.

  > 대분류 : 회원 가입  
  중분류 : 유효성 검사  ⇒  feat/be/validate-join-user  
  소분류 : 아이디 유효성 검사  ⇒  ✨ Feat: 아이디 유효성 검사 구현
  >
    </aside>

2.  PR 제목은 다음과 같이 작성합니다.

```bash
Type: Subject

✨ Feat: 회원 가입 시 유효성 검사
```

3.  PR 내용은 다음과 같이 작성합니다.

```bash
## Summary
해당 PR의 목적을 간단히 적습니다.

---

## Describe
1. 변경 사항을 상세히 적습니다.
2. 변경 사항은 Commit과 대응될 가능성이 높습니다.

---

## What you know
리뷰어가 PR을 이해하기 위해 사전에 알아야 하는 것을 적습니다.
```

<br>

---

<br>

## <div style='background-color: #ddffe4'>git branch 전략 - git flow 채택</div>

<br>

### <div style='background-color: #f1f8ff'>:one: gitflow 5가지 브랜치</div>

<br>

1.  master
    - 기준이 되는 브랜치로 제품을 배포하는 브랜치
2.  develop
    - 개발 브랜치로 개발자들이 이 브랜치를 기준으로 각자 작업한 기능들을 Merge
3.  feature
    - 단위 기능을 개발하는 브랜치로 기능 개발이 완료되면 develop 브랜치에 Merge
4.  release
    - 배포를 위해 master 브랜치로 보내기전, 먼저 QA(품질검사)를 하기위한 브랜치
5.  hotfix
    - master 브랜치로 배포를 했는데 버그가 생겼을 때 긴급 수정을 위한 브랜치

<br>

### <div style='background-color: #f1f8ff'>:two: gitflow 과정</div>

<br>


```jsx
1. master 브랜치에서 develop 브랜치 분기
2. 개발자들은 develop 브랜치에 자유롭게 커밋
3. 기능 구현이 있는 경우 develop 브랜치에서 feature-* 브랜치를 분기
4. 배포를 준비하기 위해 develop 브랜치에서 release-* 브랜치를 분기
5. 테스트를 진행하면서 발생하는 버그 수정은 release-* 브랜치에 직접 반영
6. 테스트가 완료되면 release 브랜치를 master와 develop에 merge

** readme 수정의 경우 docs 브랜치 사용
```

![GitFlow-Chart](https://s3.us-west-2.amazonaws.com/secure.notion-static.com/c80eaacd-055e-439f-9e49-130e5c6df61f/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230110%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230110T074404Z&X-Amz-Expires=86400&X-Amz-Signature=201a3c2e552fd0c47e367159c18c34f7c24d70034e0313e7c9e98cb092ec3442&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject)

### <div style='background-color: #f1f8ff'>참고</div>

<br>


[Git branch 전략(Git-Flow, Github-Flow, Gitlab-Flow)](https://velog.io/@kw2577/Git-branch-%EC%A0%84%EB%9E%B5)

<br>

---

<br>