package com.myspringboot.sajo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;
import com.myspringboot.sajo.member.Orders;
import com.myspringboot.sajo.member.OrdersRepository;
import com.myspringboot.sajo.payment.Payment;
import com.myspringboot.sajo.payment.PaymentRepository;

@SpringBootTest
public class PaymentTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private OrdersRepository ordersRepo;
	@Autowired
	private PaymentRepository payRepo;
	
	/**
	 * testInsertPayment : Payment 테이블 더미데이터 추가
	 */
	@Test
	void testInsertPayment1() {
		// Given
		Member m = memberRepo.findById(1).get();
		Orders o = ordersRepo.findById("202603161111").get();
		Payment pm = new Payment();
		pm.setMemberNo(m);
		pm.setOrderNo(o);
		pm.setTid("asdfqwer");
		pm.setAmount(120000);
		pm.setStatus("fail");
		pm.setPaymentTime(LocalDateTime.now());
		
		// When
		payRepo.save(pm);
		
		// Then
	}
	
	@Test
	void testInsertPayment2() {
		// Given
		Member m = memberRepo.findById(2).get();
		Orders o = ordersRepo.findById("202603161112").get();
		Payment pm = new Payment();
		pm.setMemberNo(m);
		pm.setOrderNo(o);
		pm.setTid("asdasda");
		pm.setAmount(200000);
		pm.setStatus("success");
		pm.setPaymentTime(LocalDateTime.now());
		
		// When
		payRepo.save(pm);
		
		// Then
	}
	
	@Test
	void testInsertPayment3() {
		// Given
		Member m = memberRepo.findById(3).get();
		Orders o = ordersRepo.findById("202603161113").get();
		Payment pm = new Payment();
		pm.setMemberNo(m);
		pm.setOrderNo(o);
		pm.setTid("qwewqeq");
		pm.setAmount(350000);
		pm.setStatus("success");
		pm.setPaymentTime(LocalDateTime.now());
		
		// When
		payRepo.save(pm);
		
		// Then
	}
}