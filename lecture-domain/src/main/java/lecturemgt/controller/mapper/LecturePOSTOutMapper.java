package lecturemgt.controller.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import lecturemgt.controller.dto.LecturesPostOutDTO;
import lecturemgt.domain.Lecture;


@Mapper(componentModel = "spring", uses = {})
public interface LecturePOSTOutMapper extends EntityMapper<LecturesPostOutDTO, Lecture> {


    @Mapping(target = "status", ignore = true)
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
