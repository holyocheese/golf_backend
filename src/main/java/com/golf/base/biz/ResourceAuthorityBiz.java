package com.golf.base.biz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golf.common.BaseBiz;
import com.golf.dao.entity.ResourceAuthority;
import com.golf.dao.mapper.ResourceAuthorityMapper;
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceAuthorityBiz extends BaseBiz<ResourceAuthorityMapper,ResourceAuthority> {
}
