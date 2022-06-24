package com.shop.projectlion.web.health.service;


import com.shop.projectlion.api.health.HealthCheckFeignClient;
import com.shop.projectlion.web.health.dto.HealthCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

    private final HealthCheckFeignClient healthCheckFeignClient;

    public HealthCheckDto getServerStatus(){
        return healthCheckFeignClient.getServerStatus();
    }
}
