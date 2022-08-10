package everylecture.lecturemgt.controller.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class MemberInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long memberId;
    private String name;
    private String phone;
}
