package com.myspringboot.sajo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myspringboot.sajo.cart.CartDto;
import com.myspringboot.sajo.cart.CartRepository;
import com.myspringboot.sajo.cart.CartService;

@SpringBootTest
public class CartTest {
	@Autowired
	private CartRepository cartRepo;
	@Autowired CartService cartSvc;
	/**
	 * CartListTest() : 회원번호에 따른 장바구니 내역조회
	 * input : member_no
	 * output : 회원번호에 따른 상품 이름, 가격, 이미지
	 */
	@Test
	public void CartListTest() {
		//Give
        
        //When
		List<CartDto> dto = cartSvc.getCartList(1);
		
        //Then
		if(dto.isEmpty()) {
			System.out.println("장바구니가 비어있습니다.");
		} else {
			dto.forEach(item -> {
				System.out.println("장바구니 번호: " + item.getCartIdx());
				System.out.println("상품 이미지: " + item.getItemImg());
				System.out.println("상품명: " + item.getItemName());
				System.out.println("가격: " + item.getItemPrice());
				System.out.println("--------------------");
			});
		}
	}
}
