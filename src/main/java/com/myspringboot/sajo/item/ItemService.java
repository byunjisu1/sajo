package com.myspringboot.sajo.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepo;
	
	//상품 내역 조회
	public List<ItemDto> getItemList() {
		List<Item> items = itemRepo.findAll();
		List<ItemDto> listItemDto = new ArrayList<>();
		for (Item i : items) {
			listItemDto.add(new ItemDto(i));
		}
		
		return listItemDto;
	}
}
