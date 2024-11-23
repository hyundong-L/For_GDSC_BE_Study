package com.example.SpringBoot3.api;

import com.example.SpringBoot3.dto.MemberForm;
import com.example.SpringBoot3.entity.Member;
import com.example.SpringBoot3.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberApiController {
    @Autowired
    private MemberService memberService;

    //GET
    //전체 회원 조회
    @GetMapping("/api/members")
    public List<Member> index() {
        return memberService.index();
    }

    //단일 회원 조회
    @GetMapping("/api/members/{id}")
    public Member show(@PathVariable Long id) {
        return memberService.show(id);
    }

    //POST
    @PostMapping("/api/members")
    public ResponseEntity<Member> create(@RequestBody MemberForm dto) {
        Member created = memberService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/members/{id}")
    public ResponseEntity<Member> update(@PathVariable Long id, @RequestBody MemberForm dto) {
        Member updated = memberService.update(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<Member> delete(@PathVariable Long id) {
        Member deleted = memberService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
