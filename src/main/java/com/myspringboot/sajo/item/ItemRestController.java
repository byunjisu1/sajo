package com.myspringboot.sajo.item;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRestController {
	@Autowired
	private ItemService ISvc;
	@Autowired
	private ItemAnalysisService IASvc;

	// 상품 상세 정보 가져오기
	@GetMapping("/itemDetail/{itemIdx}") 
	public ItemDto itemDetailList(@PathVariable("itemIdx") Integer itemIdx) {
		return ISvc.getItemDetail(itemIdx);
	}
	
	// 상품 가품 분석하기
	@PostMapping("/item/analyze")
	public ItemAnalysisDto itemAnalyze(@RequestBody Map<String, String> params) {
		try {
			String imageUrl = params.get("imageUrl");
			String description = params.get("description");
			
			return IASvc.analyzeProduct(imageUrl, description);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
