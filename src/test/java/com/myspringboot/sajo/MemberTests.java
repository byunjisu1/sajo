package com.myspringboot.sajo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@SpringBootTest
public class MemberTests {
	@Autowired
	private MemberRepository memberRepo;
	
	/**
	 * testInsertMember : Member 테이블 더미데이터 추가
	 */
	@Test
	void testInsertMember() {
		Member m = new Member();
		m.setNickname("김민재");
		m.setEmail("lim82378@gmail.com");
		m.setNameKor("김민재");
		m.setNameEng("KIMMINJAE");
		m.setBirth("20000402");
		m.setPhone("01038218237");
		memberRepo.save(m);
	}
}
