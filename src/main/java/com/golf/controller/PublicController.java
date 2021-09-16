package com.golf.controller;

import com.golf.anno.IgnoreClientToken;
import com.golf.base.biz.AppSettingBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.AppSetting;
import com.golf.service.FileStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("public")
@Api(value="公开接口",tags={"公开接口接口"})
@IgnoreClientToken
public class PublicController extends BaseController<AppSettingBiz,AppSetting> {

    @Value("${path.appPath}")
    private String filePath;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private AppSettingBiz appSettingBiz;

    @GetMapping("/download/lastest/{type}")
    @ApiOperation(value = "根据类型下载最新安装包", notes = "错误401：id不存在\n错误402：未维护对应文件")
    public ResponseEntity<Resource> downloadFile(@PathVariable("type") Integer type, HttpServletRequest request) throws UnsupportedEncodingException {
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
