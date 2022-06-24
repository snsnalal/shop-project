package com.shop.projectlion.api.interceptor;


import com.shop.projectlion.domain.jwt.service.TokenManager;
import com.shop.projectlion.global.annotation.Permission;
import com.shop.projectlion.global.annotation.TokenCheck;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class TokenCheckInterceptor implements HandlerInterceptor {

    private final TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        TokenCheck tokenCheck = handlerMethod.getMethodAnnotation(TokenCheck.class);

        if (tokenCheck == null){
            return true;
        }

        String authorization = request.getHeader("Authorization");

        if(!StringUtils.hasText(authorization)){
            throw new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        Claims tokenClaims = tokenManager.getTokenClaims(authorization.split(" ")[1]);

        if(tokenManager.isTokenExpired(tokenClaims.getExpiration())){
            throw new BusinessException(ErrorCode.ACCESS_TOKEN_EXPIRED);
        }

        return true;
    }
}
