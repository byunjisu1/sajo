package com.myspringboot.sajo.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
	private Integer itemIdx;
	private String itemName;
	private String itemDetail;
	private int itemPrice;
	private String itemImg;
	
	public ItemDto(Item item) {
		this.itemIdx = item.getItemIdx();
		this.itemName = item.getItemName();
		this.itemDetail = item.getItemDetail();
		this.itemPrice = item.getItemPrice();
		this.itemImg = item.getItemImg();
	}
}
