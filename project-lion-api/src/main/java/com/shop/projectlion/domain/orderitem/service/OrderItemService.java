package com.shop.projectlion.domain.orderitem.service;


import com.shop.projectlion.domain.orderitem.entity.OrderItem;
import com.shop.projectlion.domain.orderitem.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> findOrderItemList(Long id, Pageable pageable){
        return orderItemRepository.findOrderItemList(id, pageable);
    }

    public int getOrderItemSize(Long id){
        return orderItemRepository.getOrderItemSize(id).size();
    }

    @Transactional
    public void saveOrderItem(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }

    public OrderItem findOrderItem(Long id) {
        return orderItemRepository.findById(id).get();
    }
}
