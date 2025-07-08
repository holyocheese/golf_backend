package com.golf.base.biz;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.golf.common.BaseBiz;
import com.golf.dao.entity.Firmware2Setting;
import com.golf.dao.mapper.Firmware2SettingMapper;
import com.golf.service.FileStorageService4Firmware;

import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(rollbackFor = Exception.class)
public class Firmware2SettingBiz extends BaseBiz<Firmware2SettingMapper,Firmware2Setting>{

	@Autowired
    private FileStorageService4Firmware fileStorageService;
    
	public Firmware2Setting uploadFirmware(MultipartFile file,Integer id){
		Firmware2Setting as = mapper.selectByPrimaryKey(id);
		String fileName = fileStorageService.storeFile(file, as.getName()+"_"+as.getVersion()+"_"+as.getId()+".bin");
		as.setFileName(fileName);
		mapper.updateByPrimaryKeySelective(as);
		return as;
	}
	
	public Firmware2Setting getLastest(){
		Example example = new Example(Firmware2Setting.class);
		example.setOrderByClause("id desc");
		List<Firmware2Setting> as = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(as)?null:as.get(0);
	}
	
	/**
	 * 根据车型版本获取最新固件
	 * @param carVersionId 车型版本ID
	 * @return Firmware2Setting
	 */
	public Firmware2Setting getLastestByCarVersion(Integer carVersionId){
		Example example = new Example(Firmware2Setting.class);
		example.createCriteria().andEqualTo("carVersionId", carVersionId);
		example.setOrderByClause("id desc");
		List<Firmware2Setting> as = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(as)?null:as.get(0);
	}
}
