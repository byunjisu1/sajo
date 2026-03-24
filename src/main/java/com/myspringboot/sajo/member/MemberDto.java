package com.myspringboot.sajo.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
	private String nickname;
	private String profile_img;
	
	public MemberDto(Member member) {
		this.nickname = member.getNickname();
		this.profile_img = member.getProfileImg();
	}
}
