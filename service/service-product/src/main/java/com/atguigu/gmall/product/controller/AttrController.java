package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.AttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("admin/product/")
public class AttrController {
    @Autowired
    AttService attService;

    /**
     * 4.根据分类Id 获取平台属性数据
     *
     * @param category3Id
     * @return
     */
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")

    public Result attrInfoList(@PathVariable("category1Id") Long category1Id,
                               @PathVariable("category2Id") Long category2Id,
                               @PathVariable("category3Id") Long category3Id) {
     List<BaseAttrInfo>    baseAttrInfos = attService.attrInfoList(category3Id);
        return Result.ok(baseAttrInfos);
    }

    /**
     * 5.保存平台属性方法
     *
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
// 前台数据都被封装到该对象中baseAttrInfo
        attService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    /**
     *  6.修改平台属性
     *
     * @param attrId
     * @return
     */
    @GetMapping("getAttrValueList/{attrId}")
    public Result  getAttrValueList(@PathVariable("attrId") Long attrId) {
       // BaseAttrInfo baseAttrInfo = attService.getAttrInfo(attrId);
        List<BaseAttrValue> baseAttrValues = attService.getAttrValueList(attrId);
        return Result.ok(baseAttrValues);
    }
}
