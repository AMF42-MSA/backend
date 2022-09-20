package everylecture.lecturemgt.controller.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class MemberInfoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String memberId;
    private String name;
    private String phone;
}
