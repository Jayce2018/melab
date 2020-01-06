package com.jayce.common.util.base.exception.auth;


import com.jayce.common.util.base.constant.CommonConstants;
import com.jayce.common.util.base.exception.BaseException;

/**
 * date: 2017/9/8.
 */
public class UserTokenException extends BaseException {
    public UserTokenException(String message) {
        super(null, message, CommonConstants.EX_USER_INVALID_CODE);
    }
}
