package com.everyoneslecture.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everyoneslecture.member.domain.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{    // Repository Pattern Interface
    MemberEntity findByMemberId(Long memberId);
    MemberEntity findByEmail(String email);
}
