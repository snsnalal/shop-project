package com.shop.projectlion.web.health.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class HealthCheckDto {

    private String status;
    private String health;

}
