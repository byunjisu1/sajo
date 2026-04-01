package com.myspringboot.sajo.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RakutenItemDto {
	private String itemName;
	private String itemImg;
	private String itemDetail;
	private int itemPrice;
}
