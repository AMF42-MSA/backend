package com.everyoneslecture.domain.auction.vo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<MemberVo, Long>{    // Repository Pattern Interface
  Optional<MemberVo> findByMemberId(Long memberId);
}
