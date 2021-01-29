package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.SkuAttrValueMapper;
import com.atguigu.gmall.product.mapper.SkuImageMapper;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import com.atguigu.gmall.product.mapper.SkuSaleAttrValueMapper;
import com.atguigu.gmall.product.service.SkuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    SkuInfoMapper skuInfoMapper;

    @Autowired
    SkuImageMapper skuImageMapper;

    @Autowired
    SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public IPage<SkuInfo> SkuList(Long page, Long limit) {
        Page<SkuInfo> infoPage = new Page<>();
        infoPage.setSize(limit);
        infoPage.setCurrent(page);
        IPage<SkuInfo> iPage = skuInfoMapper.selectPage(infoPage, null);
        return iPage;
    }

    @Override
    public void SaveSkuInfo(SkuInfo skuInfo) {
        //1.添加spu，生成主键id
        skuInfoMapper.insert(skuInfo);
        Long skuId = skuInfo.getId();
        //2.添加sku图片
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        for (SkuImage skuImage : skuImageList) {
            skuImage.setSkuId(skuId);
            skuImageMapper.insert(skuImage);

        }
        //3.添加sku平台属性id
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue skuAttrValue : skuAttrValueList) {
            skuAttrValue.setSkuId(skuId);
            skuAttrValueMapper.insert(skuAttrValue);
        }
        //4.保存销售属性
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
            skuSaleAttrValue.setId(skuId);
            skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
            skuSaleAttrValueMapper.insert(skuSaleAttrValue);
        }
    }

    @Override
    public void OnSale(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(1);
        skuInfoMapper.updateById(skuInfo);

        //同步搜索引擎

    }

    @Override
    public void CancelSale(Long skuId) {
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);
        skuInfo.setIsSale(0);
        skuInfoMapper.updateById(skuInfo);

        //同步搜索引擎

    }

    @Override
    public SkuInfo getSkuInfoById(Long skuId) {
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        QueryWrapper<SkuImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id", skuId);
        List<SkuImage> skuImages = skuImageMapper.selectList(queryWrapper);
        skuInfo.setSkuImageList(skuImages);
        return skuInfo;
    }

    @Override
    public BigDecimal getSkuPriceById(Long skuId) {
        QueryWrapper<SkuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", skuId);
        SkuInfo skuInfo = skuInfoMapper.selectOne(queryWrapper);
        return skuInfo.getPrice();
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long spuId, Long skuId) {
        List<SpuSaleAttr> spuSaleAttrs = skuSaleAttrValueMapper.selectSpuSaleAttrListCheckBySku(spuId, skuId);
        return spuSaleAttrs;
    }

    @Override
    public List<Map<String, Object>> getSaleAttrValuesBySpu(Long spuId) {
        List<Map<String, Object>> listmap = skuSaleAttrValueMapper.selectSaleAttrValuesBySpu(spuId);

        return listmap;
    }
}