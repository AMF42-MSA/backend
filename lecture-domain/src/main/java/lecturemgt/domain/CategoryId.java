package lecturemgt.domain;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class CategoryId {

    private Long id;

    // 만약 필드가 2개 이상이면 테이블에 어찌 생성될까
    // private String categoryName;
}
