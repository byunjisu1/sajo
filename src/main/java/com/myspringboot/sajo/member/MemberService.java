package com.myspringboot.sajo.member;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	/*
	 * modifyMemberProfile : 회원정보수정 페이지에서 수정하기 
	 * input : memberNo
	 * output : member테이블의 모든 컬럼 
	 */
	public void modifyMemberProfile(Integer memberNo, MemberUpdateDto dto) {

		Optional<Member> om = memberRepo.findById(memberNo);
		
		if(om.isEmpty()) {
			return;
		}
		Member m = om.get();
		
		m.setNickname(dto.getNickname());
		m.setNameKor(dto.getNameKor());
		m.setNameEng(dto.getNameEng());
		
		MultipartFile file = dto.getUploadFile(); // 파일 이미지 업로드 객체 
		if(file!=null && !file.isEmpty()) {
			String path = "/Users/baeseungbin/sajo_uploads";
			String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			
			try {
				file.transferTo(new File(path, savedName));
				m.setProfileImg(savedName);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		memberRepo.save(m);
	}

}
