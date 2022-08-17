package classmgt.infra;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import classmgt.domain.CategoryRepository;

@RestController
@RequestMapping(value = "/categories")
@Transactional
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    // keep
}
