package com.shop.projectlion.domain.delivery.entity;


import com.shop.projectlion.domain.base.BaseEntity;
import com.shop.projectlion.domain.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "delivery")
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long deliveryId;

    private int deliveryFee;

    private String deliveryName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Delivery(int deliveryFee, String deliveryName){
        this.deliveryFee = deliveryFee;
        this.deliveryName = deliveryName;
    }

    public static Delivery createDelivery(Delivery delivery, Member member){
        Delivery returnDelivery = Delivery.builder()
                .deliveryFee(delivery.getDeliveryFee())
                .deliveryName(delivery.getDeliveryName())
                .build();

        returnDelivery.member = member;

        return delivery;
    }
}
