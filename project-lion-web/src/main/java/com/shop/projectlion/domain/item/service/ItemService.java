package com.shop.projectlion.domain.item.service;


import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Item> findById(Long id){
        return itemRepository.findById(id);
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
