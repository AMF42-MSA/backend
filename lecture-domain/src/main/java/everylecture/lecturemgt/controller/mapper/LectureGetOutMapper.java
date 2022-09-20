package everylecture.lecturemgt.controller.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import everylecture.lecturemgt.controller.dto.LecturesGetOutDTO;
import everylecture.lecturemgt.domain.Lecture;


@Mapper(componentModel = "spring")
public interface LectureGetOutMapper extends EntityMapper<LecturesGetOutDTO, Lecture> {


//    @Mapping(target = "status", ignore = true)
//    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "categoryId", ignore = true)
//    @Mapping(target = "memberId", ignore = true)
//    @Mapping(source = "lectId", target = "id")
//    Lecture toEntity(LecturesGetOutDTO lectureDTO);

    @Mapping(source="id", target="lectureId")
    LecturesGetOutDTO toDto(Lecture entityList);

    @Mapping(source="id", target="lectureId")
    List <LecturesGetOutDTO> toDto(List<Lecture> entityList);
    
//    default Lecture fromId(Long id) {
//        if (id == null) {
//            return null;
//        }
//        Lecture lecture = new Lecture();
//        lecture.setId(id);
//        return lecture;
//    }
}
