package com.sullung.myfirstspringbootproject.repository;

import com.sullung.myfirstspringbootproject.model.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void cleanUp() {
        memberRepository.deleteAll();
    }

    @Sql("classpath:/insert-members.sql")
    @Test
    public void getAllMembers() {
        //when
        List<Member> members = memberRepository.findAll();

        //then
        Assertions.assertThat(members.size()).isEqualTo(10);
    }

    @Sql("classpath:/insert-members.sql")
    @Test
    public void getMemberById() {
        //when
        Member member = memberRepository.findById(2L).get();

        //then
        Assertions.assertThat(member.getName()).isEqualTo("Bob");
    }

    @Sql("classpath:/insert-members.sql")
    @Test
    public void getMemberByName() {
        //when
        Member member = memberRepository.findByName("Hank").get();

        //then
        Assertions.assertThat(member.getName()).isEqualTo("Hank");
    }

    @Test
    public void saveMember() {
        //given
        Member newMember = new Member("Sullung");

        //when
        memberRepository.save(newMember);

        //then
        Assertions.assertThat(memberRepository.findByName(newMember.getName()).get().getName()).isEqualTo("Sullung");
    }

    @Test
    public void saveMembers() {
        //given
        Member newMember1 = new Member("Sullung1");
        Member newMember2 = new Member("Sullung2");
        Member newMember3 = new Member("Sullung3");

        //when
        memberRepository.saveAll(List.of(newMember1, newMember2, newMember3));

        //then
        Assertions.assertThat(memberRepository.findByName(newMember1.getName()).get().getName()).isEqualTo("Sullung1");
        Assertions.assertThat(memberRepository.findByName(newMember2.getName()).get().getName()).isEqualTo("Sullung2");
        Assertions.assertThat(memberRepository.findByName(newMember3.getName()).get().getName()).isEqualTo("Sullung3");
    }

    @Test
    public void deleteMember() {
        //given
        Member newMember = new Member("Sullung");
        memberRepository.save(newMember);

        //when
        memberRepository.deleteById(newMember.getId());

        //then
        Assertions.assertThat(memberRepository.findByName(newMember.getName())).isEmpty();
    }

    @Sql("classpath:/insert-members.sql")
    @Test
    public void updateMember() {
        // given
        Member member = memberRepository.findById(5L).get();

        // when
        member.changeName("Sullung");

        // then
        Assertions.assertThat(memberRepository.findById(5L).get().getName()).isEqualTo("Sullung");
    }

}