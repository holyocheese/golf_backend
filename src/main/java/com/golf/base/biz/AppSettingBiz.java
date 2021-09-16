package com.golf.base.biz;

import java.util.List;

import com.golf.constant.AppTypeConstant;
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
		if(as.getType()== AppTypeConstant.ANDROID){//如果是安卓最新版上传 复制一份到Ewheel_lastest.apk
			AppSetting lastestAndroid = getLastest(AppTypeConstant.ANDROID);
			if(lastestAndroid.getId()==id){
				//保存一份为最新apk
				fileStorageService.storeFile(file, "Ewheel_lastest.apk");
			}
			String fileName = fileStorageService.storeFile(file, as.getName()+"_"+as.getType()+"_"+as.getVersion()+"_"+as.getId()+".apk");
			as.setFileName(fileName);
		}
		if(as.getType()== AppTypeConstant.WINDOW){//如果是windows最新版上传 复制一份到Ewheel_lastest.exe
			AppSetting lastestWindows = getLastest(AppTypeConstant.WINDOW);
			if(lastestWindows.getId()==id){
				//保存一份最新
				fileStorageService.storeFile(file, "Ewheel_lastestversion.exe");
			}
			String fileName = fileStorageService.storeFile(file, as.getName()+"_"+as.getType()+"_"+as.getVersion()+"_"+as.getId()+".exe");
			as.setFileName(fileName);
		}
		mapper.updateByPrimaryKeySelective(as);
		return as;
	}
	
	public AppSetting getLastest(Integer type){
		Example example = new Example(AppSetting.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("type", type);
		example.setOrderByClause("id desc");
		List<AppSetting> as = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(as)?null:as.get(0);
	}
}
