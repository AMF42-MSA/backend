package everylecture.lecturemgt;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import everylecture.lecturemgt.config.interceptor.MethodTraceInterceptor;
import everylecture.lecturemgt.config.kafka.KafkaProcessor;


@SpringBootApplication(scanBasePackages={"everylecture"})
@EnableBinding(KafkaProcessor.class)  //kafka관련 설정 Binder 
//@EnableFeignClients 이중으로 등록되어 있어 여기서는 제거
@EnableAutoConfiguration
@EnableAspectJAutoProxy    //메소드 시작.종료시점에 자동로깅 추가(AOP)
public class LectureApplication {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext =
            SpringApplication.run(LectureApplication.class, args);
    }
    
    
    
/*
 * 2022-08-28 AOP관련 추가 내용
 * 참조: example.springdata.jpa.interceptors    
 *  https://github.com/spring-projects/spring-data-examples
 */
    
	public  @Bean CustomizableTraceInterceptor interceptor() {
//		var interceptor = new CustomizableTraceInterceptor();

		var interceptor = new MethodTraceInterceptor();
		//class명이 proxy를 제외하기 위하여
		interceptor.setHideProxyClassNames(true);  
		interceptor.setEnterMessage("메소드시작: $[targetClassName].$[methodName]($[arguments])");
		interceptor.setExitMessage( "메소드종료: $[targetClassName].$[methodName] : return:  $[returnValue], RunTime: $[invocationTime]");

		return interceptor;
	}

     
	public @Bean Advisor traceAdvisor() {

		var pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("within(everylecture.lecturemgt..*)");
//		pointcut.setExpression("execution(* everylecture..*Controller.*(..))");  //이름이 Controller르 끝나는 인터페이스의 파라미터가 0개 이상인 모든 메서드
//		pointcut.setExpression("execution(* everylecture..*Service.*(..))");  //이름이 Controller르 끝나는 인터페이스의 파라미터가 0개 이상인 모든 메서드

		return new DefaultPointcutAdvisor(pointcut, interceptor());
	}

    
}
