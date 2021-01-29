package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.SpuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    SpuInfoMapper spuInfoMapper;

    @Autowired
    BaseSaleMapper baseSaleMapper;

    @Autowired
    SpuImageMapper spuImageMapper;

    @Autowired
    SpuPosterMapper spuPosterMapper;

    @Autowired
    SpuSaleAttrMapper spuSaleAttrMapper;

    @Autowired
    SpuSaleAttrValueMapper spuSaleAttrValueMapper;




    /**
     *
     * @param page
     * @param limit
     * @param category3Id
     * @return
     */
    @Override
    public IPage<SpuInfo> SpuList(Long page, Long limit, Long category3Id) {
        Page<SpuInfo> infoPage = new Page<>();
        infoPage.setSize(limit);
        infoPage.setCurrent(page);
        QueryWrapper<SpuInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category3_id", category3Id);
        IPage<SpuInfo> iPage = spuInfoMapper.selectPage(infoPage, queryWrapper);
        return iPage;
    }

    @Override
    public List<BaseSaleAttr> baseSaleAttrList() {


        return baseSaleMapper.selectList(null);
    }

    /**
     *
     * @param spuInfo
     */
    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //1.添加spu，生成主键id
        spuInfoMapper.insert(spuInfo);
        Long spu_id = spuInfo.getId();
        //2.添加图片集合
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (null != spuImageList && spuImageList.size() > 0) {
            for (SpuImage spuImage : spuImageList) {
                spuImage.setSpuId(spu_id);
                spuImageMapper.insert(spuImage);
            }
        }
        //3.添加销售属性集合
        final List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (null != spuSaleAttrList && spuSaleAttrList.size() > 0) {
            for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                spuSaleAttr.setSpuId(spu_id);
                spuSaleAttrMapper.insert(spuSaleAttr);

                //4.添加销售属性值集合

                List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                if (null != spuSaleAttrValueList && spuSaleAttrValueList.size() > 0) {
                    for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                        spuSaleAttrValue.setSpuId(spu_id);
                        spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
                        spuSaleAttrValueMapper.insert(spuSaleAttrValue);
                    }
                }
            }
        }


    }

    @Override
    public List<SpuImage> SpuImageList(Long spuId) {
        QueryWrapper<SpuImage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spu_id",spuId);
        List<SpuImage> spuImages = spuImageMapper.selectList(queryWrapper);
        return spuImages;
    }

    @Override
    public List<SpuSaleAttr> SpuSaleAttrList(Long spuId) {
        QueryWrapper<SpuSaleAttr> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("spu_id", spuId);
        final List<SpuSaleAttr> spuSaleAttrs = spuSaleAttrMapper.selectList(queryWrapper);
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrs) {
            QueryWrapper<SpuSaleAttrValue> queryWrapperValue = new QueryWrapper<>();
            queryWrapperValue.eq("spu_id", spuId);

            queryWrapperValue.eq("base_sale_attr_id",spuSaleAttr.getBaseSaleAttrId());
            spuSaleAttrValueMapper.selectList(queryWrapperValue);
            spuSaleAttr.setSpuSaleAttrValueList(null);

        }

        return null;
    }


}
