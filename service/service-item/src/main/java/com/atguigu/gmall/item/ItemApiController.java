package com.atguigu.gmall.item;

import com.atguigu.gmall.item.service.ItemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/item")
public class ItemApiController {

    @Autowired
    ItemApiService itemApiService;

    /**
     * 获取sku详情信息
     * @param skuId
     * @return
     */
    @GetMapping("{skuId}")
    public Map<String,Object> getItem(@PathVariable Long skuId){

        Map<String,Object> map = new HashMap<>();

        map = itemApiService.getItem(skuId);

     return  map;
    }
}



