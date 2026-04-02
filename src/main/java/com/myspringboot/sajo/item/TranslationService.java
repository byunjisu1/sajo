package com.myspringboot.sajo.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslationService {
	
	@Value("${deepl.api.key}")
	private String DEEPL_API_KEY;
    private final String DEEPL_URL = "https://api-free.deepl.com/v2/translate";
   
    public String translateToKorean(String text) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "DeepL-Auth-Key " + DEEPL_API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        map.put("text", new String[]{text});
        map.put("target_lang", "KO");
        map.put("model_type", "quality_optimized");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        @SuppressWarnings({"unchecked", "rawtypes"})
        ResponseEntity<Map> response = restTemplate.postForEntity(DEEPL_URL, entity, Map.class);
        
        List<Map<String, String>> translations = (List<Map<String, String>>) response.getBody().get("translations");
        return translations.get(0).get("text");
    }
    
    public void checkUsage() {
    	// [중요] 주소 끝이 /usage 인지 다시 확인!
        String url = "https://api-free.deepl.com/v2/usage";

        RestTemplate restTemplate = new RestTemplate();

        // 1. 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "DeepL-Auth-Key " + DEEPL_API_KEY);
        // Usage 조회는 GET 방식이므로 별도의 Body(text 등)가 전혀 필요 없습니다.
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            // 2. exchange를 통한 GET 요청
            ResponseEntity<String> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                entity, 
                String.class
            );

            System.out.println("=== DeepL 사용량 조회 결과 ===");
            System.out.println(response.getBody());
            
        } catch (Exception e) {
            // 여기서 여전히 400이 뜬다면, 주소가 정확한지 혹은 
            // 본인의 플랜(Free vs Pro)에 맞는 주소인지 확인해야 합니다.
            System.err.println("에러 발생: " + e.getMessage());
        }
    }
    
    //다른 기능

    private final String API_TOKEN = "_";
    private final String ACTOR_TASK_ID = "YOUR_TASK_ID";

    // [임시 DB] Key: 상품ID, Value: 상품정보
    private Map<String, ProductDto2> wishListDb = new HashMap<>();

    /**
     * 기능 1: 사용자가 최초로 상품을 찜할 때 (초기 저장)
     * 이 메서드는 테스트 코드나 컨트롤러에서 상품 URL을 처음 등록할 때 호출됩니다.
     */
    public void firstWish(ProductDto2 product) {
        wishListDb.put(product.getProductId(), product);
        System.out.println("[DB 저장] " + product.getMallName() + " 상품 등록 완료: " + product.getTitle());
    }

    /**
     * 기능 2: Apify API 호출 및 크롤링 결과 반환
     * 서비스 내부에 크롤링 로직을 캡슐화합니다.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<ProductDto2> crawlLatestData() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.apify.com/v2/actor-tasks/" + ACTOR_TASK_ID + "/runs?token=" + API_TOKEN + "&waitForFinish=60";
        
        System.out.println("[API 요청] Apify를 통해 최신 가격 정보를 수집합니다...");
        
        try {
            // 1. Apify 실행 요청
            ResponseEntity<Map> response = restTemplate.postForEntity(url, null, Map.class);
            
            // 2. 결과 데이터셋 ID 추출 및 실제 데이터 가져오기
            // (Apify의 데이터셋 API를 통해 실제 상품 리스트를 ProductDto 배열로 받음)
            String datasetId = (String) ((Map) response.getBody().get("data")).get("defaultDatasetId");
            String dataUrl = "https://api.apify.com/v2/datasets/" + datasetId + "/items?token=" + API_TOKEN;
            
            ProductDto2[] results = restTemplate.getForObject(dataUrl, ProductDto2[].class);
            return Arrays.asList(results);
            
        } catch (Exception e) {
            System.err.println("[에러] API 호출 중 오류 발생: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * 기능 3: 가격 변동 체크 및 업데이트
     * 스케줄러가 호출할 핵심 비즈니스 로직입니다.
     */
    public void refreshAndCheckPrices() {
        // 1. 최신 데이터 긁어오기 (기능 2 호출)
        List<ProductDto2> latestData = crawlLatestData();

        // 2. 기존 Map(DB)에 저장된 데이터와 비교
        for (ProductDto2 latest : latestData) {
            ProductDto2 saved = wishListDb.get(latest.getProductId());

            if (saved != null) {
                // 가격이 하락했는지 확인
                if (latest.getPrice() < saved.getPrice()) {
                    int dropPrice = saved.getPrice() - latest.getPrice();
                    
                    // [알림 발송 시뮬레이션]
                    System.out.println("\n-----------------------------------------");
                    System.out.println("📢 [가격 변동 알림] " + saved.getTitle());
                    System.out.println("기존가: " + saved.getPrice() + " -> 현재가: " + latest.getPrice());
                    System.out.println("가격이 " + dropPrice + "엔 떨어졌습니다! 구매하세요!");
                    System.out.println("URL: " + saved.getUrl());
                    System.out.println("-----------------------------------------\n");

                    // 3. 가격 업데이트 (다음 비교를 위해 최신가로 동기화)
                    saved.setPrice(latest.getPrice());
                } else {
                    System.out.println("[모니터링] " + saved.getTitle() + ": 가격 변동 없음.");
                }
            }
        }
    }
    
}
