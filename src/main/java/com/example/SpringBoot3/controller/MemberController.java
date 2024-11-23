package com.example.SpringBoot3.controller;

import com.example.SpringBoot3.dto.MemberForm;
import com.example.SpringBoot3.entity.Member;
import com.example.SpringBoot3.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signUp() {
        return "members/new";
    }

    @PostMapping("/join")
    public String memberRegister(MemberForm memberForm) {
        //DTO 확인용
        System.out.println(memberForm.toString());

        //1. DTO -> Entity
        Member member = memberForm.toEntity();
//        System.out.println(member.toString());
        log.info(member.toString());

        //2. Repository -> DB
        Member saved = memberRepository.save(member);
//        System.out.println(saved);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        //1. id 값으로 DB에서 데이터 받아오기
        Member memberEntity = memberRepository.findById(id).orElse(null);

        //2. 모델에 데이터 등록
        model.addAttribute("member", memberEntity);

        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        List<Member> memberEntityList = memberRepository.findAll();

        model.addAttribute("memberList", memberEntityList);

        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        //1. Find Data in DB
        Member memberEntity = memberRepository.findById(id).orElse(null);

        //2. add Data in model
        model.addAttribute("member", memberEntity);

        return "/members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) {
        //1. DTO -> Entity
        Member memberEntity = form.toEntity();

        //2. Entity -> DB
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);

        if (target != null) {
            memberRepository.save(memberEntity);
        }

        //3. Return
        return "redirect:/members/" + memberEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        //1. find Data
        Member target = memberRepository.findById(id).orElse(null);

        //2. Delete Data
        if (target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다!");
        }

        //3. Redirect
        return "redirect:/members";
    }
}
