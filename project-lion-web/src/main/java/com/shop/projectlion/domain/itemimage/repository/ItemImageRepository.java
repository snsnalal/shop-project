package com.shop.projectlion.domain.itemimage.repository;

import com.shop.projectlion.domain.itemimage.entity.ItemImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemImageRepository{

    private final EntityManager em;

    public void save(ItemImage itemImage){
        em.persist(itemImage);
    }

    public List<ItemImage> findByItemId(Long itemId){
        return em.createQuery("select i from ItemImage i where i.item.itemId = :itemId")
                .setParameter("itemId", itemId)
                .getResultList();
    }
}
