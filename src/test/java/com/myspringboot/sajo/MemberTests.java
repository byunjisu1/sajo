package com.myspringboot.sajo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberDto;
import com.myspringboot.sajo.member.MemberRepository;
import com.myspringboot.sajo.member.MemberService;
import com.myspringboot.sajo.member.MemberUpdateDto;

@SpringBootTest
public class MemberTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private MemberService mSvc;
	
	// Member 테이블 더미데이터 추가
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
		
		m = new Member();
		m.setNickname("지수");
		m.setEmail("0902jisu@naver.com");
		m.setNameKor("변지수");
		m.setNameEng("BYUNJISU");
		m.setBirth("20020902");
		m.setPhone("01071879350");
		memberRepo.save(m);
		
		m = new Member();
		m.setNickname("배승빈");
		m.setEmail("seungbin4369@gmail.com");
		m.setNameKor("배승빈");
		m.setNameEng("BAESEUNGBIN");
		m.setBirth("19960226");
		m.setPhone("01047056832");
		memberRepo.save(m);
	}
	
	// memberNo에 해당하는 Header Profile 가져오기
	@Test
	void testMemberHeaderProfile() {
		//Given
		int memberNo = 2;
		//When
		MemberDto dto = mSvc.getMemberHeaderProfile(memberNo);
		//Then
		System.out.println(dto.getNickname() + " : " + dto.getProfileImg());
	}
	
	// 회원정보 가져오기 
	@Test
	void testGetMemberUpdateProfile() {
		//Given
		int MemberNo = 1;
		//When
		MemberUpdateDto dto = mSvc.getMemberUpdateProfile(MemberNo);
		//Then
		System.out.println(dto.getBirth() + " : " + "생년월일" + dto.getEmail() + " : " + "이메일");
		 
	}
	
	// memberNo에 해당하는 회원정보수정하기
	@Test
	@Transactional
	void testUpdateMemberProfile() {
		//Given
		int memberNo = 2;
		MemberUpdateDto dto = new MemberUpdateDto();
		dto.setNickname("민재짱");
		//When
		mSvc.modifyMemberProfile(memberNo, dto);
		//Then
		System.out.println(" 수정된 닉네임 : "+ dto.getNickname());
	}
}
