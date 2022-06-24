package com.shop.projectlion.web.kakaotoken.controller;

import com.shop.projectlion.web.kakaotoken.dto.KakaoTokenResponseDto;
import com.shop.projectlion.web.kakaotoken.service.KakaoTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class KakaoTokenRestController {

    @Value("${kakao.client.secret}")
    private String clientSecret;

    @Value("${kakao.client.id}")
    private String clientId;

    private final KakaoTokenService kakaoTokenService;

    @GetMapping("/auth/kakao/callback")
    public String loginCallback(String code){

        // todo 카카오 토큰 조회 및 반환 로직 구현
        KakaoTokenResponseDto kakaoToken = kakaoTokenService.getToken(code, clientId, clientSecret);

        return "kakao token : " + kakaoToken;
    }

}
