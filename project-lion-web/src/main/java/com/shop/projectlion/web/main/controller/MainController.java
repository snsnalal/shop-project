package com.shop.projectlion.web.main.controller;

import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.item.service.ItemService;
import com.shop.projectlion.web.main.dto.ItemSearchDto;
import com.shop.projectlion.web.main.dto.MainItemDto;
import com.shop.projectlion.web.main.service.MainItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final MainItemService mainItemService;

    @GetMapping("/")
    public String main(ItemSearchDto itemSearchDto, Optional<Integer> page, Model model) {

        int start = page.isPresent() ? page.get()*6 : 0;
        int totalSize = mainItemService.getTotalSize(itemSearchDto.getSearchQuery());

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6); // 페이지 및 한 페이지에 나올 페이지 갯수로 pageable 객체 생성

        List<MainItemDto> mainItemDtos = mainItemService.getMainItemDto(itemSearchDto.getSearchQuery(), start);


        Page<MainItemDto> pageMainItemDto = new PageImpl<>(mainItemDtos, pageable, totalSize);

        model.addAttribute("items", pageMainItemDto);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5); // 메인페이지에 노출되는 최대 페이지 갯수

        return "main/mainpage";
    }

}