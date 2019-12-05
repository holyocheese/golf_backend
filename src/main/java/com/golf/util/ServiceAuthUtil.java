package com.golf.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.golf.common.BaseResponse;
import com.golf.common.ObjectRestResponse;
import com.golf.common.ServiceAuthConfig;
import com.golf.common.exception.ClientTokenException;
import com.golf.config.IJWTInfo;
import com.golf.service.AuthClientService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

public class ServiceAuthUtil {

	@Autowired
    private ServiceAuthConfig serviceAuthConfig;

    @Autowired
    private AuthClientService serviceAuthFeign;

    private List<String> allowedClient;
    private String clientToken;


    public IJWTInfo getInfoFromToken(String token) throws Exception {
        try {
            return JWTHelper.getInfoFromToken(token, serviceAuthConfig.getPubKeyByte());
        } catch (ExpiredJwtException ex) {
            throw new ClientTokenException("Client token expired!");
        } catch (SignatureException ex) {
            throw new ClientTokenException("Client token signature error!");
        } catch (IllegalArgumentException ex) {
            throw new ClientTokenException("Client token is null or empty!");
        }
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAllowedClient() {
    	this.allowedClient = serviceAuthFeign.getAllowedClient(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshClientToken() throws Exception {
    	this.clientToken  = serviceAuthFeign.apply(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
    }


    public String getClientToken() throws Exception {
        if (this.clientToken == null) {
            this.refreshClientToken();
        }
        return clientToken;
    }

    public List<String> getAllowedClient() {
        if (this.allowedClient == null) {
            this.refreshAllowedClient();
        }
        return allowedClient;
    }
}
