package com.kitchensink.kitchensink.controller;

import com.kitchensink.kitchensink.dto.MemberDTO;
import com.kitchensink.kitchensink.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MemberMvcController {

    private final MemberService memberService;

    @GetMapping
    public String showForm(Model model) {
        List<MemberDTO> members = memberService.getAllMembers(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("members", members);

        MemberDTO member = new MemberDTO();
        model.addAttribute("member", member);
        return "index";
    }

    @PostMapping
    public String submitForm(@ModelAttribute("member") MemberDTO member, Model model) {
        System.out.println(member);
        memberService.createMember(member);

        List<MemberDTO> members = memberService.getAllMembers(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("members", members);
        return "index";
    }
}
