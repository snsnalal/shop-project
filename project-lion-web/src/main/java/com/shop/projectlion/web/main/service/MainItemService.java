package com.shop.projectlion.web.main.service;

import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.item.service.ItemService;
import com.shop.projectlion.domain.itemimage.entity.ItemImage;
import com.shop.projectlion.domain.itemimage.service.ItemImageService;
import com.shop.projectlion.web.main.dto.MainItemDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MainItemService {

    private final ItemService itemService;
    private final ItemImageService itemImageService;

    public List<MainItemDto> getMainItemDto(String searchWord, int start){
        List<Item> itemList = itemService.findItemForPage(searchWord, start);
        List<List<ItemImage>> itemImageList = new ArrayList<>();


        for (Item item : itemList){
            itemImageList.add(itemImageService.findItemImageByItemId(item.getItemId()));
        }

        List<MainItemDto> mainItemDtos = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++){
            mainItemDtos.add(new MainItemDto(itemList.get(i), itemImageList.get(i).get(0)));
        }

        return mainItemDtos;
    }

    public int getTotalSize(String searchWord){
        return itemService.findSellItemSize(searchWord);
    }

}
