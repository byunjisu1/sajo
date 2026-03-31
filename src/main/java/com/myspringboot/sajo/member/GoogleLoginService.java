package com.myspringboot.sajo.member;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleLoginService {

    @Autowired
    private MemberRepository memberRepo;
    
    //구글 소셜 로그인 시 가져올 데이터들
    @Transactional
    public Member processGoogleLogin(Map<String, Object> userInfo, String accessToken) {
        // 1. 기본 정보 추출
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String picture = (String) userInfo.get("picture");
        String snsType = (String) userInfo.get("sns_type");

        // 2. People API 호출해서 상세 정보(생일, 전화번호) 가져오기
        Map<String, Object> additionalInfo = getGooglePeopleApi(accessToken);
        
        String tempBirth = null;
        String tempPhone = null;

        if (additionalInfo != null) {
            // 생일 파싱
            if (additionalInfo.get("birthdays") instanceof List) {
                List<Map<String, Object>> birthdays = (List<Map<String, Object>>) additionalInfo.get("birthdays");
                if (!birthdays.isEmpty()) {
                    Map<String, Object> date = (Map<String, Object>) birthdays.get(0).get("date");
                    tempBirth = String.format("%s%02d%02d", 
                        date.get("year"), date.get("month"), date.get("day"));
                }
            }
            // 전화번호 파싱
            if (additionalInfo.get("phoneNumbers") instanceof List) {
                List<Map<String, Object>> phoneNumbers = (List<Map<String, Object>>) additionalInfo.get("phoneNumbers");
                if (!phoneNumbers.isEmpty()) {
                    tempPhone = (String) phoneNumbers.get(0).get("value");
                }
            }
        }

        final String birth = tempBirth;
        final String phone = tempPhone;

        // 3. DB 저장 및 업데이트 로직 (DB 검색해서 email로 유저 비교)
        return memberRepo.findByEmail(email)
            .map(existingMember -> {
                existingMember.setAccessToken(accessToken);
                existingMember.setProfileImg(picture);
                if (birth != null) existingMember.setBirth(birth);
                if (phone != null) existingMember.setPhone(phone);
                return memberRepo.save(existingMember);
            })
            .orElseGet(() -> {
                Member newMember = new Member();
                newMember.setEmail(email);
                newMember.setNickname(name);
                newMember.setNameKor(name);
                newMember.setProfileImg(picture);
                newMember.setSnsType(snsType);
                newMember.setAccessToken(accessToken);
                newMember.setBirth(birth);
                newMember.setPhone(phone);
                return memberRepo.save(newMember);
            });
    }

    //구글 People API를 직접 호출하여 추가 정보를 가져오는 메서드
    private Map<String, Object> getGooglePeopleApi(String accessToken) {
        String url = "https://people.googleapis.com/v1/people/me?personFields=birthdays,phoneNumbers";
        
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // 전달받은 액세스 토큰 사용
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            
            System.out.println("================ People API 원본 응답 시작 ================");
            System.out.println(response.getBody());
            System.out.println("================ People API 원본 응답 끝 ==================");
            
            return (Map<String, Object>) response.getBody();
        } catch (Exception e) {
            System.err.println("People API 호출 에러: " + e.getMessage());
            return null;
        }
    }
}