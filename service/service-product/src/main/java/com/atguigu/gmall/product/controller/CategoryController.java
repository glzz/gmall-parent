package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品基础属性接口")
@RestController
@RequestMapping("admin/product/")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 1.查询所有的以及分类信息
     *
     * @return
     */


   @GetMapping("getCategory1")
    public Result<List<BaseCategory1>> getCategory1() {
        List<BaseCategory1> category1List = categoryService.getCategory1();
        return Result.ok(category1List);
    }

    /**
     *2. 根据一级分类Id 查询二级分类数据
     *
     * @param category1Id
     * @return
     */
    @GetMapping("getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable("category1Id") Long category1Id) {
        List<BaseCategory2> category2List = categoryService.getCategory2(category1Id);
        return Result.ok(category2List);
    }


    /**
     * 3.根据二级分类Id 查询三级分类数据
     *
     * @param category2Id
     * @return
     */

    @GetMapping("getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable("category2Id") Long category2Id) {
        List<BaseCategory3> category3List = categoryService.getCategory3(category2Id);
        return Result.ok(category3List);
    }
}
