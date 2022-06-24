package com.shop.projectlion.domain.order.entity;


import com.shop.projectlion.domain.base.BaseEntity;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.order.constant.OrderStatus;
import com.shop.projectlion.domain.orderitem.entity.OrderItem;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Orders extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime orderTime;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Orders(OrderStatus orderStatus, LocalDateTime localDateTime, Member member){
        this.orderStatus = orderStatus;
        this.orderTime = localDateTime;
        this.member = member;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItemList.add(orderItem);
        orderItem.setOrders(this);
    }

    public void cancelOrder(){
        this.orderStatus = OrderStatus.CANCEL;
    }

    public static Orders createOrder(Member member, OrderItem... orderItems){

        Orders orders = new Orders(OrderStatus.ORDER, LocalDateTime.now(), member);
        for (OrderItem orderItem : orderItems){
            orders.addOrderItem(orderItem);
        }

        return orders;
    }


}
