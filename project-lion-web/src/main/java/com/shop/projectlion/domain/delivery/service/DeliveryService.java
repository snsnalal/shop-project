package com.shop.projectlion.domain.delivery.service;


import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.delivery.repository.DeliveryRepository;
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


    public Optional<Delivery> findDeliveryByid(Long id){
        log.info("deliveryId={}", id);
        return deliveryRepository.findById(id);
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
