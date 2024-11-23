package com.example.SpringBoot3.service;

import com.example.SpringBoot3.dto.MemberForm;
import com.example.SpringBoot3.entity.Member;
import com.example.SpringBoot3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> index() {
        return memberRepository.findAll();
    }

    public Member show(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member create(MemberForm dto) {
        //1. DTO -> Entity
        Member memberEntity = dto.toEntity();

        //2. Entity -> DB
        return memberRepository.save(memberEntity);
    }

    public Member update(Long id, MemberForm dto) {
        //1. dto -> Entity
        Member memberEntity = dto.toEntity();

        //2. find data in DB
        Member target = memberRepository.findById(id).orElse(null);

        //3. invalid request
        if (target == null || id != target.getId()) {
            return null;
        }

        //4. update
        target.patch(memberEntity);
        return memberRepository.save(target);
    }

    public Member delete(Long id) {
        //1. find data in DB
        Member target = memberRepository.findById(id).orElse(null);

        //2. invalid requests
        if (target == null || id != target.getId()) {
            return null;
        }

        //3. return
        memberRepository.delete(target);
        return target;
    }
}
