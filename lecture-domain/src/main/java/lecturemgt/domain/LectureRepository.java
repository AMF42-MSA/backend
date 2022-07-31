package lecturemgt.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "entity/lectures",
    path = "lectures"
)
public interface LectureRepository
    extends PagingAndSortingRepository<Lecture, Long> {}
