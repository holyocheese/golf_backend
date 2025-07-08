package com.golf.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.golf.base.biz.CarVersionBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.CarVersion;
import com.golf.model.response.TableResultResponse;
import com.golf.util.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("carVersion")
@Api(value = "车型管理接口", tags = {"车型管理接口"})
public class CarVersionController extends BaseController<CarVersionBiz, CarVersion> {

    @Autowired
    private CarVersionBiz carVersionBiz;

    @ApiOperation(value = "分页列表", notes = "分页列表")
    @GetMapping("/page")
    @ResponseBody
    @Override
    public TableResultResponse<CarVersion> list(@RequestParam Map<String, Object> params) {
        // 查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByQuery(query);
    }
}
