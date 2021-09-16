package com.golf.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.golf.anno.IgnoreClientToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.golf.base.biz.AppSettingBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.AppSetting;
import com.golf.model.response.ObjectRestResponse;
import com.golf.model.response.TableResultResponse;
import com.golf.service.FileStorageService;
import com.golf.util.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("appSetting")
@Api(value="app版本相关接口",tags={"app版本相关接口"})
public class AppSettingController extends BaseController<AppSettingBiz,AppSetting>{
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
    private AppSettingBiz appSettingBiz;
	
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    @ApiIgnore
    @Override
    public TableResultResponse<AppSetting> list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByIdDescQuery(query);
    }
	
	@RequestMapping(value = "/getLastest",method = RequestMethod.GET)
    @ApiOperation(value = "根据客户端类型来查询最新版本", notes = "手机系统类型：1、安卓 2、IOS")
    public ObjectRestResponse<AppSetting> getLastest(@RequestParam("type") Integer type) {
		ObjectRestResponse<AppSetting> result =  new ObjectRestResponse<AppSetting>();
		if(type>2){
			result.setData(null);
			result.setMessage("wrong client");
			result.setStatus(400);
			return result;
		}
		result.setData(appSettingBiz.getLastest(type));
		result.setMessage("OK");
		result.setStatus(200);
    	return result;
    }
	
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadApp",method = RequestMethod.POST)
    @ApiIgnore
    public ObjectRestResponse<AppSetting> uploadApp(@RequestParam("file") MultipartFile file
    		,@RequestParam("id") Integer id) {
    	ObjectRestResponse<AppSetting> result =  new ObjectRestResponse<AppSetting>();
		result.setData(appSettingBiz.uploadApp(file, id));
		result.setMessage("OK");
		result.setStatus(200);
    	return result;
    }
    
    @GetMapping("/download")
    @ApiOperation(value = "根据id下载对应app", notes = "错误401：id不存在\n错误402：未维护对应文件")
    public ResponseEntity<Resource> downloadFile(@RequestParam("id") Integer id,HttpServletRequest request) throws UnsupportedEncodingException {
    	AppSetting as = appSettingBiz.selectById(id);
    	if(null==as){
    		return ResponseEntity.status(401).build();
    	}
    	if(StringUtils.isEmpty(as.getFileName())){
    		return ResponseEntity.status(402).build();
    	}
    	// Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(as.getFileName());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
        		.headers(getDownloadHeaders(as.getFileName()))
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    @GetMapping("/download/lastest/{type}")
    @ApiOperation(value = "根据类型下载最新安装包", notes = "错误401：id不存在\n错误402：未维护对应文件")
    public ResponseEntity<Resource> downloadLastestFile(@PathVariable("type") Integer type, HttpServletRequest request) throws UnsupportedEncodingException {
        AppSetting as = appSettingBiz.getLastest(type);
        if(null==as){
            return ResponseEntity.status(401).body(null);
        }
        if(StringUtils.isEmpty(as.getFileName())){
            return ResponseEntity.status(402).body(null);
        }
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(as.getFileName());

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .headers(getDownloadHeaders(as.getFileName()))
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
