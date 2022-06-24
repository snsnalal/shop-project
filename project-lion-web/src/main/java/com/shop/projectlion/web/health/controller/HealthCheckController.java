package com.shop.projectlion.web.health.controller;


import com.shop.projectlion.web.health.dto.HealthCheckDto;
import com.shop.projectlion.web.health.service.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthCheckService healthCheckService;

    @GetMapping("/api/health")
    public HealthCheckDto checkServerStatus(){
        return healthCheckService.getServerStatus();
    }

    @GetMapping("/health-check")
    public HealthCheckDto getServerStatus(){

        HealthCheckDto healthCheckDto = new HealthCheckDto("true", "ok");

        return healthCheckDto;
    }

}
