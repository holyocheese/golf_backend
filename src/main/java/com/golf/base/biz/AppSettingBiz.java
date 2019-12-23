package com.golf.base.biz;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.golf.common.BaseBiz;
import com.golf.dao.entity.AppSetting;
import com.golf.dao.mapper.AppSettingMapper;
import com.golf.service.FileStorageService;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(rollbackFor = Exception.class)
public class AppSettingBiz extends BaseBiz<AppSettingMapper,AppSetting>{

	@Autowired
    private FileStorageService fileStorageService;
    
	public AppSetting uploadApp(MultipartFile file,Integer id){
		AppSetting as = mapper.selectByPrimaryKey(id);
		String fileName = fileStorageService.storeFile(file, as.getName()+"_"+as.getType()+"_"+as.getVersion()+"_"+as.getId()+".apk");
		as.setFileName(fileName);
		mapper.updateByPrimaryKeySelective(as);
		return as;
	}
	
	public AppSetting getLastest(Integer type){
		Example example = new Example(AppSetting.class);
		Example.Criteria criteria = example.createCriteria();
		example.orderBy("id desc");
		criteria.andEqualTo("type", type);
		List<AppSetting> as = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(as)?null:as.get(0);
	}
}
