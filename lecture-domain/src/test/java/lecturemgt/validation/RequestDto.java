package lecturemgt.validation;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;

@Getter
public class RequestDto {

    @URL(message = "유효한 URL 값이 아닙니다.")
    private String url;

    @URL(message = "깃허브 주소가 아닙니다.", protocol = "https", host = "github.com")
    private String githubUrl;
}
