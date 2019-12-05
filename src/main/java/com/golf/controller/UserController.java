package com.golf.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.golf.anno.IgnoreClientToken;
import com.golf.base.biz.MenuBiz;
import com.golf.base.biz.UserBiz;
import com.golf.common.BaseController;
import com.golf.common.TableResultResponse;
import com.golf.dao.entity.Menu;
import com.golf.dao.entity.User;
import com.golf.model.vo.FrontUser;
import com.golf.model.vo.MenuTree;
import com.golf.service.GroupService;
import com.golf.service.PermissionService;
import com.golf.util.Query;

@IgnoreClientToken
@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserBiz,User>{
	@Autowired
    private PermissionService permissionService;

    @Autowired
    private GroupService groupService;
    @Autowired
    private MenuBiz menuBiz;

    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(String token) throws Exception {
        FrontUser userInfo = permissionService.getUserInfo(token);
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    List<MenuTree> getMenusByUsername(String token) throws Exception {
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Menu> getAllMenus() throws Exception {
        return menuBiz.selectListAll();
    }
    
    @RequestMapping(value = "/pageExactly",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<User> listExactly(@RequestParam Map<String, Object> params){
        //查询列表数据
    	params.put("username", getCurrentUserName());
        Query query = new Query(params);
        return baseBiz.selectByQueryExactly(query);
    }
    
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<User> list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        TableResultResponse<User> result =  baseBiz.selectByQuery(query);
        long total = result.getData().getTotal();
        if(total>0){
        	List<User> userList = result.getData().getRows();
            for(User user:userList){
            	List<String> userGroupStr = groupService.selectUserGroup(user.getId());
            	if(userGroupStr.size()>1){
            		user.setAttr1(userGroupStr.get(0));
            		user.setAttr2(userGroupStr.get(1));
            	}
            }
            return new TableResultResponse<User>(total,userList);
        }
        return result;
    }
}
