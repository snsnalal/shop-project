package com.shop.projectlion.web.main.dto;

import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.itemimage.entity.ItemImage;
import lombok.*;


@Getter @Setter
@NoArgsConstructor
public class MainItemDto {

    private Long itemId;

    private String itemName;

    private String itemDetail;

    private String imageUrl;

    private Integer price;

    public MainItemDto(Item item, ItemImage itemImage){
        this.itemId = item.getItemId();
        this.itemName = item.getItemName();
        this.itemDetail = item.getItemDetail();
        this.imageUrl = itemImage.getImageName();
        this.price = item.getPrice();
    }

}
