package com.myspringboot.sajo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.cart.Cart;
import com.myspringboot.sajo.cart.CartRepository;
import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.item.ItemRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@SpringBootTest
public class CartTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ItemRepository itemRepo;
	
	// Cart 테이블 더미데이터 추가
	@Test
	void testInsertCart() {
		Member m = memberRepo.findById(1).get();
		Item i = itemRepo.findById(3).get();
		Cart c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
		
		m = memberRepo.findById(1).get();
		i = itemRepo.findById(4).get();
		c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
		
		m = memberRepo.findById(2).get();
		i = itemRepo.findById(1).get();
		c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
		
		m = memberRepo.findById(2).get();
		i = itemRepo.findById(2).get();
		c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
		
		m = memberRepo.findById(3).get();
		i = itemRepo.findById(4).get();
		c = new Cart();
		c.setCartMemberNo(m);
		c.setCartItemIdx(i);
		cartRepo.save(c);
	}
}
