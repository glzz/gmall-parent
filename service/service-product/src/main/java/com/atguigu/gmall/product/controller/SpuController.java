package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.SpuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/product/")
@CrossOrigin
public class SpuController {

    @Autowired
    SpuService spuService;


    /**
     * 根据spuId获取图片列表
     *
     * @param spuId
     * @return
     */
    @GetMapping("spuImageList/{spuId}")
    public Result SpuImageList(@PathVariable("spuId") Long spuId) {

        List<SpuImage> spuImages = spuService.SpuImageList(spuId);
        return Result.ok(spuImages);
    }

    /**
     * 根据spuId获取销售属性
     * @param spuId
     * @return
     */
    @GetMapping("spuSaleAttrList/{spuId}")
    public Result SpuSaleAttrList(@PathVariable("spuId") Long spuId) {
        List<SpuSaleAttr> spuSaleAttrs = spuService.SpuSaleAttrList(spuId);
        return Result.ok(spuSaleAttrs);
    }

    /**
     * 添加spu
     */
    @PostMapping("saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo) {
        spuService.saveSpuInfo(spuInfo);

        return Result.ok();
    }

    /**
     * 获取销售属性
     *
     * @return
     */
    @GetMapping("baseSaleAttrList")
    public Result baseSaleAttrList() {
        List<BaseSaleAttr> baseSaleAttrs = spuService.baseSaleAttrList();

        return Result.ok(baseSaleAttrs);
    }

    /**
     * 获取spu分页列表
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("{page}/{limit}")
    public Result SpuList(@PathVariable("page") Long page, @PathVariable("limit") Long limit, Long category3Id) {
        IPage<SpuInfo> iPage = spuService.SpuList(page, limit, category3Id);
        return Result.ok(iPage);
    }
}
