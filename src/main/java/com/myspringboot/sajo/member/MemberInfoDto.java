package com.myspringboot.sajo.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDto {
	private int memberNo;       
    private String nickname;   
    private String email;       
    private String nameKor;   
    private String nameEng;      
    private String birth;        
    private String phone;        
    private String profileImg;   
    private String snsType;      // SNS_TYPE (varchar2) - 'google' 또는 'kakao'
    private String accessToken;  
    private String customsId;  	//통관번호
}
