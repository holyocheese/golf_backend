package com.golf.model.authen;

import java.io.Serializable;

public interface IJWTInfo extends Serializable{
	
    String getUniqueName();

    String getId();

    String getName();
    
    String getTenantId();
}