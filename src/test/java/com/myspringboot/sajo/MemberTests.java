package com.myspringboot.sajo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.member.AddressRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@SpringBootTest
public class MemberTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private AddressRepository addressRepo;
	
	
	/**
	 * testInsertMember : Member 테이블 더미데이터 추가
	 */
	@Test
	void testInsertMember() {
		// Given
		Member m = new Member();
		m.setNickname("김민재");
		m.setEmail("lim82378@gmail.com");
		m.setNameKor("김민재");
		m.setNameEng("KIMMINJAE");
		m.setBirth("20000402");
		m.setPhone("01038218237");
		
		// When
		memberRepo.save(m);
		
		// Then
	}
	
	@Test
	void testInsertMember2() {
		// Given
		Member m = new Member();
		m.setNickname("지수");
		m.setEmail("0902jisu@naver.com");
		m.setNameKor("변지수");
		m.setNameEng("BYUNJISU");
		m.setBirth("20020902");
		m.setPhone("01071879350");
		
		// When
		memberRepo.save(m);
		
		// Then
	}
	
	@Test
	void testInsertMember3() {
		// Given
		Member m = new Member();
		m.setNickname("배승빈");
		m.setEmail("seungbin4369@gmail.com");
		m.setNameKor("배승빈");
		m.setNameEng("BAESEUNGBIN");
		m.setBirth("19960226");
		m.setPhone("01047056832");
		
		// When
		memberRepo.save(m);
		
		// Then
	}
	
	/**
	 * testInsertAddress : Address 테이블 더미데이터 추가
	 */
	@Test
	void testInsertAddress() {
		// Given
		
	}
}
