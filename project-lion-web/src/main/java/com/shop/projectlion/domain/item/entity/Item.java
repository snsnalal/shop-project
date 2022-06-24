package com.shop.projectlion.domain.item.entity;


import com.shop.projectlion.domain.base.BaseEntity;
import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.item.constant.ItemSellStatus;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "item")
@DynamicUpdate
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    private String itemDetail;

    private String itemName;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    private Integer price;

    private Integer stock;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Builder
    public Item(Long itemId, String itemDetail, String itemName, ItemSellStatus itemSellStatus, Integer price, Integer stock, Delivery delivery){

        this.itemId = itemId;
        this.itemDetail = itemDetail;
        this.itemName = itemName;
        this.itemSellStatus = itemSellStatus;
        this.price = price;
        this.stock = stock;
        this.delivery = delivery;
    }

    public void modifyItem(Item item){
        this.itemDetail = item.getItemDetail();
        this.itemName = item.getItemName();
        this.itemSellStatus = item.getItemSellStatus();
        this.price = item.getPrice();
        this.stock = item.getStock();
        this.delivery = item.getDelivery();
    }

    public static Item createItem(Item item, Member member, Delivery delivery) {

        Item returnItem = Item.builder()
                .itemDetail(item.getItemDetail())
                .itemName(item.getItemName())
                .itemSellStatus(item.getItemSellStatus())
                .price(item.getPrice())
                .stock(item.getStock())
                .build();

        returnItem.member = member;
        returnItem.delivery = delivery;

        return returnItem;
    }

    public void addStock(Integer quantity){
        this.stock += quantity;
    }

    public void removeStock(Integer quantity){
        int restStock = this.stock - quantity;

        if (restStock < 0){
            throw new BusinessException(ErrorCode.STOCK_NOT_ENOUGH_ERROR);
        }
        this.stock = restStock;
    }

    public boolean checkStock(Integer quantity){
        if (stock >= quantity){
            return true;
        }
        return false;
    }

}
