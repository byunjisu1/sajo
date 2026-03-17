package com.myspringboot.sajo.member;

import java.time.LocalDateTime;

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
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqOrder")
	@SequenceGenerator(name="seqOrder", sequenceName="seq_order_no", allocationSize=1)
	@Column(columnDefinition="VARCHAR2(1000 BYTE)")
	private String order_no;
	
	@ManyToOne
	@JoinColumn(name="member_no")
	Member memberNo;
	
	private int total_price;
	
	private LocalDateTime order_date;
	
	@Column(columnDefinition="VARCHAR2(100 BYTE)")
	private String order_status;
}
