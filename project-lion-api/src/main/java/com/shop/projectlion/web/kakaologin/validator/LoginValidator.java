package com.shop.projectlion.web.kakaologin.validator;

import com.shop.projectlion.domain.jwt.constant.GrantType;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
@Slf4j
public class LoginValidator {

    public void validatorAuthorization(String authorization){

        if (!StringUtils.hasText(authorization)){
            throw new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        String[] authorizations = authorization.split(" ");

        if (authorizations.length < 2 || (!GrantType.BEARRER.getType().equals(authorizations[0]))){
            throw new BusinessException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
        }
    }

    public void validatorMemberType(String memberType){
        if (!memberType.equals("KAKAO")){
            throw new BusinessException(ErrorCode.INVALID_MEMBER_TYPE);
        }
    }

}
