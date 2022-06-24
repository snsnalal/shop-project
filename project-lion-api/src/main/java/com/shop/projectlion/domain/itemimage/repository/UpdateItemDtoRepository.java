package com.shop.projectlion.domain.itemimage.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UpdateItemDtoRepository{

    private final EntityManager em;

    public List<Object[]> findItemImageDtoById(Long itemId){

        return em.createQuery("select i.itemImageId, i.originalImageName from ItemImage i where i.item.itemId = :itemId", Object[].class)
                .setParameter("itemId", itemId)
                .getResultList();
    }

}
