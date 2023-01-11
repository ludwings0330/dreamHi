# <div style='background-color: #f5f0ff'>:elephant: 아프리카 코끼리</div>

<br>

:crown: Team Leader : 배창민 <br>
:muscle: FrontEnd Leader : 정지은 <br>
:clown_face: BackEnd : 이다운 <br>
:clown_face: BackEnd : 황준현 <br>
:smiley_cat: FrontEnd : 이여민 <br>
:smiley_cat: FrontEnd : 정효상 <br>

<br><br>

# <div style='background-color: #f5f0ff'>:pushpin: Team Rules</div>

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
- 이름은 `Type/Subject`와 같이 생성합니다. 예를 들어, `Feat/Validate-Join-User`입니다.
- 기능 단위로 Branch를 만들어서 작업한다.
    
    <aside>
    💡 Commit도 기능 단위로, Branch로 기능 단위로?
    
    기능도 ‘대분류 - 중분류 - 소분류’로 구분될 수 있고, 대분류와 중분류에 따라서 Branch를 생성하고, 소분류에 해당되는 기능을 구현하여 Branch 내에서 Commit을 수행합니다.
    
    > 대분류 : 회원 가입
    중분류 : 유효성 검사  ⇒  validate-join-user
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

## <div style='background-color: #ddffe4'>자바 코딩 컨벤션</div>

<br>

### <div style='background-color: #f1f8ff'>:one: 개행, 들여쓰기, 공백 (IntelliJ)</div>

<br>


[Java-Coding-Convention.xml](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9a78ba6a-34fd-4a68-bc84-7a329e693f7b/Java-Coding-Convention.xml)

<br>

### <div style='background-color: #f1f8ff'>:two: 이름 규칙</div>

<br>


- `패키지` : 모두 소문자
- `클래스` : UpperCamelCase
- `비상수 필드` : lowerCamelCase
- `상수` : UPPER_SNAKE_CASE
- `변수` : lowerCamelCase
    - 임시 변수는 되도록 사용하지 않는다.
    - 논리적인 목적을 가진 변수를 사용하기 보다는 메서드를 분리하여 작성한다.
    - 변수의 목적을 변수명에 그대로 표현한다.
- `메서드` : lowerCamelCase
    - 메서드의 기능을 변수명에 그대로 표현한다.
- `SQL - 테이블` : lower_snake_case
    - 최대한 집합 명사를 사용한다. 예를 들어, employees보다는 staff를 사용한다.
    - 피할 수 없는 경우 복수 명사를 사용한다.
- `SQL - 속성` : lower_snake_case
    - 단수 명사를 사용한다.
    - 테이블명과 동일한 속성 명을 사용하지 않는다.

### <div style='background-color: #f1f8ff'>:three: 어노테이션 규칙</div>

<br>


- 타입 어노테이션은 타입의 바로 앞에 위치시킨다.
    
    ```java
    private final @Nullable String name;
    
    private @Nullable Point point;
    ```
    
- 클래스, 필드, 생성자, 메서드 어노테이션은 다음과 같이 한 줄에 하나씩 기입한다.
    
    ```java
    @ClassAnotation1
    public class Wolf {
        @Autowired
        @Mock
        private Service service;

        @Override
        public int hashCode() {
                ...
        }
    }
    ```
    
<br>

### <div style='background-color: #f1f8ff'>:four: 예외 규칙</div>

<br>


- 메서드에서 발생할 수 있는 예외는 구체적으로 적는다. 이때, 발생 가능한 순서대로 적는다.
    
    ```java
    public void boo() throws Exception1, Exception2, Exceptions3 {
        if (validate()) {
                throw new Exception1();
        }

        optional.OrElseThrow(() -> new Exception2());
        foo();
    }
    
    public void foo() throws Exception3 {
        throw new Exception3();
    }
    ```
    
- 예외를 던질 때는 예외가 발생한 이유를 메세지로 알려라.
    
    ```java
    throw new RuntimeException("이 코드는 실행될 수 없습니다.");
    ```
    
- 예외를 처리할 때는 RuntimeException으로 한 번에 처리한다. 다만, 예외별로 다른 로직을 적용해야 하는 경우에는 개별적으로 처리할 수 있다.
- 외부에서 내부로 데이터를 받아올 때, 경계에서 검증하고 예외를 처리한다.
- Optional을 반환하는 것을 고려한다.

<br>

### <div style='background-color: #f1f8ff'>:five: JavaDoc으로 설명한다.</div>

<br> 


```java
/**
 * 이 메서드는 이럴 때 사용하는 메서드입니다.
 * @param var1 이 매개변수는 이런 조건을 만족시켜야 합니다. 없으면 적지 않아도 됩니다.
 * @param var2 이 매개변수는 이런 조건을 만족시켜야 합니다.
 * @throws Exception1 이런 상황이면 예외가 발생합니다. 이건 꼭 적어야 합니다.
 * @throws Exception2 이런 상황에도 예외가 발생합니다.
 * @return 반환값이 있다면 무엇을 반환하는지 적습니다.
 */
public void foo(String var1, int var2) throws Exception1, Exception2 {
    ...
}
```

<br>

### <div style='background-color: #f1f8ff'>:six: 변수 선언 및 초기화 규칙</div>

<br>

- 변수는 한 줄에 하나씩만 선언한다.
- 리터럴 사용 가능한 경우 리터럴을 반드시 사용한다.
- 배열은 Type[] 형태로 선언한다.

<br>

---

<br>

## <div style='background-color: #ddffe4'>Front-End **코딩 컨벤션**</div>

<br>

 

### <div style='background-color: #f1f8ff'>:one: 들여쓰기</div>

<br>

- space와 tab을 섞어서 사용하지 않는다.


### <div style='background-color: #f1f8ff'>:two: 문장의 종료</div>

<br>

- 한 줄에 하나의 문장만 허용하며, 문장 종료 시에는 반드시 세미콜론 (;) 을 사용한다.

### <div style='background-color: #f1f8ff'>:three: 명명 규칙</div>

<br>

- 예약어를 사용하지 않는다.
- `상수` : UPPER_SNAKE_CASE
- `생성자` : UpperCamelCase
- `변수` : lowerCamelCase
- `함수`: lowerCamelCase
- `지역변수 or private 변수` : _lowerCamelCase
- 모든 변수는 카멜 케이스로 작성한다.
- `컴포넌트` : 해당 컴포넌트 기능 + 역할(동사)

```jsx
Compenents - BoardDetail
             BoardDelete
```

### <div style='background-color: #f1f8ff'>:four: 전역 변수</div>

<br>

- 전역 변수를 사용하지 않는다.
- 암묵적 전역 변수를 사용하지 않는다.

```jsx
// Bad
function sum(x, y) {
  result = x + y;
  return result;
}

// Bad
function foo() {
  let a = b = 0; // let a = (b = 0);와 같다. b가 암묵적 전역이 된다.
}
// Good
function sum(x, y) {
  let result = x + y;
  return result;
}

// Good
function foo() {
  let a, b;
  a = b = 0;
}
```


### <div style='background-color: #f1f8ff'>:five: 선언과 할당</div>

<br>

- `변수` : 값이 변하지 않는 변수는 const 값이 변하는 변수는 let을 사용하여 선언한다.
**<span style='background-color: #ffa59e'>var는 절대로 사용하지 않는다.</span>**
⇒ 기본은 const 사용

- const를 let보다 위에 선언한다.

```jsx
// Bad - 그룹화 없음
let foo;
let i = 0;
const len = this._array.length;
let bar;

// Good
const len = this._array.length;
const len2 = this._array2.length;
let i = 0;
let j = 0;
let foo, bar;
```

- const와 let은 사용 시점에 선언 및 할당을 한다.

```jsx
// Bad - 블록 스코프 밖에서 변수 선언
function foo() {
  const len = this._array.length;
  let i = 0;
  let j = 0;
  let len2, item;

  for (; i < len; i += 1) {
    ...
  }
  
  len2 = this._array2.length;
  for (j = 0, len2 = this._array2.length; j < len2; j += 1) {
    item = this._array2[j];
    ...
  }
}

// Good 
function foo() {
  const len = this._array.length;
  for (let i = 0; i < len; i += 1) {
    ...
  }

  // 사용 시점에 선언 및 할당
  const len2 = this._array2.length;
  for (let j = 0; j < len2; j += 1) {
    const item = this._array2[j];
    ...
  }
}
```


### <div style='background-color: #f1f8ff'>:six: 클래스와 생성자</div>

<br>

- class와 extends를 이용하여 객체 생성 및 상속을 구현한다.

```jsx
// Bad
function Queue(contents = []) {
  this._queue = [...contents];
}
Queue.prototype.pop = function() {
  const value = this._queue[0];
  this._queue.splice(0, 1);
  return value;
};

// Good
class Queue {
  constructor(contents = []) {
    this._queue = [...contents];
  }
  pop() {
    const {value} = this._queue;
    this._queue.splice(0, 1);
    return value;
  }
}
```

- mixin을 제외하고는 명시적으로 prototye을 호출하지 않는다.

```jsx
// Bad
const inherits = require('inherits');
function PeekableQueue(contents) {
  Queue.apply(this, contents);
}
inherits(PeekableQueue, Queue);
PeekableQueue.prototype.peek = function() {
  return this._queue[0];
};

// Good
class PeekableQueue extends Queue {
  peek() {
    return this._queue[0];
  }
}
```


### <div style='background-color: #f1f8ff'>:seven: 함수</div>

<br>

- 함수 생성자를 사용하여 선언하지 않는다.

```jsx
// Bad - 함수 생성자 사용
const doSomething = new Function('param1', 'param2', 'return param1 + param2;');

// Good - 함수 선언식 사용
function doSomething(param1, param2) {
  return param1 + param2;
}

// Good - 함수 표현식 사용
const doSomething = function(param1, param2) {
  return param1 + param2;
};
```

- 함수는 사용 전에 선언해야 하며, 함수 선언문은 변수 선언문 다음에 오도록 한다.

```jsx
// Bad - 선언 이전에 사용
const sumedValue = sum(1, 2);
const sum = function(param1, param2) {
  return param1 + param2;
};

// Bad - 선언 이전에 사용
const sumedValue = sum(1, 2);
function sum(param1, param2) {
  return param1 + param2;
};

// Good
const sum = function(param1, param2) {
  return param1 + param2;
};
const sumedValue = sum(1, 2);
```


### <div style='background-color: #f1f8ff'>:eight: 블록 구문</div>

<br>

- 한 줄짜리 블록일 경우라도 {} 를 생략하지 않는다.

```jsx
// Bad
if(condition) doSomething();

// Bad
if (condition) doSomething();
else doAnything();

// Bad
for(let prop in object) someIterativeFn();

// Bad
while(condition) iterating += 1;

// Good
if (condition) {
  ...
}

// Good
if (condition) {
  ...
} else {
  ...
}
```

- 키워드와 조건문 사이에 빈칸을 사용한다.

```jsx
// Bad
var i = 0;
for(;i<100;i+=1) {
  someIterativeFn();
} 

// Good
var i = 0;
for(; i < 100; i+=1) {
  someIterativeFn();
}
```

- `do~while` : while문 끝에 세미콜론을 사용한다.
- `switch~case` : 첫 번째 case문을 제외하고 case문 사용 이전에 개행한다.


### <div style='background-color: #f1f8ff'>:nine: 주석</div>

<br>

- 설명하려는 구문에 맞춰 들여쓰기 한다.

```jsx
// Bad
function someFunction() {
  ...

// statement에 관한 주석
  statements
}

// Good
function someFunction() {
  ...

  // statement에 관한 주석
  statements
}
```

- 한 줄 주석 : 공백 추가 후 문장 끝에 사용한다.

```jsx
// Bad
var someValue = data1;//주석 표시 전후 공백

// Bad
var someValue = data1; /* 여러 줄 주석 */

// Good
var someValue = data1; // 주석 표시 전후 공백
```

- 여러 줄 주석 : /* … */

```jsx
// Bad - '*' 표시의 정렬
/*
* 주석내용
*/

// Bad - 주석의 첫 줄에는 기술하지 않는다
...
/* var foo = '';
 * var bar = '';
 * var quux;
 */

// Good - '*' 표시의 정렬을 맞춘다
/*
 * 주석내용 
 */
```