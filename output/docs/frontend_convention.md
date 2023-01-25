

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