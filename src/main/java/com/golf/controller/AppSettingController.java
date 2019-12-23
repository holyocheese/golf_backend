package com.golf.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.golf.base.biz.AppSettingBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.AppSetting;
import com.golf.model.response.ObjectRestResponse;
import com.golf.service.FileStorageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("appSetting")
@Api(value="app版本相关接口",tags={"app版本相关接口"})
public class AppSettingController extends BaseController<AppSettingBiz,AppSetting>{
	
	@Value("${path.appPath}")
	private String filePath;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
    private AppSettingBiz appSettingBiz;
	
	@RequestMapping(value = "/getLastest",method = RequestMethod.GET)
    @ApiOperation(value = "根据客户端类型来查询最新版本", notes = "手机系统类型：1、安卓 2、IOS")
    public ObjectRestResponse<AppSetting> getLastest(@RequestParam("type") Integer type) {
		if(type>2){
			ObjectRestResponse<AppSetting> result =  new ObjectRestResponse<AppSetting>();
			result.setData(null);
			result.setMessage("客户端类型不存在");
			result.setStatus(400);
			return result;
		}
    	return new ObjectRestResponse<AppSetting>().data(appSettingBiz.getLastest(type));
    }
	
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadApp",method = RequestMethod.POST)
    @ApiIgnore
    public ObjectRestResponse<AppSetting> uploadApp(@RequestParam("file") MultipartFile file
    		,@RequestParam("id") Integer id) {
    	return new ObjectRestResponse<AppSetting>().data(appSettingBiz.uploadApp(file, id));
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
}
