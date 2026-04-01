package com.myspringboot.sajo.member;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import jakarta.servlet.http.HttpSession;

@RestController
public class GoogleLoginController {

    @Autowired
    private GoogleLoginService GSvc;
    
    @Value("${google.api.clientId}")
    private String CLIENT_ID;
    @Value("${google.api.clientSc}")
    private String CLIENT_SECRET;
    @Value("${google.api.uri}")
    private String REDIRECT_URI;
    
    //구글 소셜 로그인
    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody Map<String, String> data, HttpSession session) {
        String code = data.get("code");
        
        try {
            // 인가 코드로 Access Token 받기
            String accessToken = fetchAccessToken(code);

            // Access Token으로 구글 유저 정보 받기
            Map<String, Object> userInfo = fetchUserInfo(accessToken);
            userInfo.put("sns_type", "google");	

            // 서비스 호출 (DB 조회/저장 로직은 서비스에서 한 번에 처리)
            Member member = GSvc.processGoogleLogin(userInfo, accessToken);
            
            return ResponseEntity.ok(member); // 프론트에 최종 유저 정보 반환
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("로그인 처리 중 오류 발생");
        }
    }
    
    // 구글의 임시 토큰 값을 정상 토큰으로 변경
    private String fetchAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        String tokenUrl = "https://oauth2.googleapis.com/token";

        Map<String, String> params = Map.of(
            "code", code,
            "client_id", CLIENT_ID,
            "client_secret", CLIENT_SECRET,
            "redirect_uri", REDIRECT_URI,
            "grant_type", "authorization_code"
        );

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, params, Map.class);
        return (String) response.getBody().get("access_token");
    }
    
    // 위의 정식토큰 값으로 유저의 정보 가져오기
    private Map<String, Object> fetchUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoUrl, HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }
    
    //구글 소셜 로그인 주소 생성
    @GetMapping("/google/url")
    public ResponseEntity<String> getGoogleAuthUrl() {
        String url = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + CLIENT_ID 
                   + "&redirect_uri=" + REDIRECT_URI 
                   + "&response_type=code&scope=email profile";
        return ResponseEntity.ok(url);
    }
}