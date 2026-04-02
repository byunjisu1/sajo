package com.myspringboot.sajo.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RakutenItemDetailDto {
	private String name;
    private String description;
    private String image;
    private String url;
    private Offers offers;
    
    @Data
    public static class Offers {
        private Double price;
        private String priceCurrency;
    }
}
