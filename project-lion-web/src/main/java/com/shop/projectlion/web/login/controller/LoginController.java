package com.shop.projectlion.web.login.controller;

import com.shop.projectlion.global.config.UserdetailsImpl;
import com.shop.projectlion.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {


    @GetMapping("/login")
    public String login(@RequestParam(required = false)String isError, Model model) {

        if(Boolean.valueOf(isError)) {
            model.addAttribute("loginError", ErrorCode.LOGIN_ERROR.getMessage());
        }

        return "login/loginForm";
    }




}
