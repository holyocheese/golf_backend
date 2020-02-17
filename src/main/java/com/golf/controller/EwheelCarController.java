package com.golf.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.golf.base.biz.EwheelCarBiz;
import com.golf.common.exception.BaseException;
import com.golf.dao.entity.EwheelCar;
import com.golf.model.response.ObjectRestResponse;
import com.golf.model.vo.EwheelCarPassVo;
import com.golf.model.vo.EwheelCarVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("ewheelcar")
@Api(value="小车相关接口",tags={"小车相关接口"})
public class EwheelCarController{

	@Autowired
	private EwheelCarBiz baseBiz;
	
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "新增小车", notes = "id 初始密码 问题 答案 必填")
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<EwheelCar> add(@RequestBody EwheelCarVo entity){
    	EwheelCar ec = new EwheelCar();
    	BeanUtils.copyProperties(entity, ec);
    	ec.setPassword(entity.getInitialPassword());
        baseBiz.insertSelective(ec);
        return new ObjectRestResponse<EwheelCar>().data((EwheelCar)ec);
    }
    
    @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<EwheelCar> get(@PathVariable String id){
        ObjectRestResponse<EwheelCar> entityObjectRestResponse = new ObjectRestResponse<>();
        Object o = baseBiz.selectById(id);
        if(o==null){
        	throw new BaseException("小车不存在",400);
        }
        entityObjectRestResponse.data((EwheelCar)o);
        return entityObjectRestResponse;
    }
    
    @ApiOperation(value = "初始化小车密码", notes = "初始化小车密码")
    @RequestMapping(value = "/initPassword",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<String> get(@RequestBody EwheelCarVo entity){
    	ObjectRestResponse<String> entityObjectRestResponse = new ObjectRestResponse<>();
    	if(entity==null){
    		entityObjectRestResponse.setData(null);
    		entityObjectRestResponse.setMessage("empty entity");
    		entityObjectRestResponse.setStatus(500);
    		return entityObjectRestResponse;
    	}
    	if(StringUtils.isEmpty(entity.getCarId())){
    		entityObjectRestResponse.setData(null);
    		entityObjectRestResponse.setMessage("empty car id");
    		entityObjectRestResponse.setStatus(400);
    		return entityObjectRestResponse;
    	}
    	if(StringUtils.isEmpty(entity.getExtAnswer1())||StringUtils.isEmpty(entity.getExtQuestion1())){
    		entityObjectRestResponse.setData(null);
    		entityObjectRestResponse.setMessage("empty answer or question ");
    		entityObjectRestResponse.setStatus(400);
    		return entityObjectRestResponse;
    	}
    	entityObjectRestResponse.data(baseBiz.initPass(entity));
        return entityObjectRestResponse;
    }
}
