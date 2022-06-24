package com.shop.projectlion.domain.itemimage.service;


import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.itemimage.entity.ItemImage;
import com.shop.projectlion.infra.FileService;
import com.shop.projectlion.infra.UploadFile;
import com.shop.projectlion.web.adminitem.dto.UpdateItemDto;
import com.shop.projectlion.domain.itemimage.repository.ItemImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemImageService{

    private final ItemImageRepository itemImageRepository;
    private final FileService fileService;

    public List<ItemImage> findItemImageByItemId(Long itemId){
        return itemImageRepository.findByItemId(itemId);
    }

    @Transactional
    public void modifyItemImage(UpdateItemDto updateItemDto, Long itemId) throws IOException {
        List<ItemImage> itemImageByItemId = findItemImageByItemId(itemId);

        List<UploadFile> uploadFiles = fileService.storeFiles(updateItemDto.getItemImageFiles());
        int index = 0;

        for(int i = 0; i < 5; i++){
            //이미지 수정페이지에서 받아온 파일 목록과 현재 DB에 저장된 파일 비교
            if (updateItemDto.getOriginalImageNames().get(i).isEmpty() && itemImageByItemId.get(i).getOriginalImageName() != null){
                fileService.deleteFile(itemImageByItemId.get(i).getImageUrl());
                itemImageByItemId.get(i).modifyItemImage();
            }
            //입력폼에 업로드한 파일 목록이 비어있는지 확인
            else if (!updateItemDto.getItemImageFiles().get(i).getOriginalFilename().isEmpty()){
                //DB에 저장 itemImage가 null이 아니면
                if(itemImageByItemId.get(i).getImageUrl() != null){
                    fileService.deleteFile(itemImageByItemId.get(i).getImageUrl());
                }
                itemImageByItemId.get(i).modifyItemImage(uploadFiles.get(index));
                index++;
            }
        }
    }

    @Transactional
    public void saveItemImage(List<UploadFile> uploadFiles, Item item) throws IOException {

        ItemImage itemImage;
        boolean isRepImage = true;
        for(int i = 0; i < 5; i++){

            if (i != 0 && isRepImage){
                isRepImage = false;
            }

            if(i < uploadFiles.size()){
                itemImage = ItemImage.createItemImage(uploadFiles.get(i).toEntity(), isRepImage, item);
            }else{
                itemImage = new ItemImage(isRepImage, item);
            }

            itemImageRepository.save(itemImage);
        }

    }

}
