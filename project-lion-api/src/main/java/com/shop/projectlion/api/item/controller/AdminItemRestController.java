package com.shop.projectlion.api.item.controller;

import com.shop.projectlion.api.item.dto.UpdateItemRequestDto;
import com.shop.projectlion.api.item.service.ItemApiService;
import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.delivery.service.DeliveryService;
import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.item.service.ItemService;
import com.shop.projectlion.domain.itemimage.service.ItemImageService;
import com.shop.projectlion.domain.member.constant.MemberRole;
import com.shop.projectlion.domain.member.service.MemberService;
import com.shop.projectlion.global.annotation.Permission;
import com.shop.projectlion.global.annotation.TokenCheck;
import com.shop.projectlion.infra.file.FileService;
import com.shop.projectlion.api.item.dto.UpdateItemDto;
import com.shop.projectlion.api.item.service.UpdateItemDtoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/items")
@Slf4j
public class AdminItemRestController {

    private final ItemApiService itemApiService;


    @GetMapping("/{itemId}")
    public ResponseEntity<UpdateItemDto> itemEdit(@PathVariable Long itemId) {

        List<UpdateItemDto.ItemImageDto> itemImageDto = itemApiService.mappingToItemImageDto(itemId);
        Item item = itemApiService.findItemById(itemId);
        UpdateItemDto updateItemDto = new UpdateItemDto(item, itemImageDto);

        return ResponseEntity.ok(updateItemDto);
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity<?> editItemInformation(@PathVariable Long itemId, @RequestBody UpdateItemRequestDto updateItemRequestDto){

        Delivery delivery = itemApiService.findDeliveryById(updateItemRequestDto.getDeliveryId());

        Item item = updateItemRequestDto.toEntity(delivery);

        itemApiService.modifyItem(item);

        return ResponseEntity.ok(updateItemRequestDto);
    }

}
