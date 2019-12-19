package com.golf.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadApp",method = RequestMethod.POST)
    public ObjectRestResponse<AppSetting> uploadApp(@RequestParam("file") MultipartFile file
    		,@RequestParam("id") Integer id) {
    	return new ObjectRestResponse<AppSetting>().data(appSettingBiz.uploadApp(file, id));
    }
    
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("id") Integer id,HttpServletRequest request) throws UnsupportedEncodingException {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource("123.txt");

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
        		.headers(getDownloadHeaders("123.txt"))
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
