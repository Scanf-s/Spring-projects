package com.sullung.myfirstspringbootproject.service;

import com.sullung.myfirstspringbootproject.model.Member;
import com.sullung.myfirstspringbootproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final MemberRepository memberRepository;

    @Autowired
    public TestService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

}
