package com.myspringboot.sajo.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;


// 카카오 로그인 서비스 
@Service
public class KakaoService {
	@Autowired
	MemberRepository mRepo;
	@Autowired
	MemberKakaoRepository mkRepo;
	
	@Value("${kakao.api.key}")
	private String clientId;
	private final String REDIRECT_URI = "http://13.209.208.223:9090/oauth/kakao/callback";

	/**
	 * 1단계: 인가 코드로 Access Token 받기
	 */
	public String getAccessToken(String code) {
	    RestTemplate rt = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "authorization_code");
	    params.add("client_id", clientId);
	    params.add("redirect_uri", REDIRECT_URI);
	    params.add("code", code);
	    params.add("client_secret", "6vBp0pDflO0SQV4ROVR7Ab8PpvtskNm9");


	    HttpEntity<MultiValueMap<String, String>> request =
	            new HttpEntity<>(params, headers);

	    try {
	        ResponseEntity<String> response = rt.exchange(
	                "https://kauth.kakao.com/oauth/token",
	                HttpMethod.POST,
	                request,
	                String.class
	        );

	        System.out.println("성공 응답 = " + response.getBody());

	        ObjectMapper objectMapper = new ObjectMapper();
	        KakaoTokenDto tokenDto =
	                objectMapper.readValue(response.getBody(), KakaoTokenDto.class);

	        return tokenDto.getAccess_token();

	    } catch (HttpClientErrorException e) {
	        throw e;
	    } catch (Exception e) {
	        throw new RuntimeException("카카오 토큰 파싱 실패", e);
	    }
	}

	public Map<String,Object> getKakaoUserInfo(String accessToken) {
		RestTemplate rt = new RestTemplate();

		// 헤더에 토큰 실어 보내기
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + accessToken);
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// 유저 정보 요청
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);
		ResponseEntity<KakaoUserInfoDto> response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest, KakaoUserInfoDto.class // 카카오 DTO 
		);

		// 2단계: 유저 정보 가져오기 로직 안에서
		KakaoUserInfoDto body = response.getBody();

		// 1. 새 바구니(객체)를 하나 만든다
		MemberInfoDto member = new MemberInfoDto();

		// 2. 카카오 상자에서 꺼내서 하나씩 담기
		member.setNickname(body.getKakao_account().getProfile().getNickname());
		member.setEmail(body.getKakao_account().getEmail());
		member.setProfileImg(body.getKakao_account().getProfile().getThumbnail_image_url());
		member.setSnsType("kakao");
		member.setAccessToken(accessToken);

		// +. HashMap 생성
		Map<String, Object> mapRet = new HashMap<>();
		mapRet.put("member", member);
		mapRet.put("id", body.getId());
		mapRet.put("nickname", body.getKakao_account().getProfile().getNickname());
		
		// 3. 다 채운 바구니를 리턴
		return mapRet;
	}
	
	// 카카오에서 받아온 정보를 db에 넣기 
	public Member loginCheckByKakao(Long id, String nickname) {
		Long kakaoId = id;

	    // 1. 이미 연결된 카카오 계정이 있는지 DB에서 먼저 조회
	    List<MemberKakao> existingKakaoList = mkRepo.findByKakaoId(kakaoId);
	    
	    if (existingKakaoList.size()>0) {
	    	MemberKakao existingKakao = existingKakaoList.get(0);
	        // 2. 이미 있다면? 추가 저장(save) 없이 바로 Member를 리턴 (여기서 에러 차단!)
	        return existingKakao.getMember();
	    } else {
		    
	    	// 3. 없다면? 처음 가입하는 유저이므로 새로 생성
            Member newMember = Member.builder()
                .nickname(nickname)
                .snsType("kakao")
                .build();
            newMember = mRepo.save(newMember);
            
            MemberKakao newMemberKakao = new MemberKakao();
            newMemberKakao.setMember(newMember);
            newMemberKakao.setKakaoId(kakaoId);
            mkRepo.save(newMemberKakao);
            
            return newMember;
	    }
		
	}
}
