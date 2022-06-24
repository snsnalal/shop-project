package com.shop.projectlion.web.login.dto;

import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.member.constant.MemberRole;
import com.shop.projectlion.domain.member.constant.MemberType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
public class MemberRegisterDto {

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    private String name;
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    private String email;
    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 16, message = "비밀번호는 8자 이상 16자 이하로 입력해주세요")
    private String password;

    private String password2;


    public Member toEntity(PasswordEncoder passwordEncoder)
    {
        return Member.builder()
                .memberName(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .memberRole(MemberRole.USER)
                .memberType(MemberType.GENERAL)
                .build();
    }


}