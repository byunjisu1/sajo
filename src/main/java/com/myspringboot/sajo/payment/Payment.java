package com.myspringboot.sajo.payment;

import java.time.LocalDateTime;

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
public class Payment {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqPay")
	@SequenceGenerator(name="seqPay", sequenceName="seq_payment_idx", allocationSize=1)
	private Integer payment_idx;
	
	private int memberNo;
	
	private int orderNo;
	
	@Column(columnDefinition="VARCHAR2(100 BYTE)")
	private String tid;
	
	private int amount;
	
	@Column(columnDefinition="VARCHAR2(500 BYTE)")
	private String status;
	
	private LocalDateTime paymentTime;
	
}
