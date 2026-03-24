package com.myspringboot.sajo.member;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService mSvc;
	@Autowired
	private MemberRepository mRepo;
	
	@GetMapping("/member/{memberNo}")
	public MemberDto memberHeaderProfile(@PathVariable("memberNo") Integer memberNo) {
		MemberDto dto = mSvc.getMemberHeaderProfile(memberNo);
		
		return dto;
	}
	@GetMapping("/memberUpdate/{memberNo}")
	public MemberUpdateDto memberUpdateProfile(@PathVariable("memberNo") Integer memberNo) {
		MemberUpdateDto dto = mSvc.getMemberUpdateProfile(memberNo);
		
		return dto;
	}
	@PutMapping("/modify/{memberNo}")
	public void memberModify(@RequestBody MemberUpdateDto dto,@PathVariable("memberNo") Integer memberNo) {
		
		Optional<Member> om = mRepo.findById(memberNo);
		
		if(om.isEmpty()) {
			return;
		}
		Member m = om.get();
		
		m.setNickname(dto.getNickname());
		m.setNameKor(dto.getNameKor());
		m.setNameEng(dto.getNameEng());
		
		mRepo.save(m);
	}
}
