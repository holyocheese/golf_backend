package com.golf.base.biz;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.golf.common.BaseBiz;
import com.golf.dao.entity.GroupType;
import com.golf.dao.mapper.GroupTypeMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class GroupTypeBiz extends BaseBiz<GroupTypeMapper,GroupType> {
}
