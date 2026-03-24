package com.myspringboot.sajo.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.sajo.likes.LikesService;
import com.myspringboot.sajo.likes.WishListDto;
import com.myspringboot.sajo.cart.CartDto;
import com.myspringboot.sajo.cart.CartService;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService mSvc;
	@Autowired
	private LikesService lSvc;
	private CartService cartSvc;
	
	@GetMapping("/member/{memberNo}")
	public MemberDto memberHeaderProfile(@PathVariable("memberNo") Integer memberNo) {
		MemberDto dto = mSvc.getMemberHeaderProfile(memberNo);
		
		return dto;
	}
	
	// 회원에 대한 찜 내역 불러오기
	@GetMapping("/likes/{memberNo}")
	public List<WishListDto> memberWishList(@PathVariable("memberNo") Integer memberNo, @RequestParam(value="sort", defaultValue="latest") String sortType) {
		List<WishListDto> list = lSvc.getWishList(memberNo, sortType);
		
		return list;
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
