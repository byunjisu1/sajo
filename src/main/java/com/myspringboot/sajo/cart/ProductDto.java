package com.myspringboot.sajo.cart;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private String title;      // 상품명
    private String price;      // 가격
    private String description; // 상세 설명
    private String imageUrl;       // 대표 이미지 (1번 사진)
    private List<String> allImages; // 전체 이미지 리스트 (1, 2번 모두)
    
    //url 크롤링해서 번역하고 담아서 뿌려주는 기능
}
