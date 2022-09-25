package everylecture.lecturemgt.controller.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import everylecture.lecturemgt.controller.dto.LecturesPostInDTO;
import everylecture.lecturemgt.domain.Lecture;


@Mapper(componentModel = "spring", uses = {})
public interface LecturePostInMapper extends EntityMapper<LecturesPostInDTO, Lecture> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
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
