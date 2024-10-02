package com.kitchensink.kitchensink.repository;

import com.kitchensink.kitchensink.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    boolean existsByEmail(String email);
}