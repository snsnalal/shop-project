package com.shop.projectlion.web.adminitem.dto;

import com.shop.projectlion.domain.delivery.entity.Delivery;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Builder
public class DeliveryDto {

    private Long deliveryId;
    private String deliveryName;
    private int deliveryFee;


    public Delivery toEntity(){
        return Delivery.builder()
                .deliveryFee(deliveryFee)
                .deliveryName(deliveryName)
                .build();
    }


}