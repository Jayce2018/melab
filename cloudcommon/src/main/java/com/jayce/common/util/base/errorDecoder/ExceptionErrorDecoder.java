package com.jayce.common.util.base.errorDecoder;

import com.jayce.common.util.base.exception.BaseException;
import com.jayce.common.util.base.exception.ExceptionCodeUtil;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * description: feign的除200类型都会进入feign的ErrorDecoder处理,此处做统一过滤
 * date: 2018-08-09 9:24
 * author: sun jie
 */
@Configuration
@Slf4j

public class ExceptionErrorDecoder implements ErrorDecoder {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionErrorDecoder.class);

    public ExceptionErrorDecoder() {
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        logger.debug("ExceptionErrorDecoder decode:" + methodKey + "/" + response);
        Exception exception = null;
        if (response.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            String message = "";
            if (null != response.headers().get("message")) {
                message = response.headers().get("message").toString();
                try {
                    message = URLDecoder.decode(message, "utf-8");
                    logger.debug("【Exception】" + message);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                exception = new BaseException(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_FEIGN_ERROR, message, 400);
                if (null != response.headers().get("businessStatus") && 0 != response.headers().get("businessStatus").size()) {
                    Integer businessStatus = Integer.parseInt(response.headers().get("businessStatus").iterator().next());
                    //过滤掉token异常状态码
                    if (666 == businessStatus) {
                        exception = new BaseException(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_FEIGN_ERROR, message, businessStatus);
                    }
                }
            } else {
                return feign.FeignException.errorStatus(methodKey, response);
            }
        }
        return exception;


    }
}

