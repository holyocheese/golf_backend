package com.golf.common.exception;

import com.golf.constant.CommonConstants;

public class ClientTokenException extends BaseException {
    public ClientTokenException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}

