package com.kitchensink.kitchensink.controller;

import com.kitchensink.kitchensink.dto.MemberDTO;
import com.kitchensink.kitchensink.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberMvcController {

    private final MemberService memberService;

    @GetMapping
    public String showForm(Model model) {
        List<MemberDTO> members = memberService.getAllMembers(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("members", members);

        MemberDTO member = MemberDTO.builder().build();
        model.addAttribute("member", member);
        return "index";
    }

    @PostMapping
    public String submitForm(@Valid @ModelAttribute("member") MemberDTO member,
                             BindingResult bindingResult,
                             Model model) {
        log.info(member.toString());

        if (bindingResult.hasErrors()) {
            model.addAttribute("members", getAllMembers());
            return "index";
        }

        memberService.createMember(member);

        model.addAttribute("members", getAllMembers());
        return "index";
    }

    private List<MemberDTO> getAllMembers() {
        return memberService.getAllMembers(Sort.by(Sort.Direction.ASC, "name"));
    }
}
