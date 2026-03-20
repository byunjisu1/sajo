package com.myspringboot.sajo.order;

import java.time.LocalDateTime;

import com.myspringboot.sajo.member.Member;

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
public class Orders {
	@Id
	@Column(columnDefinition="VARCHAR2(1000 BYTE)")
	private String orderNo;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	private Member ordersMemberNo;
	
	private int totalPrice;
	
	private LocalDateTime orderDate;
	
	@Column(columnDefinition="VARCHAR2(100 BYTE)")
	private String orderStatus;
}
