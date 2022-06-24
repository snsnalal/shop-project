package com.shop.projectlion.api.interceptor;

import com.shop.projectlion.domain.jwt.service.TokenManager;
import com.shop.projectlion.domain.member.constant.MemberRole;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.member.service.MemberService;
import com.shop.projectlion.global.annotation.Permission;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PermissionInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;
    private final MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)){
            return true;
        }


        String authorization = request.getHeader("Authorization");

        if(!StringUtils.hasText(authorization)){
            throw new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        String memberEmail = tokenManager.getMemberEmail(authorization.split(" ")[1]);
        Optional<Member> memberByEmail = memberService.findMemberByEmail(memberEmail);

        if (memberByEmail.isEmpty()){
            throw new BusinessException(ErrorCode.MEMBER_NOT_EXISTS);
        }

        if (MemberRole.ADMIN.equals(memberByEmail.get().getMemberRole())){
            return true;
        }

        throw new BusinessException(ErrorCode.CANT_ACCESS_ROLE);

    }
}
