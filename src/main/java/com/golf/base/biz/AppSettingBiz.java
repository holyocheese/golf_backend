package com.golf.base.biz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golf.common.BaseBiz;
import com.golf.dao.entity.AppSetting;
import com.golf.dao.mapper.AppSettingMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppSettingBiz extends BaseBiz<AppSettingMapper,AppSetting>{

}
