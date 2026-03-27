package com.myspringboot.sajo.likes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LikesService {
	@Autowired
	private LikesRepository likesRepo;
	
	// 회원번호에 대한 찜 내역 불러오기
	public List<WishListDto> getWishList(Integer memberNo, String sortType) {
		Sort sort;
	    
	    if ("name".equals(sortType)) {
	        sort = Sort.by(Sort.Direction.ASC, "likeItemIdx.itemName");
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
}
