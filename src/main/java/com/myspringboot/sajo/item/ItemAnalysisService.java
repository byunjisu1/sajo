package com.myspringboot.sajo.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ItemAnalysisService {
	@Value("${openai.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 상품 가품 분석
	public ItemAnalysisDto analyzeProduct(String imageUrl, String description) throws Exception {
		RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(apiKey);

	    List<Map<String, Object>> contentList = new ArrayList<>();

	    contentList.add(Map.of(
	        "type", "text",
	        "text",
	        "이 상품의 이미지와 설명을 분석해서 가품 여부 리스크 리포트를 작성해줘. \n" +
	        "상품 설명: " + description + "\n" +
	        "반드시 JSON 형식으로만 답해:\n" +
	        "{ \"isCounterfeit\": \"확률(높음/낮음)\", \"reason\": \"이유\", \"riskPoints\": [리스크 요소들], \"advice\": \"조언\" }"
	    ));

	    contentList.add(Map.of(
	        "type", "image_url",
	        "image_url", Map.of("url", imageUrl)
	    ));

	    Map<String, Object> body = new HashMap<>();
	    body.put("model", "gpt-4o");
	    body.put("messages", List.of(Map.of("role", "user", "content", contentList)));
	    body.put("response_format", Map.of("type", "json_object"));

	    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
	    ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

	    // API 응답에서 content 부분만 추출하여 DTO로 변환
        JsonNode root = objectMapper.readTree(response.getBody());
        String jsonStr = root.path("choices").get(0).path("message").path("content").asText();
        
        return objectMapper.readValue(jsonStr, ItemAnalysisDto.class);
	}
}
