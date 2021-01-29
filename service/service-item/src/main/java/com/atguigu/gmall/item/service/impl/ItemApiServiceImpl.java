package com.atguigu.gmall.item.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.item.service.ItemApiService;
import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemApiServiceImpl implements ItemApiService {

    @Autowired
    ProductFeignClient productFeignClient;

    @Override
    public Map<String, Object> getItem(Long skuId) {

        Map<String, Object> result = new HashMap<>();
        //skuInfo //image
        SkuInfo skuInfo = productFeignClient.getSkuInfoById(skuId);
        result.put("skuInfo", skuInfo);
        //分类信息
        BaseCategoryView baseCategoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());

        result.put("categoryView", baseCategoryView);

        //销售属性列表
        List<SpuSaleAttr> spuSaleAttrs = productFeignClient.getSpuSaleAttrListCheckBySku(skuInfo.getSpuId(), skuId);

        result.put("spuSaleAttrList", spuSaleAttrs);

        //价格
        BigDecimal price = productFeignClient.getSkuPriceById(skuId);
        result.put("price", price);

        //销售属性组合hash表

        List<Map<String, Object>> valuesSkuMaps = productFeignClient.getSaleAttrValuesBySpu(skuInfo.getSpuId());
        // 将mybatis的map集合转化为一个map
        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> valuesSkuMap : valuesSkuMaps) {
            map.put((String) valuesSkuMap.get("valueIds"),valuesSkuMap.get("sku_id"));
        }
        result.put("valuesSkuJson", JSON.toJSONString(map));

        return result;
    }
}
