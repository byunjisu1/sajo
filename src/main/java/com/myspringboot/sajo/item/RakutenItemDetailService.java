package com.myspringboot.sajo.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RakutenItemDetailService {
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private TranslationService tSvc;
	
	@Value("${apify2.api.token}")
    private String apifyToken;

    private final RestTemplate restTemplate = new RestTemplate();
    
    // DB에 있는 item인지 판단한 후, 해당 itemIdx 반환
    public Integer alreadyScraped(String searchUrl) {
    	Item item = itemRepo.findByItemUrl(searchUrl);
    	if(item != null) {
    		return item.getItemIdx();
    	}
    	return null;
    }

    // 라쿠텐 url 크롤링
    public Integer scrapeRakuten(String productUrl) {
    	Integer itemIdx = alreadyScraped(productUrl);
    	if(itemIdx == null) {
			System.out.println("넘어온 url : " + productUrl);
			String apiUrl = "https://api.apify.com/v2/acts/apify~e-commerce-scraping-tool/run-sync-get-dataset-items?token=" + apifyToken;
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			Map<String, Object> input = new HashMap<>();
			input.put("detailsUrls", List.of(Map.of("url", productUrl)));
			input.put("scrapeMode", "BROWSER");
			input.put("additionalProperties", false);
			input.put("additionalPropertiesSearchEngine", true);
			input.put("additionalReviewProperties", false);
			input.put("scrapeInfluencerProducts", false);
			input.put("scrapeReviewsDelivery", false);
			
			// 프록시 설정 (라쿠텐 차단 방지)
			Map<String, Object> proxy = new HashMap<>();
			proxy.put("useApifyProxy", true);
			proxy.put("apifyProxyGroups", List.of("RESIDENTIAL"));
			input.put("proxyConfiguration", proxy);
			
			HttpEntity<Map<String, Object>> entity = new HttpEntity<>(input, headers);
			
			try {
				ResponseEntity<RakutenItemDetailDto[]> response = restTemplate.postForEntity(apiUrl, entity, RakutenItemDetailDto[].class);
				RakutenItemDetailDto dto = null;
				String jpName = "";
				String jpDetail = "";
				Item item = new Item();
				
				if (response.getBody() != null && response.getBody().length > 0) {
					dto = response.getBody()[0];
					
					jpName = dto.getName();
					
					if (dto.getOffers() != null && dto.getOffers().getPrice() != null) {
						String priceRaw = String.valueOf(dto.getOffers().getPrice()).replaceAll("[^0-9]", "");
						int jpyPrice = priceRaw.isEmpty() ? 0 : Integer.parseInt(priceRaw);
						// 원화 환산 계산 (엔화 * 환율) 후 반올림 처리
						int krwPrice = (int) Math.round(jpyPrice * 0.9);
						item.setItemPrice(krwPrice);
					}
					
					item.setItemImg(dto.getImage());
					jpDetail = dto.getDescription();
					item.setItemUrl(productUrl);
				}
				
				item.setItemName(String.valueOf(tSvc.translateToKorean(jpName)));
				item.setItemDetail(String.valueOf(tSvc.translateToKorean(jpDetail)));
				
				Item savedItem = itemRepo.save(item);
				return savedItem.getItemIdx();
				
			} catch (Exception e) {
				throw new RuntimeException("라쿠텐 상품 정보를 가져오는 중 오류 발생: " + e.getMessage());
			}
    	} else {
    		System.out.println("DB에 저장된 상품 idx를 가져옴.");
    		return itemIdx;
    	}
    }
}