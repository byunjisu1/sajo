package com.myspringboot.sajo.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myspringboot.sajo.likes.LikesService;
import com.myspringboot.sajo.likes.WishListDto;

@RestController
public class MemberRestController {
	@Autowired
	private MemberService mSvc;
	@Autowired
	private LikesService lSvc;
	
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
}
