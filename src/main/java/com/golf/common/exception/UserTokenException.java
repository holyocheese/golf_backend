package com.golf.common.exception;

import com.golf.constant.CommonConstants;

public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
