package com.jayce.common.util.base.exception.device;

import com.jayce.common.util.base.exception.BaseException;
import com.jayce.common.util.base.exception.ExceptionCodeUtil;

public class QrcodeException extends BaseException {

    /**
     * Instantiates a new Qrcode exception.
     *
     * @param message the message
     */
    public QrcodeException(String message) {
        super(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_QRCODE_TRADE_ERROR, message);
    }

    public QrcodeException(String message, Throwable cause) {
        super(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_QRCODE_TRADE_ERROR, message, cause);
    }
}