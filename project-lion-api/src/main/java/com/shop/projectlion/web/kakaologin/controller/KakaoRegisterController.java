package com.shop.projectlion.web.kakaologin.controller;


import com.shop.projectlion.api.login.KakaoUserInfo;
import com.shop.projectlion.domain.jwt.dto.TokenDto;
import com.shop.projectlion.domain.member.constant.MemberRole;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.web.kakaologin.dto.LogintokenDto;
import com.shop.projectlion.web.kakaologin.dto.RegisterDto;
import com.shop.projectlion.web.kakaologin.service.KakaoRegiserService;
import com.shop.projectlion.web.kakaologin.validator.LoginValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class KakaoRegisterController {

    private final KakaoRegiserService kakaoRegiserService;
    private final LoginValidator loginValidator;

    @PostMapping(value = "/oauth-login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDto> kakaoRegister(@RequestBody RegisterDto registerDto, HttpServletRequest request){

        String authorization = request.getHeader("Authorization");

        loginValidator.validatorAuthorization(authorization);
        loginValidator.validatorMemberType(registerDto.getMemberType());

        String accessToken = authorization.split(" ")[1];

        KakaoUserInfo kakaoUserInfo = kakaoRegiserService.getKakaoUserInfo(accessToken);
        TokenDto tokenDto = kakaoRegiserService.createTokenDto(kakaoUserInfo.getKakaoAccount().getEmail(), MemberRole.USER);
        Member member = kakaoUserInfo.kakaoToMember(tokenDto, registerDto.getMemberType());

        kakaoRegiserService.registerMember(member);

        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/token")
    public LogintokenDto reRegisterAccessToken(@RequestHeader(name = "Authorization") String refreshToken){

        loginValidator.validatorAuthorization(refreshToken);

        Member findMember = kakaoRegiserService.findMemberByRefreshToken(refreshToken.split(" ")[1]);

        LogintokenDto logintokenDto = kakaoRegiserService.reCreateAccessToken(findMember);

        return logintokenDto;
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader(name = "Authorization") String accessToken){

        loginValidator.validatorAuthorization(accessToken);

        kakaoRegiserService.logout(accessToken);

        return "logout success";
    }

}
