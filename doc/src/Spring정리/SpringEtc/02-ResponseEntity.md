<style>
.burk {
    background-color: red;
    color: yellow;
    display:inline-block;
}
</style>
# ResponseEntity란
참조: https://devlog-wjdrbs96.tistory.com/182
-demo-ResponseEntity(D:\APP\GIT-AMF3\backend\demo-ResponseEntity)

## 1. Controller와 RestController의 차이점

원본 스크랩: [Spring Boot] ResponseEntity란 무엇인가? :: Gyun&apos



Server/Spring REST API 2020. 7. 30. 05:34
먼저 REST API가 무엇인지는 아래 블로그를 먼저 잘 읽어보자.

https://meetup.toast.com/posts/92

REST API 제대로 알고 사용하기 : TOAST Meetup

REST API 제대로 알고 사용하기

meetup.toast.com

1. ResponseEntity란?

Spring Framework에서 제공하는 클래스 중 HttpEntity라는 클래스가 존재한다. 이것은 HTTP 요청(Request) 또는 응답(Response)에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스이다.

```java
public class HttpEntity<T> {

    private final HttpHeaders headers;

    @Nullable
    private final T body;
}
```

```
public class RequestEntity<T> extends HttpEntity<T>

public class ResponseEntity<T> extends HttpEntity<T>
```
HttpEntity 클래스를 상속받아 구현한 클래스가 RequestEntity, ResponseEntity 클래스이다. ResponseEntity는 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스이다. 따라서 HttpStatus, HttpHeaders, HttpBody를 포함한다.

ResponseEntity의 생성자를 보면 this( )를 통해서 매개변수가 3개인 생성자를 호출해 결국엔 아래 보이는 매개변수가 3개인 생성자로 가게된다.

```
public ResponseEntity(HttpStatus status) {
    this(null, null, status);
}
```

```
public ResponseEntity(@Nullable T body, HttpStatus status) {
    this(body, null, status);
}
```
간단한 예시를 들어보자.

```
@RestController
public class SampleController {

    @GetMapping("users")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
```



위와 같이 코드를 짜고 postMan으로 요청을 보내면 상태코드가 200으로 오는 것을 확인할 수 있다. (오른쪽 Status값)

또한 상태코드(Status), 헤더(headers), 응답데이터(ResponseData)를 담는 생성자도 존재한다.

```
public class ResponseEntity<T> extends HttpEntity<T> {

    public ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers);
        Assert.notNull(status, "HttpStatus must not be null");
        this.status = status;
    }
}

```
그리고 이제 ResponEntity를 이용해서 클라이언트에게 응답을 보내는 예제를 정리해보자.

```
import lombok.Data;

@Data
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }
}
```
Message라는 클래스를 만들어 상태코드, 메세지, 데이터를 담을 필드를 추가한다.

```
public enum StatusEnum {

    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");

    int statusCode;
    String code;

    StatusEnum(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
```
그리고 상태코드로 보낼 몇가지의 예시만 적어놓은 enum을 만들었다.

```
@RestController
public class UserController {
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK);
        message.setMessage("성공 코드");
        message.setData(user);

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }


}
```
그리고 위와 같이 Controller를 하나 만든 후에, id를 통해서 User를 가져오고 Message 클래스를 통해서 StatusCode, ResponseMessage, ResponseData를 담아서 클라이언트에게 응답을 보내는 코드이다. (User 클래스는 만들기 나름이기 때문에 자유롭게 만들면 되고, 코드는 생략하겠다) 이제 실행을 해보고 포스트맨으로 결과를 확인해보자.


data에 해당하는 부분은 내가 User 클래스를 만든 것이고 status, messages, data 잘 응답이 오는 것을 확인할 수 있고 이런식으로 클라이언트와 협업을 하면 좋을 것 같다.

https://devlog-wjdrbs96.tistory.com/197?category=882974

[Spring Boot] 클라이언트 REST API 응답보내기

이번 글에서는 서버가 클라이언트에게 응답을 할 때 JSON 형태로 주는 것을 정리해보려 한다. 간단히 포스트맨으로 로그인 요청을 했을 때 로그인 성공, 실패의 응답을 주는 예제를 정리해보자. (

devlog-wjdrbs96.tistory.com
위의 블로그에서 클라이언트에게 응답하는 부분을 좀 더 정리한 부분을 함께 읽어보면 좋을 거 같다.


