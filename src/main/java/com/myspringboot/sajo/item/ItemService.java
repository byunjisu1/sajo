package com.myspringboot.sajo.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepo;
	
	//상품 상세보기
	public ItemDto getItemDetail(Integer ItemIdx) {
		Optional<Item> item = itemRepo.findById(ItemIdx);
		if(item.isEmpty()) {
			return null;
		}
		
		return new ItemDto(item.get());
	}
	
}
