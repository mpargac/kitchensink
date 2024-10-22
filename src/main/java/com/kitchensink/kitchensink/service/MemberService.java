package com.kitchensink.kitchensink.service;

import com.kitchensink.kitchensink.dto.MemberDTO;
import com.kitchensink.kitchensink.dto.PartialUpdateMemberDTO;
import com.kitchensink.kitchensink.entity.Member;
import com.kitchensink.kitchensink.exception.MemberEmailExistsException;
import com.kitchensink.kitchensink.exception.MemberNotFoundException;
import com.kitchensink.kitchensink.mapper.MemberMapper;
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
    private final MemberMapper memberMapper;

    public MemberDTO createMember(MemberDTO member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new MemberEmailExistsException();
        }
        Member maxMember = memberRepository.findTopByOrderByExtIdDesc();
        Member entity = memberMapper.dtoToEntity(member);
        entity.setExtId(maxMember.getExtId() + 1);
        return  memberMapper.entityToDto(memberRepository.save(entity));
    }

    public MemberDTO getMemberByExtId(Long id) {
        return memberMapper.entityToDto(memberRepository.findByExtId(id).orElseThrow(MemberNotFoundException::new));
    }

    public List<MemberDTO> getAllMembers(Sort sort) {
        return memberRepository.findAll(sort)
                .stream()
                .map(memberMapper::entityToDto)
                .toList();
    }

    public void deleteMemberByExtId(Long id) {
        if (!memberRepository.existsByExtId(id)) {
            throw new MemberNotFoundException();
        }
        memberRepository.deleteByExtId(id);
    }

    public MemberDTO updateMember(Long id, PartialUpdateMemberDTO member) {
        Member entity = memberRepository.findByExtId(id).orElseThrow(MemberNotFoundException::new);
        if (member.getEmail() != null) {
            if (memberRepository.existsByEmail(member.getEmail())) {
                throw new MemberEmailExistsException();
            }
            entity.setEmail(member.getEmail());
        }
        if (member.getName() != null) {
            entity.setName(member.getName());
        }
        if (member.getPhoneNumber() != null) {
            entity.setPhoneNumber(member.getPhoneNumber());
        }
        return memberMapper.entityToDto(memberRepository.save(entity));
    }
}
