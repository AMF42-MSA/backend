package com.everyoneslecture.member.domain.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everyoneslecture.member.domain.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{    // Repository Pattern Interface
    Optional<MemberEntity> findById(Long id);
    MemberEntity findByEmail(String email);
}
