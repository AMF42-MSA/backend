package everylecture.lecturemgt.service.vo;

import everylecture.lecturemgt.config.kafka.AbstractEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@AllArgsConstructor
public class CategoryChanged extends AbstractEvent {
//    String jobType;

    private Long categoryId;

    private String categoryName;
}
