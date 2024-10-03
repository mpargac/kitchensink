package com.kitchensink.kitchensink.repository;

import com.kitchensink.kitchensink.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    boolean existsByEmail(String email);

    Optional<Member> findByExtId(Long extId);

    boolean existsByExtId(Long extId);

    Member findTopByOrderByExtIdDesc();
}
