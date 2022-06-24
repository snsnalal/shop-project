package com.shop.projectlion.web.adminitem.controller;

import com.shop.projectlion.domain.delivery.entity.Delivery;
import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.member.service.MemberService;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.infra.FileService;
import com.shop.projectlion.infra.UploadFile;
import com.shop.projectlion.web.adminitem.dto.InsertItemDto;
import com.shop.projectlion.web.adminitem.dto.UpdateItemDto;
import com.shop.projectlion.domain.delivery.service.DeliveryService;
import com.shop.projectlion.domain.itemimage.service.ItemImageService;
import com.shop.projectlion.domain.item.service.ItemService;
import com.shop.projectlion.web.adminitem.service.UpdateItemDtoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/items")
@Slf4j
public class AdminItemController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final DeliveryService deliveryService;
    private final ItemImageService itemImageService;
    private final UpdateItemDtoService updateItemDtoService;
    private final FileService fileService;

    @GetMapping("/new")
    public String itemForm(Model model) {

        List<Delivery> delivery = deliveryService.findDeliveryAll();

        model.addAttribute("delivery", delivery);
        model.addAttribute("insertItemDto", new InsertItemDto());

        return "adminitem/registeritemform";
    }

    @PostMapping("/new")
    public String registerItem(@Valid @ModelAttribute("insertItemDto")InsertItemDto insertItemDto, BindingResult result, Authentication authentication) {

        if (result.hasErrors()){
            if(insertItemDto.getItemImageFiles().get(0).getOriginalFilename().isEmpty()){
                log.info("size={}", insertItemDto.getItemImageFiles().size());
                result.reject("FileError", "첫 번째 파일은 필수 입니다.");
            }
            return "adminitem/registeritemform";
        }
        Item item;
        try {
            Optional<Member> findMember = memberService.findMemberByEmail(authentication.getName());
            Optional<Delivery> findDelivery = deliveryService.findDeliveryByid(insertItemDto.getDeliveryId());
            List<UploadFile> uploadFiles = fileService.storeFiles(insertItemDto.getItemImageFiles());

            item = Item.createItem(insertItemDto.toEntity(), findMember.get(), findDelivery.get());

            itemService.registerItem(item);

            itemImageService.saveItemImage(uploadFiles, item);

        } catch (IllegalStateException | IOException e){
            e.printStackTrace();
            result.reject("InsertError", e.getMessage());
            return "adminitem/registeritemform";
        }

        return "redirect:"+item.getItemId();
    }


    @GetMapping("/{itemId}")
    public String itemEdit(@PathVariable Long itemId, Model model) {

        // 배송 정보
        List<Delivery> delivery = deliveryService.findDeliveryAll();

        // 상품 이미지

        List<UpdateItemDto.ItemImageDto> itemImageDto = updateItemDtoService.mappingToItemImageDto(itemId);


        Item item = itemService.findById(itemId).get();
        // 상품 정보
        UpdateItemDto updateItemDto = new UpdateItemDto(item, itemImageDto);

        model.addAttribute("delivery", delivery);
        model.addAttribute("updateItemDto", updateItemDto);

        return "adminitem/updateitemform";
    }


    @PostMapping("/{itemId}")
    public String editItemInformation(@PathVariable Long itemId, @Valid @ModelAttribute("updateItemDto") UpdateItemDto updateItemDto, BindingResult result, Model model) throws IOException {

        List<UpdateItemDto.ItemImageDto> itemImageDto = updateItemDtoService.mappingToItemImageDto(itemId);
        updateItemDto.setItemImageDtos(itemImageDto);

        if(result.hasErrors()){

            return "redirect:"+itemId.toString();

        }else if (updateItemDto.getOriginalImageNames().get(0).isEmpty() && //첫 번쨰 파일이 비어있으면
                updateItemDto.getItemImageFiles().get(0).getOriginalFilename().isEmpty()){

            result.reject("FileError", "첫 번째 파일은 필수 입니다.");
            model.addAttribute("updateItemDto", updateItemDto);

            return "adminitem/updateitemform";
        }

        Delivery delivery = deliveryService.findDeliveryByid(updateItemDto.getDeliveryId()).get();
        Item item = updateItemDto.toEntity(delivery);

        itemService.modifyItem(item);

        itemImageService.modifyItemImage(updateItemDto, itemId);


        return "redirect:"+itemId.toString();
    }

}