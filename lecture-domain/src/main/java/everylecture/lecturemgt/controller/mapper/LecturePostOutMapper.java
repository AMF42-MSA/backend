package everylecture.lecturemgt.controller.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import everylecture.lecturemgt.controller.dto.LecturesPostOutDTO;
import everylecture.lecturemgt.domain.Lecture;


@Mapper(componentModel = "spring", uses = {})
public interface LecturePostOutMapper extends EntityMapper<LecturesPostOutDTO, Lecture> {


    @Mapping(target = "version", ignore = true)
    Lecture toEntity(LecturesPostOutDTO lectureDTO);

    default Lecture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lecture lecture = new Lecture();
        lecture.setId(id);
        return lecture;
    }
}
