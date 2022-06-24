package com.shop.projectlion.domain.delivery.service;


import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.delivery.repository.DeliveryRepository;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;


    public Delivery findDeliveryById(Long id){

        return deliveryRepository.findById(id).orElseThrow(()->new BusinessException(ErrorCode.DELIVERY_NOT_EXISTS));
    }


    public List<Delivery> findDeliveryAll(){

        return deliveryRepository.findAll();
    }

    @Transactional
    public void saveDelivery(Delivery findDelivery, Member member){

        Delivery delivery = Delivery.createDelivery(findDelivery, member);
        deliveryRepository.save(delivery);
    }
}
