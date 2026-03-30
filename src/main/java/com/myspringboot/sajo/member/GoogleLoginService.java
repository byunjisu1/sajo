package com.myspringboot.sajo.member;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoogleLoginService {

    @Autowired
    private MemberRepository memberRepo;

    @Transactional // DB 작업의 안정성을 위해 추가
    public Member processGoogleLogin(Map<String, Object> userInfo, String accessToken) {
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String picture = (String) userInfo.get("picture");
        String snsType = (String) userInfo.get("sns_type");

        // 이메일로 기존 회원 확인 후 처리
        return memberRepo.findByEmail(email)
            .map(existingMember -> {
                // 기존 회원이면 액세스 토큰만 업데이트
                existingMember.setAccessToken(accessToken);
                return memberRepo.save(existingMember);
            })
            .orElseGet(() -> {
                // 신규 회원이면 Member 엔티티 새로 생성
                Member newMember = new Member();
                newMember.setEmail(email);
                newMember.setNickname(name);
                newMember.setNameKor(name);
                newMember.setProfileImg(picture);
                newMember.setSnsType(snsType);
                newMember.setAccessToken(accessToken);
                
                return memberRepo.save(newMember);
            });
    }
}