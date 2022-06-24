package com.shop.projectlion.domain.item.repository;


import com.shop.projectlion.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select * from Item where (item_name like %:search% or item_detail like %:search%) and item_sell_status = 'SELL' limit :start, 6", nativeQuery = true)
    public List<Item> findItemForPage(@Param(value = "search")String searchWord,@Param(value ="start")int start);

    @Query(value = "select it from Item it where (it.itemName like %:sea% or it.itemDetail like %:sea%) and it.itemSellStatus = 'SELL'")
    public List<Item> getTotalSize(@Param(value = "sea")String searchWord);

}
