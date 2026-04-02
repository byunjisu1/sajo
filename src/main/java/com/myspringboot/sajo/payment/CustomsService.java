package com.myspringboot.sajo.payment;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.myspringboot.sajo.item.ItemCustomsInfoDto;

@Service
public class CustomsService {
	@Value("${custom.api.key}")
	private String apiKey;
	
	// 관세
	public void getRealTaxRate(ItemCustomsInfoDto dto, String hsCode) throws Exception {
		try {
			String cleanHsCode = dto.getHsCode().replaceAll("[^0-9]", "");
			String url = "https://unipass.customs.go.kr:38010/ext/rest/trrtQry/retrieveTrrt" + "?crkyCn=" + apiKey
					+ "&hsSgn=" + cleanHsCode
					+ "&trrtTpcd=A";

			RestTemplate restTemplate = new RestTemplate();
			String xmlResponse = restTemplate.getForObject(url, String.class);
			
			if (xmlResponse.contains("<tCnt>0</tCnt>")) {
				System.out.println(">>> 관세청에 정보가 없음. 기본세율 적용.");
				dto.setTrrt("8.0"); // 기본 관세율 8% 세팅
				dto.setKornPrnm("기타 품목 (조회 실패)");
				return;
			}

			// XML 파싱 (간단하게 DocumentBuilder 사용)
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlResponse)));

			// <txrt> 태그 값 추출
			NodeList trrtList = doc.getElementsByTagName("trrt");
			if (trrtList != null && trrtList.getLength() > 0) {
				// 혹시 모르니 trim()으로 공백도 제거합니다.
				String taxRate = trrtList.item(0).getTextContent().trim();
				dto.setTrrt(taxRate);
				System.out.println("로그 - 찾은 세율: " + taxRate);
			} else {
				System.out.println("로그 - txrt 태그를 찾지 못함");
			}
			// <kornPrnm> 한글 품명 추출 (선택 사항)
			NodeList nameList = doc.getElementsByTagName("kornPrnm");
			if (nameList != null && nameList.getLength() > 0) {
				String name = nameList.item(0).getTextContent().trim();
				dto.setKornPrnm(name);
				System.out.println("로그 - 찾은 품명: " + name);
			}

		} catch (Exception e) {
			// API 오류 시 기본세율 8% 등을 세팅하는 예외 처리
			dto.setTrrt("8.0");
			System.out.println("관세청 API 호출 실패: " + e.getMessage());
		}

	}
}
