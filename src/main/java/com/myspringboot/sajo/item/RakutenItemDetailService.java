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
	@Value("${apify2.api.token}")
    private String apifyToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public Integer scrapeRakuten(String productUrl) {
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
            
            if (response.getBody() != null && response.getBody().length > 0) {
                RakutenItemDetailDto dto = response.getBody()[0];
                
                Item item = new Item();
                item.setItemName(dto.getName());
                
                if (dto.getOffers() != null && dto.getOffers().getPrice() != null) {
                	String priceRaw = String.valueOf(dto.getOffers().getPrice()).replaceAll("[^0-9]", "");
                    int jpyPrice = priceRaw.isEmpty() ? 0 : Integer.parseInt(priceRaw);
                    // 원화 환산 계산 (엔화 * 환율) 후 반올림 처리
                    int krwPrice = (int) Math.round(jpyPrice * 9.0);
                    item.setItemPrice(krwPrice);
                }
                
                item.setItemImg(dto.getImage());
                item.setItemDetail(dto.getDescription());
                
                Item savedItem = itemRepo.save(item);
                return savedItem.getItemIdx();
            }
        } catch (Exception e) {
            throw new RuntimeException("라쿠텐 상품 정보를 가져오는 중 오류 발생: " + e.getMessage());
        }
        
        return null;
    }
}
