package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.TrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/product/")
@CrossOrigin
public class TrademarkController {

    @Autowired
    TrademarkService trademarkService;

    /**
     * 获取品牌属性
     * @return
     */
    @GetMapping("baseTrademark/getTrademarkList")
    public Result baseTrademark(){

        List<BaseTrademark> baseTrademarks =  trademarkService.getTrademarkList();
        return Result.ok(baseTrademarks);
    }
    /**
     * 获取品牌分页列表
     *
     * @return
     */
    @GetMapping("baseTrademark/{page}/{limit}")
    public Result baseTrademark(@PathVariable("page") Long page,@PathVariable("limit") Long limit){

        IPage<BaseTrademark> iPage =  trademarkService.baseTrademark(page,limit);
        return Result.ok(iPage);
    }



}
