package com.shop.projectlion.infra;

import com.shop.projectlion.domain.itemimage.entity.ItemImage;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UploadFile {

    private String originalFileName;  // 원본 파일 이름
    private String storeFileName;     // 저장된 파일 이름
    private String fileUploadUrl;     // 파일 저장 경로

    public UploadFile(String originalFileName, String storeFileName, String fileUploadUrl) {
        this.originalFileName = originalFileName;
        this.storeFileName = storeFileName;
        this.fileUploadUrl = fileUploadUrl;
    }

    public ItemImage toEntity(){
        return ItemImage.builder()
                .imageName(storeFileName)
                .imageUrl(fileUploadUrl)
                .originalImageName(originalFileName)
                .build();
    }

}