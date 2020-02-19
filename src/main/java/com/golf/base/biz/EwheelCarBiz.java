package com.golf.base.biz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.golf.common.BaseBiz;
import com.golf.common.exception.BaseException;
import com.golf.dao.entity.EwheelCar;
import com.golf.dao.mapper.EwheelCarMapper;
import com.golf.model.vo.EwheelCarPassVo;
import com.golf.model.vo.EwheelCarVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class EwheelCarBiz extends BaseBiz<EwheelCarMapper,EwheelCar>{

	public String initPass(EwheelCarVo entity){
		EwheelCar ewheelCar = super.selectById(entity.getCarId());
		if(ewheelCar==null){
			throw new BaseException("car does not exist",400);
		}
		if(entity.getExtAnswer1().equals(ewheelCar.getExtAnswer1())&&
				entity.getExtQuestion1().equals(ewheelCar.getExtQuestion1())){
			return ewheelCar.getInitialPassword();
		}else if(!entity.getExtQuestion1().equals(ewheelCar.getExtQuestion1())){
			throw new BaseException("wrong question",400);	
		}else{
			throw new BaseException("wrong answer",400);
		}
	}
	
}
