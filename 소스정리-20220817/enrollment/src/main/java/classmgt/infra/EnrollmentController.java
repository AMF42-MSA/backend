package classmgt.infra;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import classmgt.domain.EnrollmentRepository;

@RestController
@RequestMapping(value = "/enrollments")
@Transactional
public class EnrollmentController {

    @Autowired
    EnrollmentRepository enrollmentRepository;
    // keep
}
