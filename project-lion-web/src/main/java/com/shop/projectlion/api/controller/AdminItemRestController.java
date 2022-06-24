package com.shop.projectlion.api.controller;

import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.delivery.service.DeliveryService;
import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.item.service.ItemService;
import com.shop.projectlion.domain.itemimage.service.ItemImageService;
import com.shop.projectlion.domain.member.constant.MemberRole;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.member.service.MemberService;
import com.shop.projectlion.global.annotation.Permission;
import com.shop.projectlion.global.annotation.TokenCheck;
import com.shop.projectlion.infra.FileService;
import com.shop.projectlion.infra.UploadFile;
import com.shop.projectlion.web.adminitem.dto.InsertItemDto;
import com.shop.projectlion.web.adminitem.dto.UpdateItemDto;
import com.shop.projectlion.web.adminitem.service.UpdateItemDtoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/items")
@Slf4j
public class AdminItemRestController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final DeliveryService deliveryService;
    private final ItemImageService itemImageService;
    private final UpdateItemDtoService updateItemDtoService;
    private final FileService fileService;


    @Permission(role = MemberRole.ADMIN)
    @TokenCheck
    @GetMapping("/{itemId}")
    public ResponseEntity<UpdateItemDto> itemEdit(@PathVariable Long itemId) {

        // 배송 정보
        List<Delivery> delivery = deliveryService.findDeliveryAll();

        // 상품 이미지

        List<UpdateItemDto.ItemImageDto> itemImageDto = updateItemDtoService.mappingToItemImageDto(itemId);


        Item item = itemService.findById(itemId).get();
        // 상품 정보
        UpdateItemDto updateItemDto = new UpdateItemDto(item, itemImageDto);


        return ResponseEntity.ok(updateItemDto);
    }


    @RequestMapping(value = "/{itemId}", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<?> editItemInformation(@PathVariable Long itemId, @Valid @RequestBody UpdateItemDto updateItemDto, BindingResult result){

        List<UpdateItemDto.ItemImageDto> itemImageDto = updateItemDtoService.mappingToItemImageDto(itemId);
        updateItemDto.setItemImageDtos(itemImageDto);

        if(result.hasErrors()){

            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }
        log.info("updateitem={}", updateItemDto);
        Delivery delivery = deliveryService.findDeliveryByid(updateItemDto.getDeliveryId()).get();
        Item item = updateItemDto.toEntity(delivery);

        itemService.modifyItem(item);

        return ResponseEntity.ok(updateItemDto);
    }

}
