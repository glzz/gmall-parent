package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.model.product.BaseCategoryView;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.CategoryService;
import com.atguigu.gmall.product.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/product/")
@CrossOrigin
public class ProductApiController {

    @Autowired
    SkuService skuService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("getSkuInfoById/{skuId}")
    SkuInfo getSkuInfoById(@PathVariable("skuId") Long skuId) {
        SkuInfo skuInfo = skuService.getSkuInfoById(skuId);
        return skuInfo;
    }


    @RequestMapping("getCategoryView/{category3Id}")
    BaseCategoryView getCategoryView(@PathVariable("category3Id") Long category3Id) {
        BaseCategoryView baseCategoryView = categoryService.getCategoryView(category3Id);
        return baseCategoryView;
    }

    @RequestMapping("getSkuPriceById/{skuId}")
    BigDecimal getSkuPriceById(@PathVariable("skuId") Long skuId) {
        BigDecimal price = skuService.getSkuPriceById(skuId);
        return price;
    }

    @RequestMapping("getSpuSaleAttrListCheckBySku/{spuId}/{skuId}")
    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable("spuId") Long spuId, @PathVariable("skuId") Long skuId) {
        List<SpuSaleAttr> spuSaleAttrs = skuService.getSpuSaleAttrListCheckBySku(spuId, skuId);
        return spuSaleAttrs;
    }

    @RequestMapping("getSaleAttrValuesBySpu/{spuId}")
    List<Map<String, Object>> getSaleAttrValuesBySpu(@PathVariable("spuId") Long spuId) {
        List<Map<String, Object>> maps = skuService.getSaleAttrValuesBySpu(spuId);
        return maps;
    }

}
