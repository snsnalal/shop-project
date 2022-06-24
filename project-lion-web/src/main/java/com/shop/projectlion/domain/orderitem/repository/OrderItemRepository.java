package com.shop.projectlion.domain.orderitem.repository;

import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.order.entity.Orders;
import com.shop.projectlion.domain.orderitem.entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(value = "select oi from OrderItem oi inner join fetch oi.orders o where o.member.memberId = :id")
    public List<OrderItem> findOrderItemList(Long id, Pageable pageable);

    @Query(value = "select oi from OrderItem oi inner join fetch oi.orders o where o.member.memberId = :id")
    public List<OrderItem> getOrderItemSize(Long id);
}
