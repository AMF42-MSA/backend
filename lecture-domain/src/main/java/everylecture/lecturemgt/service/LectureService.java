package everylecture.lecturemgt.service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.core.JsonProcessingException;

import everylecture.lecturemgt.domain.Lecture;


/**
 * Service Interface for managing {@link lecturemgt.domain.Rental}.
 */

public interface LectureService {

    /**
     * Save a rental.
     *
     * @param rentalDTO the entity to save.
     * @return the persisted entity.
     */
	Lecture save(Lecture lecture);

    /**
     * Get all the Lecture.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Lecture> findAll(Pageable pageable);

    /**
     * Get the "id" rental.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Lecture> findOne(Long id);

    /**
     * Delete the "id" rental.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Business Logic
     * 강의 신규 등록
     **/
    Lecture registerLecture(Lecture lecture) throws InterruptedException, ExecutionException, JsonProcessingException;
}
