package lecturemgt.infra;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lecturemgt.domain.LectureRepository;

@RestController
@RequestMapping(value = "/lectures")
@Transactional
public class LectureController {

    @Autowired
    LectureRepository lectureRepository;
    // keep
}
