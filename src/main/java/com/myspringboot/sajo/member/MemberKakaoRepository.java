package com.myspringboot.sajo.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberKakaoRepository extends JpaRepository<MemberKakao, Integer> {
	List<MemberKakao> findByKakaoId(Long kakaoId);
	
}
