package com.myspringboot.sajo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.member.Address;
import com.myspringboot.sajo.member.AddressRepository;
import com.myspringboot.sajo.member.BellMsgRepository;
import com.myspringboot.sajo.member.BellRepository;
import com.myspringboot.sajo.member.CartRepository;
import com.myspringboot.sajo.member.LikesRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;
import com.myspringboot.sajo.member.OrderDetail;
import com.myspringboot.sajo.member.OrderDetailRepository;
import com.myspringboot.sajo.member.Orders;
import com.myspringboot.sajo.member.OrdersRepository;
import com.myspringboot.sajo.member.SearchRepository;

@SpringBootTest
public class MemberTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private OrdersRepository ordersRepo;
	@Autowired
	private OrderDetailRepository oDetailRepo;
	@Autowired
	private LikesRepository likeRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private SearchRepository searchRepo;
	@Autowired
	private BellRepository bellRepo;
	@Autowired
	private BellMsgRepository bellMsgRepo;
	
	/**
	 * testInsertMember : Member 테이블 더미데이터 추가
	 */
	@Test
	void testInsertMember() {
		// Given
		Member m = new Member();
		m.setNickname("김민재");
		m.setEmail("lim82378@gmail.com");
		m.setNameKor("김민재");
		m.setNameEng("KIMMINJAE");
		m.setBirth("20000402");
		m.setPhone("01038218237");
		
		// When
		memberRepo.save(m);
		
		// Then
	}
	
	@Test
	void testInsertMember2() {
		// Given
		Member m = new Member();
		m.setNickname("지수");
		m.setEmail("0902jisu@naver.com");
		m.setNameKor("변지수");
		m.setNameEng("BYUNJISU");
		m.setBirth("20020902");
		m.setPhone("01071879350");
		
		// When
		memberRepo.save(m);
		
		// Then
	}
	
	@Test
	void testInsertMember3() {
		// Given
		Member m = new Member();
		m.setNickname("배승빈");
		m.setEmail("seungbin4369@gmail.com");
		m.setNameKor("배승빈");
		m.setNameEng("BAESEUNGBIN");
		m.setBirth("19960226");
		m.setPhone("01047056832");
		
		// When
		memberRepo.save(m);
		
		// Then
	}
	
	/**
	 * testInsertAddress : Address 테이블 더미데이터 추가
	 */
	@Test
	void testInsertAddress() {
		// Given
		Member m = memberRepo.findById(1).get();
		Address a = new Address();
		a.setAddressMemberNo(m);
		a.setAddress("문성로 144번길 아이티아파트 103동 803호");
		a.setPostCode("14563");
		
		// When
		addressRepo.save(a);
		
		// Then
	}
	
	@Test
	void testInsertAddress2() {
		// Given
		Member m = memberRepo.findById(2).get();
		Address a = new Address();
		a.setAddressMemberNo(m);
		a.setAddress("신촌로 51-8 코리아아이티");
		a.setPostCode("84721");
		
		// When
		addressRepo.save(a);
		
		// Then
	}
	
	@Test
	void testInsertAddress3() {
		// Given
		Member m = memberRepo.findById(3).get();
		Address a = new Address();
		a.setAddressMemberNo(m);
		a.setAddress("창천로 88번길 ");
		a.setPostCode("12345");
		
		// When
		addressRepo.save(a);
		
		// Then
	}
	
	/**
	 * testInsertOrders : ORDERS 테이블 더미데이터 추가
	 */
	@Test
	void testInsertOrders() {
		// Given
		Member m = memberRepo.findById(1).get();
		Orders o = new Orders();
		o.setOrderNo("202603161111");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(520000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_ready");
		
		// When
		ordersRepo.save(o);
		
		// Then
	}
	
	@Test
	void testInsertOrders2() {
		// Given
		Member m = memberRepo.findById(2).get();
		Orders o = new Orders();
		o.setOrderNo("202603161112");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(730000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_completed");
		
		// When
		ordersRepo.save(o);
		
		// Then
	}
	
	@Test
	void testInsertOrders3() {
		// Given
		Member m = memberRepo.findById(3).get();
		Orders o = new Orders();
		o.setOrderNo("202603161113");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(1775000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_completed");
		
		// When
		ordersRepo.save(o);
		
		// Then
	}
	
	/**
	 * testInsertOrderDetail : Order_detail 테이블 더미데이터 추가
	 */
//	@Test
//	void testInsertOrderDetail() {
//		// Given
//		Orders o = ordersRepo.findById("202603161111").get();
//		OrderDetail od = new OrderDetail();
//		od.setOrderNo(o);
//		od.setItemIdx(0);
//		od.setQty(2);
//		od.setOrderPrice(30000);
//		
//		// When
//		oDetailRepo.save(od);
//		
//		// Then
//	}
//	
//	@Test
//	void testInsertOrderDetail2() {
//		// Given
//		Orders o = ordersRepo.findById("202603161111").get();
//		OrderDetail od = new OrderDetail();
//		od.setOrderNo(o);
//		od.setItemIdx(0);
//		od.setQty(2);
//		od.setOrderPrice(23000);
//		
//		// When
//		oDetailRepo.save(od);
//		
//		// Then
//	}
//	
//	@Test
//	void testInsertOrderDetail3() {
//		// Given
//		Orders o = ordersRepo.findById("202603161112").get();
//		OrderDetail od = new OrderDetail();
//		od.setOrderNo(o);
//		od.setItemIdx(0);
//		od.setQty(1);
//		od.setOrderPrice(230000);
//		
//		// When
//		oDetailRepo.save(od);
//		
//		// Then
//	}
//	
//	@Test
//	void testInsertOrderDetail4() {
//		// Given
//		Orders o = ordersRepo.findById("202603161112").get();
//		OrderDetail od = new OrderDetail();
//		od.setOrderNo(o);
//		od.setItemIdx(0);
//		od.setQty(2);
//		od.setOrderPrice(250000);
//		
//		// When
//		oDetailRepo.save(od);
//		
//		// Then
//	}
//	
//	@Test
//	void testInsertOrderDetail5() {
//		// Given
//		Orders o = ordersRepo.findById("202603161113").get();
//		OrderDetail od = new OrderDetail();
//		od.setOrderNo(o);
//		od.setItemIdx(0);
//		od.setQty(1);
//		od.setOrderPrice(1430000);
//		
//		// When
//		oDetailRepo.save(od);
//		
//		// Then
//	}
//	
//	@Test
//	void testInsertOrderDetail6() {
//		// Given
//		Orders o = ordersRepo.findById("202603161113").get();
//		OrderDetail od = new OrderDetail();
//		od.setOrderNo(o);
//		od.setItemIdx(0);
//		od.setQty(1);
//		od.setOrderPrice(120000);
//		
//		// When
//		oDetailRepo.save(od);
//		
//		// Then
//	}
//	
//	@Test
//	void testInsertOrderDetail7() {
//		// Given
//		Orders o = ordersRepo.findById("202603161113").get();
//		OrderDetail od = new OrderDetail();
//		od.setOrderNo(o);
//		od.setItemIdx(0);
//		od.setQty(3);
//		od.setOrderPrice(75000);
//		
//		// When
//		oDetailRepo.save(od);
//		
//		// Then
//	}
}
