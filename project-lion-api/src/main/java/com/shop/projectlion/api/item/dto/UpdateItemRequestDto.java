package com.shop.projectlion.api.item.dto;

import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.item.constant.ItemSellStatus;
import com.shop.projectlion.domain.item.entity.Item;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class UpdateItemRequestDto {

    private Long itemId;

    private String itemName;

    private Integer price;

    private String itemDetail;

    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private Long deliveryId;

    @Builder
    public UpdateItemRequestDto(Long itemId, String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, Long deliveryId ){
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.itemDetail = itemDetail;
        this.stockNumber = stockNumber;
        this.itemSellStatus = itemSellStatus;
        this.deliveryId = deliveryId;
    }

    public UpdateItemRequestDto of (Long itemId, String itemName, Integer price, String itemDetail, Integer stockNumber, ItemSellStatus itemSellStatus, Long deliveryId){
        return UpdateItemRequestDto.builder()
                .itemId(itemId)
                .itemName(itemName)
                .price(price)
                .itemDetail(itemDetail)
                .stockNumber(stockNumber)
                .itemSellStatus(itemSellStatus)
                .deliveryId(deliveryId)
                .build();

    }

    public Item toEntity(Delivery delivery){
        return Item.builder()
                .itemId(itemId)
                .itemName(itemName)
                .itemDetail(itemDetail)
                .itemSellStatus(itemSellStatus)
                .price(price)
                .stock(stockNumber)
                .delivery(delivery)
                .build();
    }
}
