package com.kitchensink.kitchensink.controller;

import com.kitchensink.kitchensink.dto.MemberDTO;
import com.kitchensink.kitchensink.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/members")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> addMember(@Valid @RequestBody MemberDTO member) {
        memberService.createMember(member);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberByExtId(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberByExtId(id));
    }

    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers(Sort.by(Sort.Direction.ASC, "name")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMemberByExtId(id);
        return ResponseEntity.ok().build();
    }

}
