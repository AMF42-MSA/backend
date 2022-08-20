package lecturemgt.validation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {
    @PostMapping
    public ResponseEntity<Void> test(@Valid @RequestBody RequestDto requestDto){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
