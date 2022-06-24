package com.shop.projectlion.api.item.service;


import com.shop.projectlion.api.item.dto.UpdateItemDto;
import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.delivery.service.DeliveryService;
import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemApiService {

    private final ItemService itemService;
    private final DeliveryService deliveryService;
    private final UpdateItemDtoService updateItemDtoService;

    public List<UpdateItemDto.ItemImageDto> mappingToItemImageDto(Long itemId){
        return updateItemDtoService.mappingToItemImageDto(itemId);
    }

    public Item findItemById(Long id){
        return itemService.findById(id);
    }

    public Delivery findDeliveryById(Long id){
        return deliveryService.findDeliveryById(id);
    }

    @Transactional
    public void modifyItem(Item item){
        itemService.modifyItem(item);
    }
}
