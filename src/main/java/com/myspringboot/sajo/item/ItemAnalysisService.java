package com.myspringboot.sajo.item;

import java.util.ArrayList;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myspringboot.sajo.payment.CustomsService;

@Service
public class ItemAnalysisService {
	@Autowired
	CustomsService cSvc;
	
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
	
	// 단일 상품에 대한 관세 알아내기
	public ItemCustomsInfoDto getCustomsInfo(String imageUrl, String description) throws Exception{
		RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(apiKey);
	    
	    String promptText = "이미지를 분석해서 한국 관세청(UNI-PASS)의 10자리 HS CODE와 예상 무게를 추출해줘.\n" +
                "반드시 아래 JSON 형식으로만 응답해:\n" +
                "{\n" +
                "  \"hsCode\": \"숫자10자리\",\n" +
                "  \"estimatedWeight\": 숫자\n" +
                "}";

	    List<Map<String, Object>> messages = new ArrayList<>();
	    messages.add(Map.of("role", "system", "content", "You are a helpful assistant that outputs JSON."));
	    
	    List<Map<String, Object>> contentList = new ArrayList<>();
	    contentList.add(Map.of("type", "text", "text", promptText));
	    contentList.add(Map.of("type", "image_url", "image_url", Map.of("url", imageUrl)));
	    
	    messages.add(Map.of("role", "user", "content", contentList));

	    Map<String, Object> body = new HashMap<>();
	    body.put("model", "gpt-4o");
	    body.put("messages", messages);
	    body.put("response_format", Map.of("type", "json_object"));

	    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
	    
	    // API 호출 전 예외 처리
	    ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);
	    JsonNode root = objectMapper.readTree(response.getBody());
	    String jsonStr = root.path("choices").get(0).path("message").path("content").asText();
	    
	    // 2. DTO 변환 및 Null 체크 강화
	    ItemCustomsInfoDto dto = objectMapper.readValue(jsonStr, ItemCustomsInfoDto.class);
	    
	    // dto가 null인지 먼저 확인해야 getHsCode() 호출 시 에러가 안 남
	    if(dto != null && dto.getHsCode() != null && !dto.getHsCode().isEmpty()) {
	        cSvc.getRealTaxRate(dto, dto.getHsCode());
	    } else if (dto == null) {
	        // dto 자체가 생성 안됐을 경우 빈 객체라도 생성해서 리턴 (NPE 방지)
	        dto = new ItemCustomsInfoDto();
	        dto.setTrrt("8.0"); 
	    }
	    
	    return dto;
	}
	
	// 상품 리스트에 대한 관세 알아내기
	public Map<String, Object> getCustomsInfos(List<Map<String, Object>> selectedProducts) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(apiKey);
		
		StringBuilder productListStr = new StringBuilder();
	    for (Map<String, Object> product : selectedProducts) {
	        productListStr.append("- 상품명: ").append(product.get("itemName"))
	                      .append(", 가격: ").append(product.get("itemPrice"))
	                      .append(", 상품 이미지: ").append(product.get("itemImg")).append("\n");
	    }
	    
	    String promptText =
	    		"너는 대한민국 관세 전문가이자 국제 물류 전문가야. 함께 제공되는 [장바구니 상품 이미지들]과 아래 [상품 상세 리스트]를 종합 분석해서, 이 화물 전체에 대한 합산과세 정보를 추정해줘.\n\n" +
			    "[분석 지침]\n" +
			    "1. 이미지 Vision 분석: 각 상품의 크기, 소재, 재질, 부피감을 시각적으로 정밀 분석해.\n" +
			    "2. 텍스트 상세 분석: 아래 리스트의 상품명과 가격 정보를 시각 정보와 매칭해.\n" +
			    "3. 정밀 무게 추론: 시각+텍스트 정보를 종합하여 현실적인 '개별 무게'를 추산하고, 모든 상품의 '최종 합산 무게(g)'를 계산해. (비현실적으로 무거운 무게는 절대 지양할 것)\n" +
			    "4. 합산 세율 추론: 전체 품목의 가중 평균 관세율(%)을 추정해.\n\n" +
			    "5. 결과는 반드시 아래 JSON 형식으로만 응답하고, 다른 설명은 절대 하지 마.\n\n" +
			    "[장바구니 상품 상세 리스트]\n" + productListStr.toString() + "\n\n" +
			    "[응답 형식]\n" +
			    "{\n" +
			    "  \"estimatedAverageTaxRate\": 숫자(예: 11.3),\n" +
			    "  \"totalWeightGrams\": 숫자(모든 상품 실중량 합계)\n" +
			    "}";

	    List<Map<String, Object>> messages = new ArrayList<>();
	    messages.add(Map.of("role", "system", "content", "You are a helpful assistant that outputs JSON."));
	    messages.add(Map.of("role", "user", "content", promptText));

	    Map<String, Object> body = new HashMap<>();
	    body.put("model", "gpt-4o"); 
	    body.put("messages", messages);
	    body.put("response_format", Map.of("type", "json_object"));

	    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
	    ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);
	    
	    JsonNode root = objectMapper.readTree(response.getBody());
	    String jsonStr = root.path("choices").get(0).path("message").path("content").asText();
	    
	    return objectMapper.readValue(jsonStr, new TypeReference<Map<String, Object>>(){});
	}
}
