package com.shop.projectlion.api.login;


import com.shop.projectlion.global.config.FeignConfiguration;
import com.shop.projectlion.web.kakaotoken.dto.KakaoTokenRequestDto;
import com.shop.projectlion.web.kakaotoken.dto.KakaoTokenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
//@RequestParam(name = "grant_type")String grantType, @RequestParam(name = "client_id")String clientId, @RequestParam(name = "redirect_uri")String redirectUri, @RequestParam(name = "code")String code, @RequestParam(name = "client_secret")String clientSecret
@FeignClient(name = "kakaoTokenFeign", url = "https://kauth.kakao.com", configuration = {FeignConfiguration.class})
public interface KakaoFeignClient {

    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    //KakaoTokenResponseDto getAccessToken(@SpringQueryMap KakaoTokenRequestDto kakaoTokenRequestDto);
    KakaoTokenResponseDto getAccessToken(@RequestParam(name = "grant_type")String grantType, @RequestParam(name = "client_id")String clientId, @RequestParam(name = "redirect_uri")String redirectUri, @RequestParam(name = "code")String code, @RequestParam(name = "client_secret")String clientSecret);

}
