package com.shop.projectlion.web.orderhist.controller;

import com.shop.projectlion.domain.item.entity.Item;
import com.shop.projectlion.domain.itemimage.entity.ItemImage;
import com.shop.projectlion.domain.member.entity.Member;
import com.shop.projectlion.domain.order.entity.Orders;
import com.shop.projectlion.domain.orderitem.entity.OrderItem;
import com.shop.projectlion.web.orderhist.dto.OrderHistDto;
import com.shop.projectlion.web.orderhist.service.OrderHistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orderhist")
@Slf4j
public class OrderHistController {

    private final OrderHistService orderHistService;

    @GetMapping
    public String orderHist(Optional<Integer> page, Model model, Principal principal) {

        Member findMember = orderHistService.findMemberByEmail(principal.getName());

        int totalSize = orderHistService.getOrderItemSize(findMember.getMemberId());

        Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);

        List<OrderItem> orderItemList = orderHistService.findOrderItemsForPage(findMember.getMemberId() ,pageable);

        for (OrderItem orderItem : orderItemList){
            log.info("orderItemId={}, count={}, price={}", orderItem.getOrderItemId(), orderItem.getCount(), orderItem.getOrderPrice());
        }

        // 주문 정보
        List<OrderHistDto> orderHistDtos = new ArrayList<>();

        int i = 0;
        for (OrderItem orderItem : orderItemList){
            orderHistDtos.add(OrderHistDto.builder()
                            .orderId(orderItem.getOrders().getOrderId())
                            .orderTime(orderItem.getCreateTime())
                            .orderStatus(orderItem.getOrders().getOrderStatus())
                            .totalPrice(orderItem.getOrderPrice())
                            .totalDeliveryFee(orderItem.getItem().getDelivery().getDeliveryFee())
                            .build());

            Item findItem = orderHistService.findItemByOrderItemId(orderItem.getItem().getItemId());
            ItemImage findItemImage = orderHistService.findItemImageByItem(findItem);
            List<OrderHistDto.OrderItemHistDto> orderItemHistDtos = new ArrayList<>();
            orderItemHistDtos.add(
                    OrderHistDto.OrderItemHistDto.builder()
                            .imageUrl(findItemImage.getImageName())
                            .orderPrice(findItem.getPrice())
                            .count(orderItem.getCount())
                            .itemName(findItem.getItemName())
                            .build());

            orderHistDtos.get(i).setOrderItemHistDtos(orderItemHistDtos);
            i++;
        }


        Page<OrderHistDto> pageOrderHistDtos = new PageImpl<>(orderHistDtos, pageable, totalSize);
        model.addAttribute("orders", pageOrderHistDtos);
        model.addAttribute("page", pageable.getPageNumber());
        model.addAttribute("maxPage", 5); // 메인페이지에 노출되는 최대 페이지 갯수

        return "orderhist/orderhist";
    }

    @ResponseBody
    @RequestMapping(value = "{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@RequestBody @PathVariable Long orderId){

        log.info("id={}", orderId);

        OrderItem findOrderItem = orderHistService.findOrderItemById(orderId);

        orderHistService.cancelItem(findOrderItem);
        orderHistService.cancelOrder(findOrderItem);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
