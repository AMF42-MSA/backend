package classmgt.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "enrollments",
    path = "enrollments"
)
public interface EnrollmentRepository
    extends PagingAndSortingRepository<Enrollment, Long> {}
