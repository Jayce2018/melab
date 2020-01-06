package com.jayce.common.util.base.exception.auth;


import com.jayce.common.util.base.constant.CommonConstants;
import com.jayce.common.util.base.exception.BaseException;

/**
 * date: 2017/9/12.
 */
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(null, message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}
