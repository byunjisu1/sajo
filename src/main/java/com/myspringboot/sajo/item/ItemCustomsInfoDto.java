package com.myspringboot.sajo.item;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCustomsInfoDto {
	private String hsCode;
	private double estimatedWeight;
	private String category;
	
	private String trrt; // 세율 
	private String uttxtrt;// 단위
	private String kornPrnm;// 한글 품명 
	
}
