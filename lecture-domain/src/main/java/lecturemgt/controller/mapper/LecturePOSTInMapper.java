package lecturemgt.controller.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import lecturemgt.controller.dto.LecturesPostInDTO;
import lecturemgt.domain.Lecture;


@Mapper(componentModel = "spring", uses = {})
public interface LecturePOSTInMapper extends EntityMapper<LecturesPostInDTO, Lecture> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "version", ignore = true)
    Lecture toEntity(LecturesPostInDTO lectureDTO);

    default Lecture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lecture lecture = new Lecture();
        lecture.setId(id);
        return lecture;
    }
}
