package com.shop.projectlion.web.itemdtl.service;


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
import com.shop.projectlion.web.itemdtl.dto.ItemDtlDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemDtlService {

    private final ItemService itemService;
    private final ItemImageService itemImageService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final MemberService memberService;

    public Item findByItemId(Long itemId){
        return itemService.findById(itemId).get();
    }

    public ItemDtlDto mappingToItemDtlDtoByItemId(Long itemId){

        Optional<Item> findItem = itemService.findById(itemId);
        List<ItemImage> itemImageByItemId = itemImageService.findItemImageByItemId(itemId);
        List<ItemDtlDto.ItemImageDto> itemImageDtos = new ArrayList<>();

        for (ItemImage itemImage : itemImageByItemId){

            itemImageDtos.add(new ItemDtlDto.ItemImageDto(itemImage.getImageName()));
        }

        return ItemDtlDto.builder()
                .itemId(findItem.get().getItemId())
                .itemName(findItem.get().getItemName())
                .price(findItem.get().getPrice())
                .itemDetail(findItem.get().getItemDetail())
                .stockNumber(findItem.get().getStock())
                .itemSellStatus(findItem.get().getItemSellStatus())
                .deliveryFee(findItem.get().getDelivery().getDeliveryFee())
                .itemImageDtos(itemImageDtos)
                .build();

    }

    public Member findMemberByEmail(String email){
        return memberService.findMemberByEmail(email).get();
    }

    @Transactional
    public void saveOrder(Orders orders){

        orderService.order(orders);
    }

    @Transactional
    public void saveOrderItem(OrderItem orderItem){

        orderItemService.saveOrderItem(orderItem);
    }

}
