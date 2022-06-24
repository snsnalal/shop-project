package com.shop.projectlion.web.itemdtl.controller;

import com.shop.projectlion.domain.item.constant.ItemSellStatus;
import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.order.entity.Orders;
import com.shop.projectlion.domain.orderitem.entity.OrderItem;
import com.shop.projectlion.global.error.exception.BusinessException;
import com.shop.projectlion.global.error.exception.ErrorCode;
import com.shop.projectlion.web.itemdtl.dto.ItemDtlDto;
import com.shop.projectlion.web.itemdtl.service.ItemDtlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/itemdtl")
@Slf4j
public class ItemDtlController {

    private final ItemDtlService itemDtlService;

    @GetMapping(value = "/{itemId}")
    public String itemDtl(Model model, @PathVariable("itemId") Long itemId){

        ItemDtlDto itemDtlDto = itemDtlService.mappingToItemDtlDtoByItemId(itemId);

        model.addAttribute("item", itemDtlDto);
        return "itemdtl/itemdtl";
    }

    @ResponseBody
    @RequestMapping("/order")
    public ResponseEntity<String> orderItem(@RequestBody Map<String, Object> paramMap, Authentication authentication){

        Item item = itemDtlService.findByItemId(Long.parseLong(paramMap.get("itemId").toString()));
        Member findMember = itemDtlService.findMemberByEmail(authentication.getName());

        Integer count = Integer.valueOf(paramMap.get("count").toString());

        if (!item.checkStock(count)){
            return new ResponseEntity<>(ErrorCode.STOCK_NOT_ENOUGH_ERROR.getMessage(), HttpStatus.BAD_REQUEST);
        }

        Orders orders = Orders.createOrder(findMember);
        OrderItem orderItem = OrderItem.orderItem(count, item.getPrice() * count + item.getDelivery().getDeliveryFee(), item, orders);
        orders.addOrderItem(orderItem);
        itemDtlService.saveOrder(orders);
        itemDtlService.saveOrderItem(orderItem);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
