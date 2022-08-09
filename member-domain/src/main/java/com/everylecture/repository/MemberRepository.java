package com.everylecture.domain.repository;

import com.everylecture.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{    // Repository Pattern Interface
    MemberEntity findByLoginId(String loginId);
    MemberEntity findByMemberId(Long memberId);
    //MemberEntity findByEmail(String username);

}
