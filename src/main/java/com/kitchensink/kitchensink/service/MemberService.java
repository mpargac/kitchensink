package com.kitchensink.kitchensink.service;

import com.kitchensink.kitchensink.entity.Member;
import com.kitchensink.kitchensink.exception.MemberNotFoundException;
import com.kitchensink.kitchensink.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
    }

    public List<Member> getAllMembers(Sort sort) {
        return memberRepository.findAll(sort);
    }
}
