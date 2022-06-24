package com.shop.projectlion.web.kakaologin.dto;

import com.shop.projectlion.domain.jwt.constant.GrantType;
import com.shop.projectlion.domain.jwt.constant.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LogintokenDto {

    GrantType grantType;
    TokenType tokenType;
    String token;
    Date expireTime;

    public static LogintokenDto of(String tokenType, String token, Date expireTime){
        return LogintokenDto.builder()
                .grantType(GrantType.BEARRER)
                .tokenType(TokenType.valueOf(tokenType))
                .token(token)
                .expireTime(expireTime)
                .build();
    }

}
