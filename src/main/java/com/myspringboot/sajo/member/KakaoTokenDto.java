package com.myspringboot.sajo.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoTokenDto {
	private String access_token;
    private String token_type;
    private String refresh_token;
    private String id_token;
    private String scope;
    private Integer expires_in;
    private Integer refresh_token_expires_in;
}
