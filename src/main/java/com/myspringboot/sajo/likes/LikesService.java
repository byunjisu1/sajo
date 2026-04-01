package com.myspringboot.sajo.likes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myspringboot.sajo.item.Item;
import com.myspringboot.sajo.item.ItemRepository;
import com.myspringboot.sajo.member.Member;
import com.myspringboot.sajo.member.MemberRepository;

@Service
public class LikesService {
	@Autowired
	private LikesRepository likesRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private ItemRepository itemRepo;
	
	// 회원번호에 대한 찜 내역 불러오기
	public List<WishListDto> getWishList(Integer memberNo, String sortType) {
		Sort sort;
	    
	    if ("name".equals(sortType)) {
	        sort = Sort.by(Sort.Direction.ASC, "itemIdx.itemName");
	    } else {
	        sort = Sort.by(Sort.Direction.DESC, "likeDate");
	    }
	    
		List<Likes> wishList = likesRepo.findByMemberNo_MemberNo(memberNo, sort);
		List<WishListDto> wishListDto = new ArrayList<>();
		for(Likes l : wishList) {
			wishListDto.add(new WishListDto(l));
		}
		
		return wishListDto;
	}
	
	// 찜 삭제하기
	public void deleteLikes(int likeIdx) {
		Optional<Likes> ol = likesRepo.findById(likeIdx);
		if(ol.isEmpty()) return;
		Likes l = ol.get();
		likesRepo.delete(l);
	}
	
	// 찜 추가하기
	public boolean insertLikes(int memberNo, int itemIdx) {
		int count = likesRepo.checkExists(memberNo, itemIdx);
		if(count>0) {
			return false;
		} else {
			Member member = memberRepo.findById(memberNo).orElseThrow();
			Item item = itemRepo.findById(itemIdx).orElseThrow();
			
			Likes l = new Likes();
			l.setMemberNo(member);
			l.setItemIdx(item);
			l.setLikeDate(LocalDateTime.now());
			l.setLikePrice(item.getItemPrice());
			
			likesRepo.save(l);
			return true;
		}
	}
}
