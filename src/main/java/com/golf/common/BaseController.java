package com.golf.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.golf.util.Query;

import io.swagger.annotations.ApiOperation;


public class BaseController<Biz extends BaseBiz,Entity> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Biz baseBiz;
    @ApiOperation(value = "新增数据", notes = "新增数据")
    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Entity> add(@RequestBody Entity entity){
        baseBiz.insertSelective(entity);
        return new ObjectRestResponse<Entity>();
    }
    @ApiOperation(value = "根据id获取数据", notes = "根据id获取数据")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Entity> get(@PathVariable int id){
        ObjectRestResponse<Entity> entityObjectRestResponse = new ObjectRestResponse<>();
        Object o = baseBiz.selectById(id);
        entityObjectRestResponse.data((Entity)o);
        return entityObjectRestResponse;
    }
    @ApiOperation(value = "根据id更新数据", notes = "根据id更新数据")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Entity> update(@RequestBody Entity entity){
        baseBiz.updateSelectiveById(entity);
        return new ObjectRestResponse<Entity>();
    }
    @ApiOperation(value = "根据id删除数据", notes = "根据id删除数据")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Entity> remove(@PathVariable int id){
        baseBiz.deleteById(id);
        return new ObjectRestResponse<Entity>();
    }
    @ApiOperation(value = "all列表", notes = "all列表")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> all(){
        return baseBiz.selectListAll();
    }
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Entity> list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByQuery(query);
    }
    @ApiOperation(value = "分页列表(精确查询)", notes = "分页列表(精确查询)")
    @RequestMapping(value = "/pageEqual",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Entity> listEqual(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByQueryEqual(query);
    }
//    public String getCurrentUserName(){
//        return BaseContextHandler.getUsername();
//    }
//    
//    public String getCurrentUserId(){
//        return BaseContextHandler.getUserID();
//    }
//    
//    public String getCurrentTenantId(){
//        return BaseContextHandler.get("tenantId")+"";
//    }
}
