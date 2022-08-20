# Validation알아보기-01
- 2022-08-20정리
- https://www.digitalocean.com/community/tutorials/spring-validation-example-mvc-validator
- 소스: [git clone https://github.com/spring-guides/gs-validating-form-input.git](https://journaldev.nyc3.digitaloceanspaces.com/spring/SpringFormValidation.zip)

- 제목: Spring Validation Example - Spring MVC Form Validator
##  문서 정리

### 스프링 유효성 검사
Spring MVC Framework 는 기본적으로 JSR-303 사양을 지원하며 Spring MVC 애플리케이션에서 JSR-303 및 구현 종속성을 추가하기만 하면 됩니다.
- Spring은 또한 컨트롤러 요청 핸들러 메소드에서 Validator 구현에 의해 발생하는 오류를 얻을 수 있는 @Validator주석과 클래스를 제공합니다.
- 두 가지 방법: BindingResult, 사용자 정의 유효성 검사기
  - 첫 번째 방법은 JSR-303 사양을 확인하는 주석을 만들고 해당 Validator 클래스를 구현하는 것
  - 두 번째 접근 방식은 인터페이스를 구현하고 주석 org.springframework.validation.Validator을 사용하여 Controller 클래스에 유효성 검사기로 설정하는 것

- 구현 아티팩트 hibernate-validator@InitBinder 와 함께 JSR-303 사양을 사용할 Spring Tool Suite에서 간단한 Spring MVC 프로젝트를 생성.
  - 주석 기반 양식 유효성 검사를 사용하고 JSR-303 사양 표준을 기반으로 자체 사용자 지정 유효성 검사기를 만듭니다.
  - 인터페이스를 구현하여 자체 사용자 정의 유효성 검사기 클래스를 만들고 Validator이를 컨트롤러 처리기 메서드 중 하나에서 사용합니다. 우리의 최종 프로젝트는 아래 이미지와 같습니다.

### 스프링 MVC 폼 유효성 검사기
pom.xml ( validation-api 및 hibernate-validator )

```xml
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
    <version>1.1.0.Final</version>
</dependency>
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-validator</artifactId>
    <version>4.1.0.Final</version>
</dependency>
```

### 모델 클래스

```java
public class Customer {

	@Size(min=2, max=30)
    private String name;

    @NotEmpty @Email
    private String email;

    @NotNull @Min(18) @Max(100)
    private Integer age;

    @NotNull
    private Gender gender;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
    private Date birthday;

    @Phone
    private String phone;
```
- JSR-303에 추가되고 Hibernate 유효성 검사기 구현에 의해 제공되는 @Email , @NotEmpty 및 @DateTimeFormat 주석을 사용하고 있음을 주목 하십시오. 우리가 사용하고 있는 JSR-303 주석 중 일부는 @Size , @NotNull 등 입니다. 사용된 @Phone 주석은 JSR-303 사양을 기반으로 한 사용자 정의 구현입니다.


### 사용자 정의 유효성 검사기 구현

Phone.java 코드:

```java
package com.journaldev.spring.form.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {


    String message() default "{Phone}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
```


PhoneValidator.java 코드:
```java
package com.journaldev.spring.form.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

	@Override
	public void initialize(Phone paramA) {
	}

	@Override
	public boolean isValid(String phoneNo, ConstraintValidatorContext ctx) {
		if(phoneNo == null){
			return false;
		}
		//validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) return true;
        //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
        //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
        //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
        //return false if nothing matches the input
        else return false;
	}

}
```
SR-303 사양 유효성 검사기 구현은 javax.validation.ConstraintValidator인터페이스를 구현해야 합니다. initialize()DataSource와 같은 일부 리소스를 사용하는 경우 메서드 에서 초기화할 수 있습니다 . 유효성 검사 방법은 isValid데이터가 유효하면 true를 반환하고 그렇지 않으면 false를 반환


EmployeeFormValidator.java 클래스 코드:
```java
package com.journaldev.spring.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.journaldev.spring.form.model.Employee;

public class EmployeeFormValidator implements Validator {

	//which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> paramClass) {
		return Employee.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");

		Employee emp = (Employee) obj;
		if(emp.getId() <=0){
			errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "role.required");
	}
}
```

### 컨트롤러 클래스
1. 주석 기반
```java
@Controller
public class CustomerController {

	@RequestMapping(value = "/cust/save.do", method = RequestMethod.POST)
	public String saveCustomerAction(
			@Valid Customer customer,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning custSave.jsp page");
			return "custSave";
		}
		logger.info("Returning custSaveSuccess.jsp page");
		model.addAttribute("customer", customer);
		customers.put(customer.getEmail(), customer);
		return "custSaveSuccess";
	}

}
```
    - 주석 기반 양식 유효성 검사를 사용할 때 컨트롤러 처리기 메서드 구현을 약간만 변경하면 작동
    - 먼저 주석으로 유효성을 검사하려는 모델 객체에 주석을 @Valid 추가
    - 메서드에 BindingResult 인수가 있어야 합니다.
    - Spring은 이를 오류 메시지로 채우는 작업을 처리합니다.
    - 처리기 메서드 논리
      - 오류가 있는 경우 동일한 페이지로 응답하거나 사용자를 성공 페이지로 리디렉션합니다. 주목해야 할 또 다른 중요한 점은 "customer" 속성을 모델에 추가한다는 것입니다. 이는 Spring 프레임워크가 양식 페이지에서 사용할 모델 객체를 알려주는 데 필요합니다. 그렇게 하지 않으면 양식 데이터에 대한 개체 바인딩이 수행되지 않고 양식 유효성 검사가 작동하지 않습니다.
2. 사용자 Validator

```java
@Controller
public class EmployeeController {

	private static final Logger logger = LoggerFactory
			.getLogger(EmployeeController.class);

	private Map<Integer, Employee> emps = null;

	@Autowired
	@Qualifier("employeeValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}


	@RequestMapping(value = "/emp/save.do", method = RequestMethod.POST)
	public String saveEmployeeAction(
			@ModelAttribute("employee") @Validated Employee employee,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.info("Returning empSave.jsp page");
			return "empSave";
		}
		logger.info("Returning empSaveSuccess.jsp page");
		model.addAttribute("emp", employee);
		emps.put(employee.getId(), employee);
		return "empSaveSuccess";
	}
}
```

### 양식 유효성 검사 오류 메시지 리소스 번들
message_en.properties 파일:
```properties
#application defined error messsages
id.required=Employee ID is required
name.required=Employee Name is required
role.required=Employee Role is required
negativeValue={0} can't be negative or zero

#Spring framework error messages to be used when conversion from form data to bean fails
typeMismatch.int={0} Value must be an integer
typeMismatch.java.lang.Integer={0} must be an integer
typeMismatch={0} is of invalid format

#application messages for annotations, {ValidationClass}.{modelObjectName}.{field}
#the {0} is field name, other fields are in alphabatical order, max and then min
Size.customer.name=Customer {0} should be between {2} and {1} characters long
NotEmpty.customer.email=Email is a required field
NotNull.customer.age=Customer {0} should be in years

#Generic annotation class messages
Email=Email address is not valid
NotNull=This is a required field
NotEmpty=This is a required field
Past=Date should be Past

#Custom validation annotation
Phone=Invalid format, valid formats are 1234567890, 123-456-7890 x1234
```


### 양식 및 오류가 있는 페이지 보기

custSave.jsp
```html
	<springForm:form method="POST" commandName="customer"
		action="save.do">
		<table>
			<tr>
				<td>Name:</td>
				<td><springForm:input path="name" /></td>
				<td><springForm:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><springForm:input path="email" /></td>
				<td><springForm:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Age:</td>
				<td><springForm:input path="age" /></td>
				<td><springForm:errors path="age" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Gender:</td>
				<td><springForm:select path="gender">
						<springForm:option value="" label="Select Gender" />
						<springForm:option value="MALE" label="Male" />
						<springForm:option value="FEMALE" label="Female" />
					</springForm:select></td>
				<td><springForm:errors path="gender" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Birthday:</td>
				<td><springForm:input path="birthday" placeholder="MM/dd/yyyy"/></td>
				<td><springForm:errors path="birthday" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Phone:</td>
				<td><springForm:input path="phone" /></td>
				<td><springForm:errors path="phone" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Save Customer"></td>
			</tr>
		</table>

	</springForm:form>
```
