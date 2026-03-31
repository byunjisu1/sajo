package com.myspringboot.sajo.member;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoogleLoginService {

    @Autowired
    private MemberRepository memberRepo;

    @Transactional
    public Member processGoogleLogin(Map<String, Object> userInfo, String accessToken) {
        String email = (String) userInfo.get("email");
        String name = (String) userInfo.get("name");
        String picture = (String) userInfo.get("picture");
        String snsType = (String) userInfo.get("sns_type");

        // 1. 가변 임시 변수 선언
        String tempBirth = null;
        String tempPhone = null;

        // 생일 파싱 (데이터가 리스트 구조로 올 경우 대비)
        if (userInfo.get("birthdays") instanceof java.util.List) {
            java.util.List<Map<String, Object>> birthdays = (java.util.List<Map<String, Object>>) userInfo.get("birthdays");
            if (!birthdays.isEmpty()) {
                Map<String, Object> date = (Map<String, Object>) birthdays.get(0).get("date");
                tempBirth = String.format("%s%02d%02d", date.get("year"), date.get("month"), date.get("day"));
            }
        }

        // 전화번호 파싱
        if (userInfo.get("phoneNumbers") instanceof java.util.List) {
            java.util.List<Map<String, Object>> phoneNumbers = (java.util.List<Map<String, Object>>) userInfo.get("phoneNumbers");
            if (!phoneNumbers.isEmpty()) {
                tempPhone = (String) phoneNumbers.get(0).get("value");
            }
        }

        // 2. 람다 내부에서 사용할 상수로 고정 (effectively final)
        final String birth = tempBirth;
        final String phone = tempPhone;

        return memberRepo.findByEmail(email)
            .map(existingMember -> {
                existingMember.setAccessToken(accessToken);
                existingMember.setProfileImg(picture);
                // 고정된 상수값 사용
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
                // 고정된 상수값 사용
                newMember.setBirth(birth);
                newMember.setPhone(phone);
                return memberRepo.save(newMember);
            });
    }
}