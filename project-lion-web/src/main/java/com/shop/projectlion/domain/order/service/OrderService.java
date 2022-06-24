package com.shop.projectlion.domain.order.service;


import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.order.entity.Orders;
import com.shop.projectlion.domain.order.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;

    public Orders findOrderById(Long id){
        return ordersRepository.findById(id).get();
    }

    @Transactional
    public Orders order(Orders orders){

        ordersRepository.save(orders);
        return orders;
    }

}
