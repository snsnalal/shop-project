package com.shop.projectlion.global.error.exception;


import com.shop.projectlion.domain.member.repository.MemberRepository;
import com.shop.projectlion.web.login.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class EmailDuplicateValidator extends EmailValidator<MemberRegisterDto> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidation(MemberRegisterDto dto, Errors errors) {
        if (!memberRepository.findByEmail(dto.getEmail()).isEmpty()){
            errors.rejectValue("email", "아이디 중복", ErrorCode.ALREADY_REGISTERED_MEMBER.getMessage());
        }
    }
}
