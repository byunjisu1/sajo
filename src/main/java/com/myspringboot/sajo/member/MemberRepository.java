package com.myspringboot.sajo.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	// 이메일로 찾기 추가
    Optional<Member> findByEmail(String email);
}
