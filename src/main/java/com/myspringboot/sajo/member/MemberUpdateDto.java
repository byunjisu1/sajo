package com.myspringboot.sajo.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MemberUpdateDto {
	private String birth;
	private String email;
	private String nameEng;
	private String nameKor;
	private String nickname;
	private String phone;
	
	public MemberUpdateDto(Member member) {
		this.birth = member.getBirth();
		this.email = member.getEmail();
		this.nameEng = member.getNameEng();
		this.nameKor = member.getNameKor();
		this.nickname = member.getNickname();
		this.phone = member.getPhone();
		if (member.getPhone() != null && member.getPhone().length() == 11) {
	        this.phone = member.getPhone().replaceAll("(\\d{3})(\\d{4})(\\d{4})", "$1-$2-$3");
	    } else {
	        this.phone = member.getPhone();
	    }
		if (member.getBirth() != null && member.getBirth().length() == 8) {
	        this.birth = member.getBirth().replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$1-$2-$3");
	    } else {
	        this.birth = member.getBirth();
	    }
		
	}
}
