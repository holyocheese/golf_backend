package com.golf.base.biz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golf.common.BaseBiz;
import com.golf.common.exception.BaseException;
import com.golf.dao.entity.EwheelCar;
import com.golf.dao.mapper.EwheelCarMapper;
import com.golf.model.vo.EwheelCarPassVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class EwheelCarBiz extends BaseBiz<EwheelCarMapper,EwheelCar>{

	public String initPass(EwheelCarPassVo entity){
		EwheelCar ewheelCar = super.selectById(entity.getCarId());
		if(ewheelCar==null){
			throw new BaseException("小车不存在",400);
		}
		if(entity.getExtAnswer1().equals(ewheelCar.getExtAnswer1())){
			return ewheelCar.getInitialPassword();
		}else{
			throw new BaseException("问题答案不正确",400);
			
		}
	}
	
}
