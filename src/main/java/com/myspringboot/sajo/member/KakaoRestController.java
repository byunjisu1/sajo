package com.myspringboot.sajo.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KakaoRestController {

    @Autowired
    private KakaoService kakaoService;

    /**
     * 카카오 로그인 후 리다이렉트되는 입구
     * 주소창에 붙어오는 code 값을 @RequestParam으로 받기 .
     */
    @GetMapping("/oauth/kakao/callback")
    public ResponseEntity<Map<String, Object>> kakaoCallback(@RequestParam("code") String code) {
        
        // 1. 서비스 호출해서 '인가 코드'를 '액세스 토큰'으로 교환
        String accessToken = kakaoService.getAccessToken(code);
        
        // 2. 받은 토큰으로 카카오 유저 정보를 가져와서 DTO에 담기
        Map<String, Object> map1 = kakaoService.getKakaoUserInfo(accessToken);
        MemberInfoDto memberInfo = (MemberInfoDto)map1.get("member");
        Long idKakao = (Long)map1.get("id");
        String nicknameKakao = (String)map1.get("nickname");
        
        // 3. 테스트용 콘솔 출력 (터미널에 닉네임 찍히는지 확인)
        System.out.println("로그인 시도 유저: " + memberInfo.getNickname());
        System.out.println("이메일 정보: " + memberInfo.getEmail());

        // +. HashMap 생성 및 데이터 추가
        Map<String, Object> mapRet = new HashMap<>();
        mapRet.put("member", memberInfo);
        mapRet.put("id", idKakao);
        mapRet.put("nickname", nicknameKakao);
        
        // 4. 리액트 화면에 JSON 데이터 그대로 뿌려주기
        return ResponseEntity.ok(mapRet);
    }
    
    @PostMapping("/api/member/kakao")
    public ResponseEntity<Integer> kakaoLogin(@RequestBody Map<String, Object> mapParams) {
    	Long id = (Long)mapParams.get("id");
    	String nickname = (String)mapParams.get("nickname");
        //Member member = kakaoService.loginCheckByKakao(dto);
        Member member = kakaoService.loginCheckByKakao(id, nickname);
        
        return ResponseEntity.ok(member.getMemberNo());
    }
}
