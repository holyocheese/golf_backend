package com.golf.base.biz;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golf.common.BaseBiz;
import com.golf.dao.entity.CarVersion;
import com.golf.dao.mapper.CarVersionMapper;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(rollbackFor = Exception.class)
public class CarVersionBiz extends BaseBiz<CarVersionMapper, CarVersion> {

    /**
     * 根据名称查找车型
     * @param name 车型名称
     * @return CarVersion
     */
    public CarVersion getByName(String name) {
        Example example = new Example(CarVersion.class);
        example.createCriteria().andEqualTo("name", name);
        List<CarVersion> list = mapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    /**
     * 检查车型名称是否已存在
     * @param name 车型名称
     * @param excludeId 排除的ID（用于更新时排除自己）
     * @return boolean
     */
    public boolean isNameExists(String name, Integer excludeId) {
        Example example = new Example(CarVersion.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("name", name);
        if (excludeId != null) {
            criteria.andNotEqualTo("id", excludeId);
        }
        int count = mapper.selectCountByExample(example);
        return count > 0;
    }

    /**
     * 获取所有车型列表（按名称排序）
     * @return List<CarVersion>
     */
    public List<CarVersion> getAllOrderByName() {
        Example example = new Example(CarVersion.class);
        example.setOrderByClause("name ASC");
        return mapper.selectByExample(example);
    }
}
