package everylecture.lecturemgt.controller.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import everylecture.lecturemgt.controller.dto.LecturesGetDetailOutDTO;
import everylecture.lecturemgt.domain.Lecture;


@Mapper(componentModel = "spring", uses = {})
public interface LectureGetDetailOutMapper extends EntityMapper<LecturesGetDetailOutDTO, Lecture> {


    @Mapping(target = "version", ignore = true)
    Lecture toEntity(LecturesGetDetailOutDTO lectureDTO);

    default Lecture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lecture lecture = new Lecture();
        lecture.setId(id);
        return lecture;
    }
}
