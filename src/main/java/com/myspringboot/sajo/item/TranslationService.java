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
   
    // 일본어 -> 한국어 번역
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
    
    // 라쿠텐 키워드 검색 후 번역
    public List<String> translateListToKorean(List<String> textList) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "DeepL-Auth-Key " + DEEPL_API_KEY);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> map = new HashMap<>();
        // 핵심: String[] 배열 형태로 리스트를 변환해서 한 번에 보냅니다.
        map.put("text", textList.toArray(new String[0])); 
        map.put("target_lang", "KO");

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);
        
        @SuppressWarnings("unchecked")
        ResponseEntity<Map> response = restTemplate.postForEntity(DEEPL_URL, entity, Map.class);
        
        // 결과도 리스트로 돌아옵니다.
        List<Map<String, String>> translations = (List<Map<String, String>>) response.getBody().get("translations");
        
        List<String> resultList = new ArrayList<>();
        for (Map<String, String> obj : translations) {
            resultList.add(obj.get("text"));
        }
        
        return resultList;
    }
}
