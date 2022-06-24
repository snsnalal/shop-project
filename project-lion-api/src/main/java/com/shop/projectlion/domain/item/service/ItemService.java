package com.shop.projectlion.domain.item.service;


import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.item.repository.ItemRepository;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public int findSellItemSize(String searchWord){
        return itemRepository.getTotalSize(searchWord).size();
    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item findById(Long id){
        return itemRepository.findById(id).orElseThrow(()->new BusinessException(ErrorCode.ITEM_NOT_EXISTS));
    }


    @Transactional
    public void registerItem(Item item){

        itemRepository.save(item);

    }

    public List<Item> findItemForPage(String searchWord, int start){

        return itemRepository.findItemForPage(searchWord, start);
    }

    @Transactional
    public void modifyItem(Item item){
        Item findItem = itemRepository.findById(item.getItemId()).get();

        findItem.modifyItem(item);
    }

}
