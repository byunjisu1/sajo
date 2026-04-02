package com.myspringboot.sajo.item;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Integer>{
	Item findByItemUrl(String itemUrl);	// itemUrl로 itemIdx 추출
}
