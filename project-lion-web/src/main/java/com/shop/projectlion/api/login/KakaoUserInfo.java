package com.shop.projectlion.api.login;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.projectlion.domain.jwt.dto.TokenDto;
import com.shop.projectlion.domain.member.constant.MemberRole;
import com.shop.projectlion.domain.member.constant.MemberType;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.global.util.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@Getter @Setter
@JsonIgnoreProperties({"connected_at"})
@ToString
public class KakaoUserInfo {

    private String id;

    private Map<String, String> properties;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter @Setter
    @JsonIgnoreProperties({"profile_nickname_needs_agreement", "profile_image_needs_agreement", "profile", "has_email",
            "email_needs_agreement", "is_email_valid", "is_email_verified"})
    public static class KakaoAccount {

        private String email;

    }

    public Member kakaoToMember(TokenDto tokenDto, String memberType){
        return Member.builder()
                .memberName(properties.get("nickname"))
                .email(kakaoAccount.getEmail())
                .memberRole(MemberRole.ADMIN)
                .memberType(MemberType.valueOf(memberType))
                .refreshToken(tokenDto.getRefreshToken())
                .tokenExpirationTime(DateTimeUtils.convertToLocalDateTime(tokenDto.getRefreshTokenExpireTime()))
                .build();

    }

}