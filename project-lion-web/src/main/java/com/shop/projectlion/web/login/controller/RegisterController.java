package com.shop.projectlion.web.login.controller;



import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.web.login.service.LoginService;
import com.shop.projectlion.global.error.exception.EmailDuplicateValidator;
import com.shop.projectlion.global.error.exception.ErrorCode;
import com.shop.projectlion.web.login.dto.MemberRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.thymeleaf.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final LoginService loginService;
    private final EmailDuplicateValidator emailDuplicateValidator;
    private final PasswordEncoder passwordEncoder;

    @InitBinder
    public void validatorBinder(WebDataBinder binder){

        binder.addValidators(emailDuplicateValidator);
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("memberRegisterDto", new MemberRegisterDto());
        return "login/registerform";
    }

    @PostMapping("/register")
    public String createMember(@Valid @ModelAttribute("memberRegisterDto")MemberRegisterDto memberRegisterDto, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            return "login/registerform";
        }
        else if(!StringUtils.equals(memberRegisterDto.getPassword(), memberRegisterDto.getPassword2())){
            result.reject("mismatchedPassword", ErrorCode.MISMATCHED_PASSWORD.getMessage());
            return "login/registerform";
        }

        Member member = memberRegisterDto.toEntity(passwordEncoder);
        Member saveMember = Member.createMember(member);

        loginService.registerMember(saveMember);

        return "login/registerform";
    }
}
