package com.myspringboot.sajo.cart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvTestController {

    // application.properties에 설정한 값을 가져옵니다.
    @Value("${test.api.key}")
    private String myApiKey;

    @GetMapping("/env-test")
    public String checkEnv() {
        return "현재 연결된 API 키는: " + myApiKey + " 입니다.";
    }
}