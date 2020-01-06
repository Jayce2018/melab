package com.jayce.common.util.base.exception.device;

import com.jayce.common.util.base.exception.BaseException;
import com.jayce.common.util.base.exception.ExceptionCodeUtil;

public class DeviceException extends BaseException {

    /**
     * Instantiates a new Device exception.
     *
     * @param message the message
     */
    public DeviceException(String message) {
        super(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_DEVICE_TRADE_ERROR, message);
    }

    public DeviceException(String message, Throwable cause) {
        super(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_DEVICE_TRADE_ERROR, message, cause);
    }
}
