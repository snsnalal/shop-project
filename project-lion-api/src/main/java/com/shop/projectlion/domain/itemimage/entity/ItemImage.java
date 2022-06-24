package com.shop.projectlion.domain.itemimage.entity;

import com.shop.projectlion.domain.base.BaseEntity;
import com.shop.projectlion.domain.item.constant.BooleanToYNConverter;
import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.infra.file.UploadFile;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item_image")
@DynamicUpdate
public class ItemImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemImageId;

    private String imageName;
    private String imageUrl;
    private String originalImageName;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean isRepImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemImage(String imageName, String imageUrl, String originalImageName, boolean isRepImage , Item item){
        this.imageName = imageName;
        this.imageUrl = imageUrl;
        this.originalImageName = originalImageName;
        this.isRepImage = isRepImage;
        this.item = item;
    }
    
    //이미지가 없을 떄 쓰는 생성자
    public ItemImage(boolean isRepImage, Item item){
        this.imageName = null;
        this.imageUrl = null;
        this.originalImageName = null;
        this.isRepImage = isRepImage;
        this.item = item;
    }

    public void modifyItemImage(){
        this.imageName = null;
        this.imageUrl = null;
        this.originalImageName = null;
    }

    public void modifyItemImage(UploadFile uploadFile){
        this.imageName = uploadFile.getStoreFileName();
        this.imageUrl = uploadFile.getFileUploadUrl();
        this.originalImageName = uploadFile.getOriginalFileName();
    }


    public static ItemImage createItemImage(ItemImage itemImage, boolean isRepImage , Item item){

        ItemImage returnItemImage = ItemImage.builder()
                .imageName(itemImage.getImageName())
                .imageUrl(itemImage.getImageUrl())
                .originalImageName(itemImage.getOriginalImageName())
                .isRepImage(isRepImage)
                .item(item)
                .build();

        return returnItemImage;
    }

}
