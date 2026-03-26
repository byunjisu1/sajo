package com.myspringboot.sajo.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto2 {
	private String productId;    // 상품 고유 ID
    private String title;        // 상품명
    private int price;           // 현재 가격
    private String url;          // 상품 상세 URL
    private String imageUrl;     // 상품 이미지 URL
    private String description;  // 상세 정보
    private String mallName;     // 쇼핑몰 이름 (예: Rakuten, Amazon 등)
    
    //url로 크롤링 된 데이터 가져오기
}
