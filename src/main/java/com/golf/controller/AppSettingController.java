package com.golf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.golf.base.biz.AppSettingBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.AppSetting;

@RestController
@RequestMapping("appSetting")
public class AppSettingController extends BaseController<AppSettingBiz,AppSetting>{

}
