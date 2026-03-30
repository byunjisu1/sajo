package com.myspringboot.sajo.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentRestController {
	@Autowired
	private PaymentService paySvc;
	
	// 결제 성공 -> 결제, 주문 테이블에 정보 추가
	@PostMapping("/payment")
	public void successPayment(@RequestBody PaymentRequestDto dto) {
		paySvc.successPayment(dto);
	}
}
