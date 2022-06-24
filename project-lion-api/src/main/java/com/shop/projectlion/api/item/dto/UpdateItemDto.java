package com.shop.projectlion.api.item.dto;

import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.item.constant.ItemSellStatus;
import com.shop.projectlion.domain.item.entity.Item;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateItemDto {

    private Long itemId;

    private String itemName;

    private Integer price;

    private String itemDetail;

    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private Long deliveryId;

    private int deliveryFee;

    private List<MultipartFile> itemImageFiles;

    private List<ItemImageDto> itemImageDtos;

    private List<String> originalImageNames;


    @Builder
    public UpdateItemDto(Item item, List<ItemImageDto> itemImageDtos){
        this.itemId = item.getItemId();
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.itemDetail = item.getItemDetail();
        this.stockNumber = item.getStock();
        this.itemSellStatus = item.getItemSellStatus();
        this.deliveryId = item.getDelivery().getDeliveryId();
        this.deliveryFee = item.getDelivery().getDeliveryFee();
        this.itemImageDtos = itemImageDtos;
    }



    @Builder
    @Getter @Setter
    @AllArgsConstructor
    public static class ItemImageDto {
        private Long itemImageId;
        private String originalImageName;
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