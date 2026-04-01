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
        // 기본 정보 추출
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");

        // DB 저장 및 업데이트 로직 (DB 검색해서 email로 유저 비교)
        return memberRepo.findByEmail(email)
            .map(existingMember -> {
                existingMember.setAccessToken(accessToken);
                return memberRepo.save(existingMember);
            })
            .orElseGet(() -> {
                Member newMember = new Member();
                newMember.setEmail(email);
                newMember.setNickname(name);
                newMember.setNameKor(name);
                newMember.setAccessToken(accessToken);
                return memberRepo.save(newMember);
            });
    }
}