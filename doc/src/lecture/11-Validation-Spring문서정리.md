# Validation알아보기-01
- 2022-08-19정리
- https://spring.io/guides/gs/validating-form-input/#initial
- 소스: git clone https://github.com/spring-guides/gs-validating-form-input.git


##  테스트 방법
1. "initial" 폴더를 Eclipse에서 프로젝트 로드
2. 주요 참조 모듈(Spring Web, Thymeleaf, and Validation)
   - Validation: DTO 클래스에서 IO 필드 Validation 학인
   - Thymeleaf: 화면에서 오류시 화면에 오류 정보 Display
### 주요 소스

#### PersonForm
1. NotNull, Size, Min 이번 테스트의 핵심 부분
```java
package com.example.validatingforminput;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonForm {

	@NotNull
	@Size(min=2, max=30)
	private String name;

	@NotNull
	@Min(18)
	private Integer age;

   ....(get, set 제거)
}
```

#### Create a Web Controller
1. @Valid PersonForm personForm
   - A personForm object marked with @Valid to gather the attributes filled out in the form
   - 양식 personForm에 채워진 속성을 수집하기 위해 @Valid로 표시
2. BindingResult bindingResult
   - Validation 오류내역을 수집
   - bindingResult.hasErrors() ==> 화면으로도 전달됨
```java
import javax.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@PostMapping("/")
	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "form";  //오류가 발생하면 "form.html"로
		}

		return "redirect:/results";
	}
}
```

#### HTML 프런트 엔드 구축
1. src/main/resources/templates/form.html
   - 오류 내역을 화면에 표시하는 부분
   - <td th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Name Error</td>
   -

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
    <body>
        <form action="#" th:action="@{/}" th:object="${personForm}" method="post">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" th:field="*{name}" /></td>
                    <td th:if="${#fields.hasErrors('name')}" th:errors="*{name}">Name Error</td>
                </tr>
                <tr>
                    <td>Age:</td>
                    <td><input type="text" th:field="*{age}" /></td>
                    <td th:if="${#fields.hasErrors('age')}" th:errors="*{age}">Age Error</td>
                </tr>
                <tr>
                    <td><button type="submit">Submit</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
```

```java
```

```java
```

```java
```
