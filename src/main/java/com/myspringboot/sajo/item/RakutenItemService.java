package com.myspringboot.sajo.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RakutenItemService {
	@Autowired
	private TranslationService tSvc;
	@Value("${apify.api.token}")
    private String apifyToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<RakutenItemDto> getRakutenProducts(String keyword) {
        String url = "https://api.apify.com/v2/acts/hassy~rakuten-marketplace-scraper/run-sync-get-dataset-items?token=" + apifyToken;

        Map<String, Object> payload = new HashMap<>();
        payload.put("keyword", keyword);
        payload.put("maxItems", 8);

        try {
            List<Map<String, Object>> rawResults = restTemplate.postForObject(url, payload, List.class);
            List<RakutenItemDto> dtoList = new ArrayList<>();
            List<String> jpNames = new ArrayList<>();

            if (rawResults != null) {
                for (Map<String, Object> item : rawResults) {
                    RakutenItemDto dto = new RakutenItemDto();

                    jpNames.add(String.valueOf(item.get("itemName")));
                    dto.setItemImg(String.valueOf(item.get("imageUrl")));
                    String priceRaw = String.valueOf(item.get("itemPrice")).replaceAll("[^0-9]", "");
                    int jpyPrice = priceRaw.isEmpty() ? 0 : Integer.parseInt(priceRaw);
                    // 원화 환산 계산 (엔화 * 환율) 후 반올림 처리
                    int krwPrice = (int) Math.round(jpyPrice * 9.0);
                    
                    dto.setItemPrice(krwPrice);
                    dto.setItemUrl(String.valueOf(item.get("itemUrl")));

                    dtoList.add(dto);
                }
                
                List<String> koNames = tSvc.translateListToKorean(jpNames);

                // 번역된 한글 이름을 DTO에 순서대로 매칭
                for (int i = 0; i < dtoList.size(); i++) {
                    dtoList.get(i).setItemName(koNames.get(i));
                }
            }
            return dtoList;

        } catch (Exception e) {
            System.err.println("라쿠텐 크롤링 중 에러 발생: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
