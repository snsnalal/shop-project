package com.shop.projectlion.api.health;


import com.shop.projectlion.global.config.FeignConfiguration;
import com.shop.projectlion.web.health.dto.HealthCheckDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "healthCheck", url = "http://localhost:8084", configuration = {FeignConfiguration.class})
public interface HealthCheckFeignClient {

    @GetMapping("/health-check")
    HealthCheckDto getServerStatus();
}
