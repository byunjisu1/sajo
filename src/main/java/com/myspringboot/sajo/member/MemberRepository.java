package com.myspringboot.sajo.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	// 이메일로 찾기 추가
    Optional<Member> findByEmail(String email);
    // 엑세스토큰으로 중복여부체크 
    Optional<Member> findByAccessToken(String access_token);
}
