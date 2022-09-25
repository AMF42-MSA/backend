package everylecture.lecturemgt.config.interceptor;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;


@Component
@Getter
@Setter
//서비스 요청에서 응답까지 생명주기 유지 (종료 시점까지 동일 Logger을 생성하기 위하여)
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) 
public class OnlineContext {

    private final Logger log = LoggerFactory.getLogger(OnlineContext.class);
    
	private String GUID;
//    private String URL;

//    public void setRequestURL(String requestURL) {
//    	MDC.put("URL",requestURL);
//        this.URL = requestURL;
//    }
    
//    public Logger getLog() {
//    	return log;
//    }
    
//    public void debug(String message) {
//        log.debug("[" + uuid + "]" + "[" + requestURL + "] " +  message);
//    }
//
//    public void debug(String format, Object arg) {
//        log.debug("[" + uuid + "]" + "[" + requestURL + "] " + format, arg);
//    }
    @PostConstruct
    public void init() {
    	GUID =  UUID.randomUUID().toString();
    	MDC.put("GUID", GUID);
//    	URL = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
//    	String urlWords[] = URL.split("/");
//    	if (urlWords.length > 4) {
//    		String urlPath = "";
//    		for (int i = 4; i < urlWords.length ; i++) {
//    			urlPath = urlPath + "/" + urlWords[i]; 
//			}
//	    	MDC.put("URL",urlPath);
//    	}

    	//    	출처: https://prohannah.tistory.com/182 [Hello, Hannah!:티스토리]
        log.debug("[ {} , {}] request scope bean create: {}", GUID,  this);
    }
    @PreDestroy
    public void close() {
        log.debug("[ {} , {}] request scope bean close: {}", GUID,  this);
    }
}
