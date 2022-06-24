package com.shop.projectlion.domain.orderitem.entity;

import com.shop.projectlion.domain.base.BaseEntity;
import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.order.entity.Orders;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private int count;

    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Setter
    private Orders orders;

    public static OrderItem orderItem(int count, int orderPrice, Item item, Orders orders){

        item.removeStock(Integer.valueOf(count));

        return builder()
                .count(count)
                .orderPrice(orderPrice)
                .item(item)
                .orders(orders)
                .build();
    }

    public int calcOriginOrderPrice(){
        return orderPrice - item.getDelivery().getDeliveryFee();
    }

}
