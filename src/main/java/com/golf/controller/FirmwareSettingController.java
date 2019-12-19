package com.golf.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.golf.base.biz.FirmwareSettingBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.FirmwareSetting;
import com.golf.model.response.ObjectRestResponse;
import com.golf.service.FileStorageService4Firmware;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("firmwareSetting")
@Api(value="固件相关接口",tags={"固件相关接口"})
public class FirmwareSettingController extends BaseController<FirmwareSettingBiz,FirmwareSetting>{
	
	@Autowired
    private FileStorageService4Firmware fileStorageService;
	
	@Autowired
    private FirmwareSettingBiz firmwareSettingBiz;
	

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadApp",method = RequestMethod.POST)
    @ApiOperation(value = "根据id上传对应版本的固件", notes = "根据id上传对应版本的固件")
    public ObjectRestResponse<FirmwareSetting> uploadApp(@RequestParam("file") MultipartFile file
    		,@RequestParam("id") Integer id) {
    	return new ObjectRestResponse<FirmwareSetting>().data(firmwareSettingBiz.uploadFirmware(file, id));
    }
    
    @GetMapping("/download")
    @ApiOperation(value = "根据id获取对应版本的固件", notes = "根据id获取对应版本的固件")
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
