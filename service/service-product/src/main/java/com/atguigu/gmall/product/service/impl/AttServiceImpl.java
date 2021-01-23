package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.service.AttService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AttServiceImpl implements AttService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;


    @Override
    public List<BaseAttrInfo> attrInfoList(Long category3Id) {
        QueryWrapper<BaseAttrInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_level", 3);
        queryWrapper.eq("category_id", category3Id);
        List<BaseAttrInfo> baseAttrInfos = baseAttrInfoMapper.selectList(queryWrapper);

        if (null != baseAttrInfos && baseAttrInfos.size() > 0) {
            for (BaseAttrInfo baseAttrInfo : baseAttrInfos) {
                QueryWrapper<BaseAttrValue> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("attr_id", baseAttrInfo.getId());
                List<BaseAttrValue> baseAttrValues = baseAttrValueMapper.selectList(queryWrapper1);
                baseAttrInfo.setAttrValueList(baseAttrValues);
            }
        }
        return baseAttrInfos;

    }

    /**
     * 保存平台属性方法
     *
     * @param baseAttrInfo
     */

    @Override
    @Transactional
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

        // 什么情况下 是添加，什么情况下是更新，修改 根据baseAttrInfo 的Id
        // baseAttrInfo
        if (baseAttrInfo.getId() != null) {
// 修改数据
            baseAttrInfoMapper.updateById(baseAttrInfo);
        } else {
// 新增
// baseAttrInfo 插入数据
            baseAttrInfoMapper.insert(baseAttrInfo);
        }

// baseAttrValue 平台属性值
// 修改：通过先删除{baseAttrValue}，在新增的方式！
// 删除条件：baseAttrValue.attrId = baseAttrInfo.id
        QueryWrapper queryWrapper = new QueryWrapper<BaseAttrValue>();
        queryWrapper.eq("attr_id", baseAttrInfo.getId());
        baseAttrValueMapper.delete(queryWrapper);

// 获取页面传递过来的所有平台属性值数据
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList != null && attrValueList.size() > 0) {
// 循环遍历
            for (BaseAttrValue baseAttrValue : attrValueList) {
// 获取平台属性Id 给attrId
                baseAttrValue.setAttrId(baseAttrInfo.getId()); // ?
                baseAttrValueMapper.insert(baseAttrValue);
            }
        }


    }

    @Override
    public List<BaseAttrValue> getAttrValueList(Long attrId) {
        QueryWrapper<BaseAttrValue>queryWrapper =  new QueryWrapper<>();
        queryWrapper.eq("attr_id", attrId);
        List<BaseAttrValue> baseAttrValues = baseAttrValueMapper.selectList(queryWrapper);
        return baseAttrValues;
    }


}
