package com.kitchensink.kitchensink.controller;

import com.kitchensink.kitchensink.entity.Member;
import com.kitchensink.kitchensink.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Member> addMember(Member member) {
        return ResponseEntity.ok(memberService.createMember(member));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers(Sort.by(Sort.Direction.ASC, "name")));
    }

}
