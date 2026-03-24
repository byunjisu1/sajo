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

}
