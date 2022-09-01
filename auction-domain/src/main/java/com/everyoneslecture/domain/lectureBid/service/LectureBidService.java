package com.everyoneslecture.domain.lectureBid.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidDetailDto;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidDto;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidPostInDto;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;


/**
 * Service Interface for managing {@link lecturemgt.domain.Rental}.
 */

public interface LectureBidService {

    /**
     * Save a rental.
     *
     * @param rentalDTO the entity to save.
     * @return the persisted entity.
     */
    LectureBid save(LectureBid lectureBid);

    /**
     * Get all the Lecture.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LectureBid> findAll(Pageable pageable);

    /**
     * Get the "id" rental.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LectureBid> findOne(Long id);

    /**
     * Delete the "id" rental.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Business Logic
     * 강의 입찰정보 등록
     **/
    LectureBid registerLectureBid(LectureBid lectureBid) throws InterruptedException, ExecutionException, JsonProcessingException;

     /**
     * Business Logic
     * 입찰취소
     **/
    LectureBid cancelLectureBid(Long lectureBidId) throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 낙찰요청
     **/
    LectureBid successLectureBid(LectureBid lectureBid) throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 유찰요청
     **/
    void failLectureBid(LectureBid lectureBid) throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 경매조회_입찰자수추가
     **/
    List<LectureBidDto> searchAuctionLectureBidList() throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 입찰정보 조회
     **/
    LectureBid searchLectureBid(LectureBidPostInDto lectureBidPostInDto) throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 입찰리스트 조회(선택 경매건 기준)
     **/
    List<LectureBidDetailDto> searchLectureBidList(LectureBid lectureBid) throws InterruptedException, ExecutionException, JsonProcessingException;

}
