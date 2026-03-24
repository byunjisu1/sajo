package com.myspringboot.sajo.member;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepo;
	
	public MemberDto getMemberHeaderProfile(Integer memberNo) {
		Optional<Member> om = memberRepo.findById(memberNo);
		if(om.isEmpty()) {
			return null;
		}
		
		return new MemberDto(om.get());
	}
	/*
	 * getMemberUpdateProfile : 회원정보수정 페이지에서 필요한 회원정보 불러오기
	 * input : memberNo
	 * output : member테이블의 모든 컬럼 
	 */
	public MemberUpdateDto getMemberUpdateProfile(Integer memberNo) {
		Optional<Member> om = memberRepo.findById(memberNo);
		if(om.isEmpty()) {
			return null;
		}
		
		return new MemberUpdateDto(om.get());
	}

}
