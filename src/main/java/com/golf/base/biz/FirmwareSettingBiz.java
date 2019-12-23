package com.golf.base.biz;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.golf.common.BaseBiz;
import com.golf.dao.entity.FirmwareSetting;
import com.golf.dao.mapper.FirmwareSettingMapper;
import com.golf.service.FileStorageService4Firmware;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(rollbackFor = Exception.class)
public class FirmwareSettingBiz extends BaseBiz<FirmwareSettingMapper,FirmwareSetting>{

	@Autowired
    private FileStorageService4Firmware fileStorageService;
    
	public FirmwareSetting uploadFirmware(MultipartFile file,Integer id){
		FirmwareSetting as = mapper.selectByPrimaryKey(id);
		String fileName = fileStorageService.storeFile(file, as.getName()+"_"+as.getVersion()+"_"+as.getId()+".bin");
		as.setFileName(fileName);
		mapper.updateByPrimaryKeySelective(as);
		return as;
	}
	
	public FirmwareSetting getLastest(){
		Example example = new Example(FirmwareSetting.class);
		example.orderBy("id desc");
		List<FirmwareSetting> as = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(as)?null:as.get(0);
	}
}
