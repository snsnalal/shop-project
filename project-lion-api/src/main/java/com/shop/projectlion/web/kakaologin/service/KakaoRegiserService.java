package com.shop.projectlion.web.kakaologin.service;


import com.shop.projectlion.api.login.KakaoRegisterFeign;
import com.shop.projectlion.api.login.KakaoUserInfo;
import com.shop.projectlion.domain.jwt.constant.GrantType;
import com.shop.projectlion.domain.jwt.dto.TokenDto;
import com.shop.projectlion.domain.jwt.service.TokenManager;
import com.shop.projectlion.domain.member.constant.MemberRole;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.member.service.MemberService;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import com.shop.projectlion.web.kakaologin.dto.LogintokenDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class KakaoRegiserService {

    private final KakaoRegisterFeign kakaoRegisterFeign;
    private final TokenManager tokenManager;
    private final MemberService memberService;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf-8";

    public KakaoUserInfo getKakaoUserInfo(String token){
        return kakaoRegisterFeign.getKakaoUserInfo(CONTENT_TYPE,
                GrantType.BEARRER.getType() + " " + token);
    }

    public TokenDto createTokenDto(String email, MemberRole role){
        return tokenManager.createTokenDto(email, role);
    }


    public Member findMemberByRefreshToken(String refreshToken){
        return memberService.findByRefreshToken(refreshToken).orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION));
    }


    @Transactional
    public void logout(String accessToken){

        Claims tokenClaims = tokenManager.getTokenClaims(accessToken);
        Date accessTokenExpiration = tokenClaims.getExpiration();
        if (tokenManager.isTokenExpired(accessTokenExpiration)) {
            throw new BusinessException(ErrorCode.NOT_VALID_TOKEN);
        }

        String email = tokenManager.getMemberEmail(accessToken);
        Optional<Member> memberByEmail = memberService.findMemberByEmail(email);
        Member member = memberByEmail.get();
        member.expireRefreshToken();
    }

    public LogintokenDto reCreateAccessToken(Member member){
        Date accessTokenExpireTime = tokenManager.createAccessTokenExpireTime();
        String accessToken = tokenManager.createAccessToken(member.getEmail(), member.getMemberRole() , accessTokenExpireTime);

        return LogintokenDto.of("ACCESS", accessToken, accessTokenExpireTime);
    }

    @Transactional
    public void registerMember(Member member) {
        Optional<Member> memberByEmail = memberService.findMemberByEmail(member.getEmail());
        if (memberByEmail.isEmpty()){
            memberService.join(member);
        }else {
            log.info("가입있음");
            Date refreshTokenExpireTime = tokenManager.createRefreshTokenExpireTime();
            String refreshToken = tokenManager.createRefreshToken(member.getEmail(), refreshTokenExpireTime);
            memberByEmail.get().reRegisterRefreshToken(refreshToken, refreshTokenExpireTime);
        }
    }
}
