package com.everyoneslecture.domain.lectureBid.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.everyoneslecture.domain.lectureBid.service.LectureBidService;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidDto;
import com.everyoneslecture.domain.lectureBid.dto.LectureBidPostInDto;
import com.everyoneslecture.domain.lectureBid.entity.LectureBid;
import com.everyoneslecture.domain.lectureBid.enums.BidStatus;



@Service
@Transactional
public class LectureBidServiceImpl implements LectureBidService {

  private final Logger log = LoggerFactory.getLogger(LectureBidServiceImpl.class);

	@Override
	public LectureBid save(LectureBid lectureBid) {
		return null;
	}

	@Override
	public Page<LectureBid> findAll(Pageable pageable) {
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public Optional<LectureBid> findOne(Long id) {
		return null;
	}

	@Override
	public void delete(Long id) {
	}

  //입찰등록
	@Override
    @Transactional
	public LectureBid registerLectureBid(LectureBid lectureBid)
			throws InterruptedException, ExecutionException, JsonProcessingException {
        log.debug("registerLecture : {}", lectureBid,  lectureBid);
        lectureBid.setStatus(BidStatus.BID);//입찰
        return lectureBid.repository().save(lectureBid);
	}

  //입찰취소
  @Override
  public LectureBid cancelLectureBid(Long lectureBidId)
      throws InterruptedException, ExecutionException, JsonProcessingException {
        log.debug("registerLecture : {}", lectureBidId,  lectureBidId);
        LectureBid lectureBid = new LectureBid();
        lectureBid = LectureBid.repository().findById(lectureBidId).get();
        lectureBid.setStatus(BidStatus.CANCEL);//입찰취소
        return lectureBid.repository().save(lectureBid);
  }

  //낙찰요청
  @Override
  public LectureBid successLectureBid(Long lectureBidId)
      throws InterruptedException, ExecutionException, JsonProcessingException {
        log.debug("registerLecture : {}", lectureBidId,  lectureBidId);
        LectureBid lectureBid = new LectureBid();
        lectureBid = LectureBid.repository().findById(lectureBidId).get();
        lectureBid.setStatus(BidStatus.SUCCESS);
        return lectureBid.repository().save(lectureBid);
  }

  /**
   * Business Logic
   * 강좌별 경매정보 조회
   **/
  @Override
  public List<LectureBidDto> searchAuctionLectureBidList()
      throws InterruptedException, ExecutionException, JsonProcessingException {
        List<LectureBidDto> auctionLectureBidList = LectureBid.repository().findAuctionLectureBidList();
        return auctionLectureBidList;
  }


  /**
   * Business Logic
   * 입찰정보 조회
   **/
  @Override
  public LectureBid searchLectureBid(LectureBidPostInDto lectureBidPostInDto)
      throws InterruptedException, ExecutionException, JsonProcessingException {

        LectureBid lectureBid = LectureBid.repository().findLectureBidByAuctionIdAndMemberId(lectureBidPostInDto.getAuctionId(), lectureBidPostInDto.getMemnberId());
        return lectureBid;
  }

}


