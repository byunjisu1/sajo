package com.myspringboot.sajo;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.item.ItemRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;
import com.myspringboot.sajo.order.OrderDetail;
import com.myspringboot.sajo.order.OrderDetailRepository;
import com.myspringboot.sajo.order.OrderHistoryDto;
import com.myspringboot.sajo.order.Orders;
import com.myspringboot.sajo.order.OrdersRepository;
import com.myspringboot.sajo.order.OrdersService;

@SpringBootTest
public class OrderTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private OrdersRepository ordersRepo;
	@Autowired
	private OrderDetailRepository oDetailRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private OrdersService ordersSvc;
	// Orders 테이블 더미데이터 추가
	@Test
	void testInsertOrders() {
		Member m = memberRepo.findById(1).get();
		Orders o = new Orders();
		o.setOrderNo("202603161111");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(520000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_ready");
		ordersRepo.save(o);
		
		m = memberRepo.findById(2).get();
		o = new Orders();
		o.setOrderNo("202603161112");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(730000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_completed");
		ordersRepo.save(o);
		
		m = memberRepo.findById(3).get();
		o = new Orders();
		o.setOrderNo("202603161113");
		o.setOrdersMemberNo(m);
		o.setTotalPrice(1775000);
		o.setOrderDate(LocalDateTime.now());
		o.setOrderStatus("order_completed");
		ordersRepo.save(o);
	}
	
	// Order_detail 테이블 더미데이터 추가
	@Test
	void testInsertOrderDetail() {
		Orders o = ordersRepo.findById("202603161111").get();
		Item i = itemRepo.findById(1).get();
		OrderDetail od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(2);
		od.setOrderPrice(30000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161111").get();
		i = itemRepo.findById(2).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(2);
		od.setOrderPrice(23000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161112").get();
		i = itemRepo.findById(3).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(1);
		od.setOrderPrice(230000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161112").get();
		i = itemRepo.findById(4).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(2);
		od.setOrderPrice(250000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161113").get();
		i = itemRepo.findById(5).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(1);
		od.setOrderPrice(1430000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161113").get();
		i = itemRepo.findById(6).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(1);
		od.setOrderPrice(120000);
		oDetailRepo.save(od);
		
		o = ordersRepo.findById("202603161113").get();
		i = itemRepo.findById(7).get();
		od = new OrderDetail();
		od.setOrderNo(o);
		od.setItemIdx(i);
		od.setQty(3);
		od.setOrderPrice(75000);
		oDetailRepo.save(od);
	}
	@Test
	// 주문내역가져오기 Repository 테스트 
	public void testFindOrderHistory() {
		//Given
		int memberNo = 1;
		//When
		List<Object[]> list = ordersRepo.findOrderHistory(memberNo);
		//Then
		for(Object[] ob : list) {
			System.out.println("주문날짜 : " + ob[0] + "주문번호 : " + ob[1] );
			
		}
	}
	@Test
	// memberNo에 해당하는 주문내역 가져오기 
	public void testGetOrderHistoryList() {
		//Given
		int memberNo = 1;
		//When
		List<OrderHistoryDto> orderList = ordersSvc.getOrderList(memberNo);
		//Then
		
		for(OrderHistoryDto dto : orderList) {
			System.out.println(  "아이템품명 : "+ dto.getItemName() + " 주문날짜 : " + dto.getOrderDate() );
		}
		
	}
}
