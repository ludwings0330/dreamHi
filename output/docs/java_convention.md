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