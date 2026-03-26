package com.myspringboot.sajo.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRestController {
	@Autowired
	private ItemService ISvc;

	@GetMapping("/itemDetail/{itemIdx}") 
	public ItemDto itemDetailList(@PathVariable("itemIdx") Integer itemIdx) {
		ItemDto dto = ISvc.getItemDetail(itemIdx);
		System.out.println(dto.getItemName());
		return ISvc.getItemDetail(itemIdx);
	}
	
}
