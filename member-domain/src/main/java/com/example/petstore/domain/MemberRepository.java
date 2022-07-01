package com.example.petstore.domain;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long>{    // Repository Pattern Interface
  
}