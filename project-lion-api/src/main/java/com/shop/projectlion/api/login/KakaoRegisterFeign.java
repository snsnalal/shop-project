package com.shop.projectlion.api.login;


import com.shop.projectlion.global.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoRegisterFeign", url = "https://kapi.kakao.com", configuration = {FeignConfiguration.class})
public interface KakaoRegisterFeign {

    @GetMapping(value = "/v2/user/me",consumes = "application/json")
    KakaoUserInfo getKakaoUserInfo(
            @RequestHeader("Content-type") String contentType,
            @RequestHeader(value = "Authorization")String accessToken);
}
