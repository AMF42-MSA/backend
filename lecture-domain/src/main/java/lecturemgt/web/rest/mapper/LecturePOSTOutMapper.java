package lecturemgt.web.rest.mapper;


import org.mapstruct.Mapper;

import lecturemgt.domain.Lecture;
import lecturemgt.web.rest.dto.LecturesPostOutDTO;


@Mapper(componentModel = "spring", uses = {})
public interface LecturePOSTOutMapper extends EntityMapper<LecturesPostOutDTO, Lecture> {


//    @Mapping(target = "opName", ignore = true)

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
