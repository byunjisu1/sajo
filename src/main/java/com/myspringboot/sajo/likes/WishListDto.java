package com.myspringboot.sajo.likes;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {
	private Integer memberNo;
	private Integer itemIdx;
	private LocalDateTime likeDate;
	private String itemName;
	private Integer itemPrice;
	private String itemImg;
	
	public WishListDto(Likes likes) {
		this.memberNo = likes.getMemberNo().getMemberNo();
		this.itemIdx = likes.getLikeItemIdx().getItemIdx();
		this.likeDate = likes.getLikeDate();
		this.itemName = likes.getLikeItemIdx().getItemName();
		this.itemPrice = likes.getLikeItemIdx().getItemPrice();
		this.itemImg = likes.getLikeItemIdx().getItemImg();
	}
}
