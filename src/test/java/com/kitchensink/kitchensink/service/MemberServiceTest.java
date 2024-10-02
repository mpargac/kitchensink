package com.kitchensink.kitchensink.service;

import com.kitchensink.kitchensink.entity.Member;
import com.kitchensink.kitchensink.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void getMemberById() {
        Long extId = 1L;
        Member expected = Member.builder()
                .extId(extId)
                .name("name")
                .email("email")
                .phoneNumber("phoneNumber")
                .build();
        when(memberRepository.findByExtId(extId)).thenReturn(Optional.of(expected));

        Member actual = memberService.getMemberByExtId(extId);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
    }
}
