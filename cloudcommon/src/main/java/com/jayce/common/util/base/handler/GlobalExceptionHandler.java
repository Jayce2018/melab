package com.jayce.common.util.base.handler;

import com.alibaba.fastjson.JSONObject;
import com.jayce.common.util.base.exception.BaseException;
import com.jayce.common.util.base.exception.ExceptionCodeUtil;
import com.jayce.common.util.base.exception.auth.ClientTokenException;
import com.jayce.common.util.base.exception.auth.UserTokenException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

/**
 * date: 2017/9/8.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject baseExceptionHandler(HttpServletResponse response, BaseException ex) {
        log.error("【BaseException】", ex);
        String message = "异常信息为空";
        if (null != ex.getMessage() && StringUtils.isNotBlank(ex.getMessage())) {
            message = ex.getMessage().length() > 255 ? ex.getMessage().substring(0, 254) : ex.getMessage();
        }
        try {
            message = URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常", e);
        }
        response.setHeader("businessStatus", ex.getStatus() + "");
        response.setHeader("business-status", ex.getStatus() + "");
        response.setHeader("message", message);
        return new JSONObject();
    }

    @ExceptionHandler(ClientTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject clientTokenExceptionHandler(HttpServletResponse response, ClientTokenException ex) {
        String errorCode = ExceptionCodeUtil.bulidExceptionCode(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CHECK_ERROR);
        log.error("【ClientTokenException】", errorCode, ex);
        String message = "异常信息为空";
        if (null != ex.getMessage() && StringUtils.isNotBlank(ex.getMessage())) {
            message = ex.getMessage().length() > 255 ? ex.getMessage().substring(0, 254) : ex.getMessage();
        }
        try {
            message = URLEncoder.encode(errorCode + message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常", e);
        }
        response.setHeader("businessStatus", HttpStatus.FORBIDDEN.value() + "");
        response.setHeader("business-status", HttpStatus.FORBIDDEN.value() + "");
        response.setHeader("message", message);
        return new JSONObject();
    }

    @ExceptionHandler(UserTokenException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject userTokenExceptionHandler(HttpServletResponse response, UserTokenException ex) {
        String errorCode = ExceptionCodeUtil.bulidExceptionCode(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CHECK_ERROR);
        log.error("【UserTokenException】", errorCode, ex);
        String message = "异常信息为空";
        if (null != ex.getMessage() && StringUtils.isNotBlank(ex.getMessage())) {
            message = ex.getMessage().length() > 255 ? ex.getMessage().substring(0, 254) : ex.getMessage();
        }
        try {
            message = URLEncoder.encode(errorCode + message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常", e);
        }
        response.setHeader("businessStatus", HttpStatus.UNAUTHORIZED.value() + "");
        response.setHeader("business-status", HttpStatus.UNAUTHORIZED.value() + "");
        response.setHeader("message", message);
        return new JSONObject();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject bindExceptionHandle(HttpServletResponse response, BindException ex) {
        String errorCode = ExceptionCodeUtil.bulidExceptionCode(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CHECK_ERROR);
        log.error("【BindException】", errorCode, ex);
        response.setHeader("businessStatus", HttpStatus.BAD_REQUEST.value() + "");
        response.setHeader("business-status", HttpStatus.BAD_REQUEST.value() + "");
        String message = "异常信息为空";
        if (null != ex.getMessage() && StringUtils.isNotBlank(ex.getMessage())) {
            message = ex.getMessage().length() > 255 ? ex.getMessage().substring(0, 254) : ex.getMessage();
        }
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            // 此处可以抛个异常提示用户参数输入格式不正确
            List<ObjectError> objectErrorList = bindingResult.getAllErrors();
            if (null != objectErrorList && objectErrorList.size() > 0) {
                ObjectError objectError = objectErrorList.get(0);
                message = objectError.getDefaultMessage();
                if (StringUtils.isNotBlank(message)) {
                    if (message.length() > 255) {
                        message = message.substring(0, 254);
                    }
                    try {
                        response.setHeader("message", URLEncoder.encode(errorCode + message, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        log.error("编码异常", e);
                    }
                    return new JSONObject();
                }
            }
        }
        try {
            message = URLEncoder.encode(errorCode + message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常", e);
        }
        response.setHeader("message", message);
        return new JSONObject();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject constraintViolationExceptionHandle(HttpServletResponse response, ConstraintViolationException ex) {
        String errorCode = ExceptionCodeUtil.bulidExceptionCode(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CHECK_ERROR);
        log.error("【ConstraintViolationException】", errorCode, ex);
        response.setHeader("businessStatus", HttpStatus.BAD_REQUEST.value() + "");
        response.setHeader("business-status", HttpStatus.BAD_REQUEST.value() + "");
        String message = "异常信息为空";
        if (null != ex.getMessage() && StringUtils.isNotBlank(ex.getMessage())) {
            message = ex.getMessage().length() > 255 ? ex.getMessage().substring(0, 254) : ex.getMessage();
        }
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder messageBulider = new StringBuilder(errorCode);
        if (violations.size() > 0) {
            for (ConstraintViolation<?> item : violations) {
                /**打印验证不通过的信息*/
                messageBulider.append(item.getMessage()).append(",");
            }
            message = errorCode + messageBulider.toString();
            if (message.length() > 255) {
                message = message.substring(0, 254);
            }
        }
        try {
            message = URLEncoder.encode(errorCode + message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常", e);
        }
        response.setHeader("message", message);
        return new JSONObject();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject handle(HttpServletResponse response, MethodArgumentNotValidException ex) {
        String errorCode = ExceptionCodeUtil.bulidExceptionCode(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CHECK_ERROR);
        log.error("【MethodArgumentNotValidException】", errorCode, ex);
        response.setHeader("businessStatus", HttpStatus.BAD_REQUEST.value() + "");
        response.setHeader("business-status", HttpStatus.BAD_REQUEST.value() + "");
        String message = "异常信息为空";
        if (null != ex.getMessage() && StringUtils.isNotBlank(ex.getMessage())) {
            message = ex.getMessage().length() > 255 ? ex.getMessage().substring(0, 254) : ex.getMessage();
        }
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            // 此处可以抛个异常提示用户参数输入格式不正确
            List<ObjectError> objectErrorList = bindingResult.getAllErrors();
            if (null != objectErrorList && objectErrorList.size() > 0) {
                ObjectError objectError = objectErrorList.get(0);
                message = objectError.getDefaultMessage();
                if (StringUtils.isNotBlank(message)) {
                    if (message.length() > 255) {
                        message = message.substring(0, 254);
                    }
                    try {
                        response.setHeader("message", URLEncoder.encode(errorCode + message, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        log.error("编码异常", e);
                    }
                    return new JSONObject();
                }
            }
        }
        try {
            message = URLEncoder.encode(errorCode + message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常", e);
        }
        response.setHeader("message", message);
        return new JSONObject();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JSONObject otherExceptionHandler(HttpServletResponse response, Exception ex) {
        String errorCode = ExceptionCodeUtil.bulidExceptionCode(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CHECK_ERROR);
        log.error("【Exception】", errorCode, ex);
        response.setHeader("businessStatus", HttpStatus.BAD_REQUEST.value() + "");
        response.setHeader("business-status", HttpStatus.BAD_REQUEST.value() + "");
        String message = "异常信息为空";
        if (null != ex.getMessage() && StringUtils.isNotBlank(ex.getMessage())) {
            message = ex.getMessage().length() > 255 ? "未捕获异常!" : ex.getMessage();
        }
        try {
            message = URLEncoder.encode(errorCode + message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("编码异常", e);
        }
        response.setHeader("message", message);
        return new JSONObject();
    }
}
