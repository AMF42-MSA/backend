package everylecture.lecturemgt.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import everylecture.lecturemgt.domain.Lecture;

@Component
public class LectureHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Lecture>> {

    @Override
    public EntityModel<Lecture> process(EntityModel<Lecture> model) {
        return model;
    }
}
