package com.myspringboot.sajo.payment;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.item.ItemRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;
import com.myspringboot.sajo.order.OrderDetail;
import com.myspringboot.sajo.order.OrderDetailItemDto;
import com.myspringboot.sajo.order.OrderDetailRepository;
import com.myspringboot.sajo.order.Orders;
import com.myspringboot.sajo.order.OrdersRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class PaymentService {
	@Autowired
	private OrdersRepository orderRepo;
	@Autowired
	private PaymentRepository paymentRepo;
	@Autowired
	private OrderDetailRepository orderDetailRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ItemRepository itemRepo;
	@PersistenceContext // JPA 관리자 주입
    private EntityManager em;
	
	// 결제 성공 후 DB 정보 저장
	@Transactional
	public void successPayment(PaymentRequestDto dto) {
		System.out.println("주문번호 체크: " + dto.getOrderNo());
		Member m = memberRepo.findById(dto.getMemberNo()).get();
		Orders order = new Orders();
		order.setOrderNo(dto.getOrderNo());
		order.setOrdersMemberNo(m);
		order.setTotalPrice(dto.getTotalAmount());
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus("order_completed");
		//orderRepo.save(order);
		//em.persist(order);
		Orders savedOrder = em.merge(order);
		
		Payment p = new Payment();
		p.setMemberNo(m);
		p.setOrderNo(savedOrder);
		p.setImpUid(dto.getImpUid());
		p.setAmount(dto.getTotalAmount());
		p.setStatus("success");
		p.setPaymentTime(LocalDateTime.now());
		paymentRepo.save(p);
		
		for(OrderDetailItemDto id : dto.getItems()) {
			OrderDetail od = new OrderDetail();
			od.setOrderNo(savedOrder);
			Item i = itemRepo.findById(id.getItemIdx()).get();
			od.setItemIdx(i);
			od.setQty(id.getQty());
			od.setOrderPrice(id.getOrderPrice());
			orderDetailRepo.save(od);
		}
	}
}
