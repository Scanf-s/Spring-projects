package com.sullung.myfirstspringbootproject.repository;

import com.sullung.myfirstspringbootproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}