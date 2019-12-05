package com.golf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golf.base.biz.AuthBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.Client;

import io.swagger.annotations.Api;

@Api(value="用户角色controller",tags={"用户角色接口"})
@Controller
@RequestMapping("/role")
public class TestContoller extends BaseController<AuthBiz,Client>{

}
