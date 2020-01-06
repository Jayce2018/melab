package com.jayce.common.util.base.exception.auth;


import com.jayce.common.util.base.constant.CommonConstants;
import com.jayce.common.util.base.exception.BaseException;

/**
 * date: 2017/9/10.
 */
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(null, message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
