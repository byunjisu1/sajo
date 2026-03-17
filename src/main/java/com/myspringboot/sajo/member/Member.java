package com.myspringboot.sajo.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqMem")
	@SequenceGenerator(name="seqMem", sequenceName="seq_member_no", allocationSize=1)
	private Integer memberNo;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String nickname;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String email;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String nameKor;
	
	@Column(columnDefinition="VARCHAR2(50 BYTE)")
	private String nameEng;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String birth;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String phone;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String profileImg;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String snsType;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String accessToken;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String customsId;
}
