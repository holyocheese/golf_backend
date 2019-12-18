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
import org.springframework.web.bind.annotation.RestController;

import com.golf.base.biz.AppSettingBiz;
import com.golf.common.BaseController;
import com.golf.dao.entity.AppSetting;
import com.golf.service.FileStorageService;

@RestController
@RequestMapping("appSetting")
public class AppSettingController extends BaseController<AppSettingBiz,AppSetting>{
	
	@Value("${path.appPath}")
	private String filePath;
	
	@Autowired
    private FileStorageService fileStorageService;

//	@RequestMapping(value ="/download",method = RequestMethod.GET)  
//    public ResponseEntity<byte[]> export() throws IOException {       
//        File file = new File(filePath+"123.pdf");
//        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),getDownloadHeaders("SmartHunter.rar"), HttpStatus.CREATED);    
//    }
    
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request) throws UnsupportedEncodingException {
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
