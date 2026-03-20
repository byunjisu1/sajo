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
public class BellMsg {
	@Id
	@Column(columnDefinition="VARCHAR2(10 BYTE)")
	private String bellType;
	
	@Column(columnDefinition="VARCHAR2(1000 BYTE)")
	private String bellMsg;
}
