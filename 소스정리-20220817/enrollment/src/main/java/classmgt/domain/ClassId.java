package classmgt.domain;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ClassId {

    private Long id;
}
