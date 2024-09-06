package com.sullung.myfirstspringbootproject.repository;

import com.sullung.myfirstspringbootproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
}