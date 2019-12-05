package com.golf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.golf.common.exception.UserInvalidException;
import com.golf.model.request.JwtAuthenticationRequest;
import com.golf.model.response.ObjectRestResponse;
import com.golf.service.AuthService;


@RestController
@RequestMapping("jwt")
public class AuthenController {
	
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "token", method = RequestMethod.POST)
    public ObjectRestResponse<String> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        try{
        	final String token = authService.login(authenticationRequest);
        	return new ObjectRestResponse<>().data(token);
        }catch (UserInvalidException e) {
        	ObjectRestResponse<String> res = new ObjectRestResponse<String>();
        	res.setMessage("用户名密码错误");
        	res.setStatus(400);
        	return res;
		}catch (Exception e) {
        	ObjectRestResponse<String> res = new ObjectRestResponse<String>();
        	res.setMessage("服务器出错");
        	res.setStatus(500);
        	return res;
		}
    }
}
