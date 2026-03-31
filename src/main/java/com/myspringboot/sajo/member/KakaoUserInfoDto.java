package com.myspringboot.sajo.member;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUserInfoDto {
	private Long id; // 카카오 고유 ID
    private KakaoAccount kakao_account;

    @Getter
    @NoArgsConstructor
    public static class KakaoAccount {
        private String email;
        private Profile profile;
    }	

    @Getter
    @NoArgsConstructor
    public static class Profile {
        private String nickname;
        private String thumbnail_image_url;
    }
}
