package com.jayce.common.util.base.exception.device;

import com.jayce.common.util.base.exception.BaseException;
import com.jayce.common.util.base.exception.ExceptionCodeUtil;

public class CardException extends BaseException {

    /**
     * Instantiates a new Card exception.
     *
     * @param message the message
     */
    public CardException(String message) {
        super(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CARD_TRADE_ERROR, message);
    }

    public CardException(String message, Throwable cause) {
        super(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CARD_TRADE_ERROR, message, cause);
    }
}
