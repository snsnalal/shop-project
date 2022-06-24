package com.shop.projectlion.api.login.service;

import com.shop.projectlion.api.login.KakaoFeignClient;
import com.shop.projectlion.api.login.dto.KakaoTokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KakaoTokenService {

    private final KakaoFeignClient kakaoFeignClient;

    public KakaoTokenResponseDto getToken(String code ,String clientId, String clientSecret){

        /*
        KakaoTokenRequestDto kakaoTokenRequestDto = KakaoTokenRequestDto.builder()
                .grant_type("authorization_code")
                .client_Id(clientId)
                .redirect_uri("http://localhost:8084/auth/kakao/callback")
                .code(code)
                .client_secret(clientSecret)
                .build();
        return kakaoFeignClient.getAccessToken(kakaoTokenRequestDto);
        */
        return kakaoFeignClient.getAccessToken("authorization_code", clientId,"http://localhost:8084/auth/kakao/callback", code, clientSecret);
    }

}
