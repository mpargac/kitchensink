package com.kitchensink.kitchensink.service;

import com.kitchensink.kitchensink.dto.MemberDTO;
import com.kitchensink.kitchensink.entity.Member;
import com.kitchensink.kitchensink.mapper.MemberMapper;
import com.kitchensink.kitchensink.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberMapper memberMapper;
    @Captor
    private ArgumentCaptor<Member> memberArgumentCaptor;


    @Test
    public void getMemberById() {
        Long extId = 1L;
        Member entity = Member.builder()
                .extId(extId)
                .name("name")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();
        MemberDTO expected = MemberDTO.builder()
                .id(extId)
                .name("name")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();
        when(memberRepository.findByExtId(extId)).thenReturn(Optional.of(entity));
        when(memberMapper.entityToDto(entity)).thenReturn(expected);

        MemberDTO actual = memberService.getMemberByExtId(extId);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
    }

    @Test
    public void createMember() {
        Member entity = Member.builder()
                .extId(1L)
                .name("name")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();
        MemberDTO expected = MemberDTO.builder()
                .id(2L)
                .name("name")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();
        when(memberMapper.dtoToEntity(expected)).thenReturn(entity);
        when(memberRepository.findTopByOrderByExtIdDesc()).thenReturn(entity);

        memberService.createMember(expected);
        verify(memberRepository).save(memberArgumentCaptor.capture());
        Member actual = memberArgumentCaptor.getValue();

        assertEquals(expected.getId(), actual.getExtId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
    }
}
