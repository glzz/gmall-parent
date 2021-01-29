package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("admin/product/")
public class AttrController {
    @Autowired
    AttrService attrService;

    /**
     * 4.获取分类id获取平台属性
     *
     * @param category3Id
     * @return
     */
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")

    public Result attrInfoList(@PathVariable("category1Id") Long category1Id,
                               @PathVariable("category2Id") Long category2Id,
                               @PathVariable("category3Id") Long category3Id) {
     List<BaseAttrInfo>    baseAttrInfos = attrService.attrInfoList(category3Id);
        return Result.ok(baseAttrInfos);
    }

    /**
     * 5.修改平台属性
     *
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
// 前台数据都被封装到该对象中baseAttrInfo
        attrService.saveAttrInfo(baseAttrInfo);
        return Result.ok();
    }

    /**
     *  6.根据平台属性ID获取平台属性
     *
     * @param attrId
     * @return
     */
    @GetMapping("getAttrValueList/{attrId}")
    public Result  getAttrValueList(@PathVariable("attrId") Long attrId) {
       // BaseAttrInfo baseAttrInfo = attService.getAttrInfo(attrId);
        List<BaseAttrValue> baseAttrValues = attrService.getAttrValueList(attrId);
        return Result.ok(baseAttrValues);
    }
}
