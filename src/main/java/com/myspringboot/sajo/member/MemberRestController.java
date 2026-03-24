package com.myspringboot.sajo.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.sajo.cart.CartDto;
import com.myspringboot.sajo.cart.CartService;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService mSvc;
	@Autowired
	private CartService cartSvc;
	
	@GetMapping("/member/{memberNo}")
	public MemberDto memberHeaderProfile(@PathVariable("memberNo") Integer memberNo) {
		MemberDto dto = mSvc.getMemberHeaderProfile(memberNo);
		
		return dto;
	}
	
	//CartList 조회하는 메서드
	@GetMapping("/cart/{memberNo}")
	public List<CartDto> cartList(@PathVariable("memberNo") Integer memberNo) {
		List<CartDto> dto = cartSvc.getCartList(memberNo);
		if(dto.isEmpty()) {
			System.out.println("장바구니 없음");
		}else {
			System.out.println("뭔가 있음");
		}
		return dto;
	}
}
