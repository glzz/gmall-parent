package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.service.SkuService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/product/")
@CrossOrigin
public class SkuController {

    @Autowired
    SkuService skuService;

    /**
     * 上架
     * @return
     */
    @GetMapping("onSale/{skuId}")
    public Result OnSale(@PathVariable("skuId")Long skuId ) {
        skuService.OnSale(skuId);
        return Result.ok();
    }


    /**
     * 下架
     * @return
     */
    @GetMapping("cancelSale/{skuId}")
    public Result CancelSale(@PathVariable("skuId")Long skuId ) {
        skuService.CancelSale(skuId);
        return Result.ok();
    }
    /**
     * 添加sku
     * @param skuInfo
     * @return
     */
    @PostMapping("saveSkuInfo")
    public Result SaveSkuInfo(@RequestBody SkuInfo skuInfo) {
        skuService.SaveSkuInfo(skuInfo);
        return Result.ok();
    }

    /**
     * 获取spu分页列表
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("list/{page}/{limit}")
    public Result SkuList(@PathVariable("page") Long page, @PathVariable("limit") Long limit) {
        IPage<SkuInfo> iPage = skuService.SkuList(page, limit);
        return Result.ok(iPage);
    }

}
