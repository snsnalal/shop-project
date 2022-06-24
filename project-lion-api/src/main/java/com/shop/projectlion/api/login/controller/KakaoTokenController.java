package com.shop.projectlion.api.login.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KakaoTokenController {


    @GetMapping("/kakaologin")
    public String login(){
        return "loginform";
    }



}