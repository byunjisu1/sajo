package com.myspringboot.sajo.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqAdd")
	@SequenceGenerator(name="seqAdd", sequenceName="seq_address_idx", allocationSize=1)
	private Integer addressIdx;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	private Member addressMemberNo;
	
	@Column(columnDefinition="VARCHAR2(2000 BYTE)")
	private String address;
	
	@Column(columnDefinition="VARCHAR2(20 BYTE)")
	private String postCode;
}
