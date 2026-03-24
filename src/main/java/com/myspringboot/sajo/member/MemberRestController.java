package com.myspringboot.sajo.member;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@Autowired
	private CartService cartSvc;
	@Autowired
	private MemberRepository mRepo;
	
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
	// 회원정보 가져오기 
	@GetMapping("/memberUpdate/{memberNo}")
	public MemberUpdateDto memberUpdateProfile(@PathVariable("memberNo") Integer memberNo) {
		MemberUpdateDto dto = mSvc.getMemberUpdateProfile(memberNo);
		
		return dto;
	}
	// 회원정보 수정 
	@PutMapping("/modify/{memberNo}")
	public void memberModify(@RequestBody MemberUpdateDto dto,@PathVariable("memberNo") Integer memberNo) {
		
		Optional<Member> om = mRepo.findById(memberNo);
		
		if(om.isEmpty()) {
			return;
		}
		Member m = om.get();
		
		m.setNickname(dto.getNickname());
		m.setNameKor(dto.getNameKor());
		m.setNameEng(dto.getNameEng());
		
		mRepo.save(m);
	}
}
