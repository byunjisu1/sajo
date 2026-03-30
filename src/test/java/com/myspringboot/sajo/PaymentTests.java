package com.myspringboot.sajo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;
import com.myspringboot.sajo.order.OrderDetailItemDto;
import com.myspringboot.sajo.order.Orders;
import com.myspringboot.sajo.order.OrdersRepository;
import com.myspringboot.sajo.payment.Payment;
import com.myspringboot.sajo.payment.PaymentRepository;
import com.myspringboot.sajo.payment.PaymentRequestDto;
import com.myspringboot.sajo.payment.PaymentService;

import jakarta.transaction.Transactional;

@SpringBootTest
public class PaymentTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private OrdersRepository ordersRepo;
	@Autowired
	private PaymentRepository payRepo;
	@Autowired
	private PaymentService pSvc;
	
	// Payment 테이블 더미데이터 추가
	@Test
	void testInsertPayment() {
		Member m = memberRepo.findById(1).get();
		Orders o = ordersRepo.findById("202603161111").get();
		Payment pm = new Payment();
		pm.setMemberNo(m);
		pm.setOrderNo(o);
		pm.setImpUid("asdfqwer");
		pm.setAmount(120000);
		pm.setStatus("fail");
		pm.setPaymentTime(LocalDateTime.now());
		payRepo.save(pm);
		
		m = memberRepo.findById(2).get();
		o = ordersRepo.findById("202603161112").get();
		pm = new Payment();
		pm.setMemberNo(m);
		pm.setOrderNo(o);
		pm.setImpUid("asdasda");
		pm.setAmount(200000);
		pm.setStatus("success");
		pm.setPaymentTime(LocalDateTime.now());
		payRepo.save(pm);
		
		m = memberRepo.findById(3).get();
		o = ordersRepo.findById("202603161113").get();
		pm = new Payment();
		pm.setMemberNo(m);
		pm.setOrderNo(o);
		pm.setImpUid("qwewqeq");
		pm.setAmount(350000);
		pm.setStatus("success");
		pm.setPaymentTime(LocalDateTime.now());
		payRepo.save(pm);
	}
	
	// 결제 성공 시, DB에 정보 저장
	@Test
	@Transactional
	void testSuccessPayment() {
		// Given
		PaymentRequestDto dto = new PaymentRequestDto();
		dto.setMemberNo(1);
		dto.setOrderNo("20260330888");
		dto.setTotalAmount(50000);
		dto.setImpUid("123456");
		
		List<OrderDetailItemDto> itemList = new ArrayList<>();
		// 첫 번째 상품
	    OrderDetailItemDto item1 = new OrderDetailItemDto();
	    item1.setItemIdx(1); 
	    item1.setQty(2);  
	    item1.setOrderPrice(20000);
	    itemList.add(item1);

	    // 두 번째 상품
	    OrderDetailItemDto item2 = new OrderDetailItemDto();
	    item2.setItemIdx(2); 
	    item2.setQty(1);
	    item2.setOrderPrice(10000);
	    itemList.add(item2);
	    
		dto.setItems(itemList);
		
		// When
		pSvc.successPayment(dto);
		
		// Then
		System.out.println("정보 저장 및 트랜잭션 검증 완료");
	}
}