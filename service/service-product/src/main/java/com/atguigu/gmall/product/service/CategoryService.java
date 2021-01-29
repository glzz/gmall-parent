package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.product.BaseCategoryView;

import java.util.List;

public interface CategoryService {

    /**
     * 查询所有的一级分类信息
     *
     * @return
     */
    List<BaseCategory1> getCategory1();

    /**
     * 根据一级分类Id 查询二级分类数据
     *
     * @param category1Id
     * @return
     */
    List<BaseCategory2> getCategory2(Long category1Id);

    /**
     * 根据二级分类Id 查询三级分类数据
     *
     * @param category2Id
     * @return
     */
    List<BaseCategory3> getCategory3(Long category2Id);

    BaseCategoryView getCategoryView(Long category3Id);
}