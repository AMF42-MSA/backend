package com.everyoneslecture.domain.auction.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.everyoneslecture.domain.auction.dto.AuctionDto;
import com.everyoneslecture.domain.auction.dto.AuctionInfoResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionResultDto;
import com.everyoneslecture.domain.auction.dto.AuctionTempDto;
import com.everyoneslecture.domain.auction.entity.Auction;
import com.everyoneslecture.domain.auction.enums.AuctionStatus;


/**
 * Service Interface for managing {@link lecturemgt.domain.Rental}.
 */

public interface AuctionService {

    /**
     * Save a rental.
     *
     * @param rentalDTO the entity to save.
     * @return the persisted entity.
     */
    Auction save(Auction auction);

    /**
     * Get all the Lecture.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Auction> findAll(Pageable pageable);

    /**
     * Get the "id" rental.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Auction> findOne(Long id);

    /**
     * Delete the "id" rental.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

     /**
     * Business Logic
     * 경매 취소 요청
     **/
    String cancelAuction(Auction auction) throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 경매정보 등록
     **/
    Auction registerAuction(Auction auction) throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 경매상태 정보 변경
     **/
    void updateAuctionStatusById(Long auctionId, AuctionStatus auctionStatus) throws InterruptedException, ExecutionException, JsonProcessingException;

    
     /**
     * Business Logic
     * 강좌별 경매정보 조회
     **/
    List<AuctionTempDto>  searchLectAuctionList() throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 경매정보리스트 조회
     **/
    Iterable<Auction> searchAuctionList(Auction auction) throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 경매정보 조회
     **/
    AuctionInfoResultDto searchAuction(Long auctionId) throws InterruptedException, ExecutionException, JsonProcessingException;

    /**
     * Business Logic
     * 경매정보 조회
     **/
    List<AuctionResultDto>  searchAuctionByLectId(Long lectId) throws InterruptedException, ExecutionException, JsonProcessingException;


}
