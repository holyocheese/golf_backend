package com.golf.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.golf.base.biz.Firmware2SettingBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.Firmware2Setting;
import com.golf.model.response.ObjectRestResponse;
import com.golf.model.response.TableResultResponse;
import com.golf.service.FileStorageService4Firmware;
import com.golf.util.Query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("firmware2Setting")
@Api(value="固件2相关接口",tags={"固件2相关接口"})
public class Firmware2SettingController extends BaseController<Firmware2SettingBiz,Firmware2Setting>{
	
	@Autowired
    private FileStorageService4Firmware fileStorageService;
	
	@Autowired
    private Firmware2SettingBiz Firmware2SettingBiz;
	
    @ApiOperation(value = "分页列表", notes = "分页列表")
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    @ApiIgnore
    @Override
    public TableResultResponse<Firmware2Setting> list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByIdDescQuery(query);
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getLastest",method = RequestMethod.GET)
    @ApiOperation(value = "查询固件2最新版本", notes = "查询固件2最新版本，可根据车型版本筛选")
    public ObjectRestResponse<Firmware2Setting> getLastest(@RequestParam(value = "carVersionId") Integer carVersionId) {
        return new ObjectRestResponse<Firmware2Setting>().data(Firmware2SettingBiz.getLastestByCarVersion(carVersionId));
    }

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadApp",method = RequestMethod.POST)
    @ApiOperation(value = "根据id上传对应版本的固件2", notes = "根据id上传对应版本的固件2")
    @ApiIgnore
    public ObjectRestResponse<Firmware2Setting> uploadApp(@RequestParam("file") MultipartFile file
    		,@RequestParam("id") Integer id) {
    	return new ObjectRestResponse<Firmware2Setting>().data(Firmware2SettingBiz.uploadFirmware(file, id));
    }
    
    @GetMapping("/download")
    @ApiOperation(value = "根据id下载对应固件2", notes = "错误401：id不存在\n错误402：未维护对应文件")
    public ResponseEntity<Resource> downloadFile(@RequestParam("id") Integer id,HttpServletRequest request) throws UnsupportedEncodingException {
    	Firmware2Setting as = Firmware2SettingBiz.selectById(id);
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
