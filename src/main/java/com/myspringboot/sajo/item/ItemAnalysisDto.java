package com.myspringboot.sajo.item;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemAnalysisDto {
	private String isCounterfeit;	// 가품 확률(높음/낮음)
	private String reason;	// 이유
	private List<String> riskPoints;	// 리스크 요소 리스트
	private String advice;	// 조언
}
