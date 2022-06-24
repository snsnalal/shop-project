package com.shop.projectlion.api.controller;

import com.google.gson.Gson;
import com.shop.projectlion.api.item.controller.AdminItemRestController;
import com.shop.projectlion.domain.delivery.service.DeliveryService;
import com.shop.projectlion.domain.item.constant.ItemSellStatus;
import com.shop.projectlion.api.item.dto.UpdateItemRequestDto;
import com.shop.projectlion.api.item.service.UpdateItemDtoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
class AdminItemRestControllerTest {

    @InjectMocks
    private AdminItemRestController adminItemRestController;

    @Mock
    private UpdateItemDtoService updateItemDtoService;

    @Mock
    private DeliveryService deliveryService;


    private MockMvc mockMvc;



    @BeforeEach
    public void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(adminItemRestController).build();
    }

    @Test
    void ItemEditTest() throws Exception {
        UpdateItemRequestDto updateItemRequestDto = updateRequest();
        UpdateItemRequestDto response = updateRequest();

        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.patch("/admin/item/4")
                        .contentType("application/json")
                        .content(new Gson().toJson(updateItemRequestDto))
        );
    }

    private UpdateItemRequestDto updateRequest(){
        return new UpdateItemRequestDto().of(4L, "테스트 이름", 5000, "테스트 내용", 777, ItemSellStatus.SOLD_OUT, 100000L );
    }

}