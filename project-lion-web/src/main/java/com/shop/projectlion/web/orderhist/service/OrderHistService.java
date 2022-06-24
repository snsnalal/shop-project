package com.shop.projectlion.web.orderhist.service;


import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.item.service.ItemService;
import com.shop.projectlion.domain.itemimage.entity.ItemImage;
import com.shop.projectlion.domain.itemimage.service.ItemImageService;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.member.service.MemberService;
import com.shop.projectlion.domain.order.entity.Orders;
import com.shop.projectlion.domain.order.service.OrderService;
import com.shop.projectlion.domain.orderitem.entity.OrderItem;
import com.shop.projectlion.domain.orderitem.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderHistService {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final ItemImageService itemImageService;

    public Member findMemberByEmail(String email){
        return memberService.findMemberByEmail(email).get();
    }

    public ItemImage findItemImageByItem(Item item){
        return itemImageService.findItemImageByItemId(item.getItemId()).get(0);
    }

    public Item findItemByOrderItemId(Long id){
        return itemService.findById(id).get();
    }

    @Transactional
    public void cancelItem(OrderItem orderItem){
        Item findItem = findItemByOrderItemId(orderItem.getItem().getItemId());
        findItem.addStock(orderItem.getCount());
    }

    @Transactional
    public void cancelOrder(OrderItem orderItem){
        Orders findOrders = orderService.findOrderById(orderItem.getOrders().getOrderId());

        findOrders.cancelOrder();

    }

    public OrderItem findOrderItemById(Long id){
        return orderItemService.findOrderItem(id);
    }

    public int getOrderItemSize(Long id){
        return orderItemService.getOrderItemSize(id);
    }

    public List<OrderItem> findOrderItemsForPage(Long id, Pageable pageable){
        return orderItemService.findOrderItemList(id, pageable);
    }

}
