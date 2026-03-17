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
	private Integer member_no;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String nickname;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String email;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String name_kor;
	
	@Column(columnDefinition="VARCHAR2(50 BYTE)")
	private String name_eng;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String birth;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String phone;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String profile_img;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String sns_type;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String access_token;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String customs_id;
}
