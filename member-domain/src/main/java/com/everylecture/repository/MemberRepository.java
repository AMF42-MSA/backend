package com.everylecture.repository;

import com.everylecture.domain.Member;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MemberRepository extends PagingAndSortingRepository<Member, Long>{    // Repository Pattern Interface

}
