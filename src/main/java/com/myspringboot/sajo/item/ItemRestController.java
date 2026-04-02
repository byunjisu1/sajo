package com.myspringboot.sajo.item;

import java.util.List;
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
	@Autowired
	private RakutenItemService RakuSvc;
	@Autowired
	private RakutenItemDetailService rakuDetailSvc;

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
	
	// URL 크롤링
	@PostMapping("/item/url/search")
	public Integer itemUrlSearch(@RequestBody Map<String, String> data) {
		String searchUrl = data.get("searchUrl");
		return rakuDetailSvc.scrapeRakuten(searchUrl);
	}

	// 키워드 크롤링
	@PostMapping("/item/keyword/search/{searchValue}")
	public List<RakutenItemDto> itemKeywordSearch(@PathVariable("searchValue") String searchValue) {
		return RakuSvc.getRakutenProducts(searchValue);
	}
}
