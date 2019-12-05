package com.golf.common.exception;

import com.golf.constant.CommonConstants;

public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
