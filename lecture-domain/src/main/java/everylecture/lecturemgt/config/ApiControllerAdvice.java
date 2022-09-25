//package everylecture.lecturemgt.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
///*
// * @ControllerAdvice를 이용한 전역 에러 핸들링, 혹은 @Controller단에서의 지역 에러 핸들링을 사용
// * MethodArgumentNotValidException에 대한 @ExceptionHandler 어노테이션을 지정하여 커스텀 에러 핸들링
// */
//@RestControllerAdvice
//public class ApiControllerAdvice {
//    private final Logger log = LoggerFactory.getLogger(ApiControllerAdvice.class);
//    
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors()
//                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
//        log.error("MethodArgumentNotValidException 오류", ex);
//        return ResponseEntity.badRequest().body(errors);
//    }
//
//}
