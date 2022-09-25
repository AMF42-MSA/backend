package everylecture.lecturemgt.config.interceptor;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;



@Component
@EnableAspectJAutoProxy    //메소드 시작.종료시점에 자동로깅 추가(AOP)

public class AOPContext {

	/*
	 * 2022-08-28 AOP관련 추가 내용
	 * 참조: example.springdata.jpa.interceptors    
	 *  https://github.com/spring-projects/spring-data-examples
	 */
	@Bean
	public   CustomizableTraceInterceptor interceptor() {
//			var interceptor = new CustomizableTraceInterceptor();

		var interceptor = new MethodTraceInterceptor();
		//class명이 proxy를 제외하기 위하여
		interceptor.setHideProxyClassNames(true);  
		interceptor.setEnterMessage("메소드시작: $[targetClassName].$[methodName]($[arguments])");
		interceptor.setExitMessage( "메소드종료: $[targetClassName].$[methodName] : return:  $[returnValue], RunTime: $[invocationTime]");

		return interceptor;
	}

	@Bean 
	public  Advisor traceAdvisor() {
		var pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("within(everylecture.lecturemgt..*)");
//			pointcut.setExpression("execution(* everylecture..*Controller.*(..))");  //이름이 Controller르 끝나는 인터페이스의 파라미터가 0개 이상인 모든 메서드
//			pointcut.setExpression("execution(* everylecture..*Service.*(..))");  //이름이 Controller르 끝나는 인터페이스의 파라미터가 0개 이상인 모든 메서드

		return new DefaultPointcutAdvisor(pointcut, interceptor());
	}
		
	
	/* controller 패키지에 포함된 public 메서드와 매칭 */
	@Pointcut("execution(* everylecture..*Controller.*(..))")
	@Bean 
	public void onRequest() { }

	@Bean 
	public  CustomizableTraceInterceptor setUIrl() {
//		var interceptor = new CustomizableTraceInterceptor();

		var interceptor = new MethodTraceInterceptor();
		//class명이 proxy를 제외하기 위하여
		interceptor.setHideProxyClassNames(true);  
		interceptor.setEnterMessage("메소드시작: $[targetClassName].$[methodName]($[arguments])");
		interceptor.setExitMessage( "메소드종료: $[targetClassName].$[methodName] : return:  $[returnValue], RunTime: $[invocationTime]");

		return interceptor;
	}

//	 /* Pointcut 과 매칭되는 메서드의 실행 전, 후에 실행
//	 *  @Around advice 는 꼭 proceed()가 필요하다. */
//	@Around("onRequest()")
//	public void logAction(ProceedingJoinPoint joinPoint) throws Throwable{
//	    Class clazz = joinPoint.getTarget().getClass();
//	    String url = getRequestUrl(joinPoint, clazz);
//    	MDC.put("URL",url);
//
//	}
//	
//	private String getRequestUrl(JoinPoint joinPoint, Class clazz) {
//		  MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//		  Method method = methodSignature.getMethod();
//		  RequestMapping requestMapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
//		  String baseUrl = requestMapping.value()[0];
//
//		  String url = Stream.of( GetMapping.class, PutMapping.class, PostMapping.class, 
//		              PatchMapping.class, DeleteMapping.class, RequestMapping.class)
//		              .filter(mappingClass -> method.isAnnotationPresent(mappingClass))
//		              .map(mappingClass -> getUrl(method, mappingClass, baseUrl))
//		              .findFirst().orElse(null);
//		  return url;
//		}
//	
//		/* httpMETHOD + requestURI 를 반환 */
//		private String getUrl(Method method, Class<? extends Annotation> annotationClass, String baseUrl){
//		  Annotation annotation = method.getAnnotation(annotationClass);
//		  String[] value;
//		  String httpMethod = null;
//		  try {
//		      value = (String[])annotationClass.getMethod("value").invoke(annotation);
//		      httpMethod = (annotationClass.getSimpleName().replace("Mapping", "")).toUpperCase();
//		  } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
//		      return null;
//		  }
//		   return String.format("%s %s%s", httpMethod, baseUrl, value.length > 0 ? value[0] : "") ;
//		}
//	
}
