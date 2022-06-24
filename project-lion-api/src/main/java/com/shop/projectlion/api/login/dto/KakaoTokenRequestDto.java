package com.shop.projectlion.api.login.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KakaoTokenRequestDto {

    private String grant_type;
    private String client_Id;
    private String redirect_uri;
    private String code;
    private String client_secret;
}
