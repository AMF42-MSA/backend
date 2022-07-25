package com.example.auction.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "lectureBids", path = "lectureBids")
public interface LectureBidRepository
    extends PagingAndSortingRepository<LectureBid, Long> {}
