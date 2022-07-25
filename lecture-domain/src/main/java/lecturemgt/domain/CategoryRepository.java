package lecturemgt.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "categories",
    path = "readCategories"
)
public interface CategoryRepository
    extends PagingAndSortingRepository<Category, Long> {}
