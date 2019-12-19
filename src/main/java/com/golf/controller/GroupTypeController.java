package com.golf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.golf.anno.IgnoreClientToken;
import com.golf.base.biz.GroupTypeBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.GroupType;

import springfox.documentation.annotations.ApiIgnore;
@IgnoreClientToken
@Controller
@RequestMapping("groupType")
@ApiIgnore
public class GroupTypeController extends BaseController<GroupTypeBiz,GroupType> {
//
//    @RequestMapping(value = "/page",method = RequestMethod.GET)
//    @ResponseBody
//    public TableResultResponse<Object> page(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "1")int page, String name){
//        Example example = new Example(GroupType.class);
//        if(StringUtils.isNotBlank(name))
//            example.createCriteria().andLike("name", "%" + name + "%");
//        Page<Object> result = PageHelper.startPage(page, limit);
//        baseBiz.selectByExample(example);
//        return new TableResultResponse<Object>(result.getTotal(),result.getResult());
//    }

}
