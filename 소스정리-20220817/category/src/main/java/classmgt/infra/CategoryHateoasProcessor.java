package classmgt.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import classmgt.domain.Category;

@Component
public class CategoryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Category>> {

    @Override
    public EntityModel<Category> process(EntityModel<Category> model) {
        return model;
    }
}
