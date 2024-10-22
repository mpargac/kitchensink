package com.kitchensink.kitchensink.controller;

import com.kitchensink.kitchensink.dto.MemberDTO;
import com.kitchensink.kitchensink.dto.PartialUpdateMemberDTO;
import com.kitchensink.kitchensink.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/members")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberDTO> addMember(@Valid @RequestBody MemberDTO member) {
        return ResponseEntity.ok(memberService.createMember(member));
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

    @PatchMapping("/{id}")
    public ResponseEntity<MemberDTO> partialUpdateMember(@PathVariable Long id,
                                                         @Valid @RequestBody PartialUpdateMemberDTO member) {
        return ResponseEntity.ok(memberService.updateMember(id, member));
    }

}
