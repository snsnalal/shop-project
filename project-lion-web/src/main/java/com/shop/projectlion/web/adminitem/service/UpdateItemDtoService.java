package com.shop.projectlion.web.adminitem.service;


import com.shop.projectlion.web.adminitem.dto.UpdateItemDto;
import com.shop.projectlion.domain.itemimage.repository.UpdateItemDtoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UpdateItemDtoService {

    private final UpdateItemDtoRepository updateItemDtoRepository;

    public List<Object[]> findItemImageById(Long itemId){
        return updateItemDtoRepository.findItemImageDtoById(itemId);
    }

    public List<UpdateItemDto.ItemImageDto> mappingToItemImageDto(Long itemId){
        List<Object[]> findItemImageList = findItemImageById(itemId);

        List<UpdateItemDto.ItemImageDto> itemImageDtos = new ArrayList<>();

        for(Object[] obj : findItemImageList){
            if(obj[1] != null){
                itemImageDtos.add(new UpdateItemDto.ItemImageDto((Long)obj[0], obj[1].toString()));
            }
            else{
                itemImageDtos.add(new UpdateItemDto.ItemImageDto(null, null));
            }
        }

        return itemImageDtos;
    }

}
