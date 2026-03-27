package com.myspringboot.sajo;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.item.ItemRepository;
import com.myspringboot.sajo.likes.Likes;
import com.myspringboot.sajo.likes.LikesRepository;
import com.myspringboot.sajo.likes.LikesService;
import com.myspringboot.sajo.likes.WishListDto;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class LikesTests {
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private LikesRepository likesRepo;
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private LikesService lSvc;
	
	// Likes 테이블 더미데이터 추가
	@Test
	void testInsertLikes() {
		Member m = memberRepo.findById(1).get();
		Item i = itemRepo.findById(1).get();
		Likes l = new Likes();
		l.setMemberNo(m);
		l.setItemIdx(i);
		l.setLikeDate(LocalDateTime.now());
		l.setLikePrice(30000);
		likesRepo.save(l);
		
		m = memberRepo.findById(1).get();
		i = itemRepo.findById(2).get();
		l = new Likes();
		l.setMemberNo(m);
		l.setItemIdx(i);
		l.setLikeDate(LocalDateTime.now());
		l.setLikePrice(23000);
		likesRepo.save(l);
		
		m = memberRepo.findById(2).get();
		i = itemRepo.findById(3).get();
		l = new Likes();
		l.setMemberNo(m);
		l.setItemIdx(i);
		l.setLikeDate(LocalDateTime.now());
		l.setLikePrice(230000);
		likesRepo.save(l);
		
		m = memberRepo.findById(2).get();
		i = itemRepo.findById(5).get();
		l = new Likes();
		l.setMemberNo(m);
		l.setItemIdx(i);
		l.setLikeDate(LocalDateTime.now());
		l.setLikePrice(250000);
		likesRepo.save(l);
		
		m = memberRepo.findById(3).get();
		i = itemRepo.findById(1).get();
		l = new Likes();
		l.setMemberNo(m);
		l.setItemIdx(i);
		l.setLikeDate(LocalDateTime.now());
		l.setLikePrice(30000);
		likesRepo.save(l);
	}
	
	// 회원번호로 찜 내역 불러오기
	@Test
	void testLikesByMemberNo() {
		// Given
		int memberNo = 1;
		Sort sort = Sort.by(Sort.Direction.DESC, "likeDate");
		
		// When
		List<Likes> all = likesRepo.findByMemberNo_MemberNo(memberNo, sort);
		
		// Then
		for(Likes like : all) {
			Integer mNo = like.getMemberNo().getMemberNo();
			String itemName = like.getItemIdx().getItemName();
			System.out.println(mNo + "가 찜한 상품 : " + itemName);
		}
	}
	
	// 회원번호로 찜 내역 상세 불러오기
	@Test
	void testLikesServiceListAll() {
		// Given
		int memberNo = 1;
		String sortType = "latest";
		
		// When
		List<WishListDto> listWishListDto = lSvc.getWishList(memberNo, sortType);
		
		// Then
		for(WishListDto dto : listWishListDto) {
			System.out.println(dto.getMemberNo() + "가 찜한 상품 : " + dto.getItemName() + ", 가격 : " + dto.getItemPrice());
		}
	}
	
	// 찜 삭제하기
	@Test
	@Transactional
	void testDeleteLikes() {
		// Given
		int likeIdx = 6;
		
		// When
		lSvc.deleteLikes(likeIdx);
		
		// Then
		System.out.println("삭제 성공");
	}
}