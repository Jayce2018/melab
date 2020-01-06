package com.jayce.common.util.base.exception;

import com.jayce.common.util.base.util.date.DateStyleEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

@Api(value = "ExceptionCodeUtil", description = "异常错误唯一编号生成工具类")
@Slf4j
public class ExceptionCodeUtil {

    public static void main(String[] args) {
        String exceptionCode = null;
        try {
            exceptionCode = bulidExceptionCode(ExceptionTypeEnum.EXCEPTION_TYPE_ADD_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("exceptionCode = " + exceptionCode);
    }

    @ApiOperation(value = "bulidExceptionCodeAndMessage", notes = "创建异常编号到错误信息前")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "错误信息", required = true, dataType = "object"),
            @ApiImplicitParam(name = "ExceptionTypeEnum", value = "异常报错类型枚举对象", required = true, dataType = "object")
    })
    public static String bulidExceptionCodeAndMessage(ExceptionTypeEnum exceptionTypeEnum, String message) {
        if (StringUtils.isNotBlank(message)) {
            StringBuilder stringBuilder = new StringBuilder(bulidExceptionCode(exceptionTypeEnum));
            stringBuilder.append(message);

            return stringBuilder.toString();
        } else {
            return bulidExceptionCode(exceptionTypeEnum);
        }
    }

    @ApiOperation(value = "bulidExceptionCode", notes = "创建异常编号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ExceptionSystemEnum", value = "异常报错系统枚举对象", required = true, dataType = "object"),
            @ApiImplicitParam(name = "ExceptionTypeEnum", value = "异常报错类型枚举对象", required = true, dataType = "object")
    })
    public static String bulidExceptionCode(ExceptionTypeEnum exceptionTypeEnum) {
        /**
         * 初始化参数
         */
        try {
            Date now = new Date();
            String timeString = DateFormatUtils.format(now, DateStyleEnum.DDHHMM.getValue());

            StringBuilder stringBuilder = new StringBuilder();
            if (null == exceptionTypeEnum) {

                log.error("找不到当前对应异常产生的操作类型");
            } else {
                stringBuilder.append(exceptionTypeEnum.getCode());
            }
            stringBuilder.append(timeString).append(":");
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();

            log.error("创建异常编号异常", e);

            return "未知来源";
        }
    }

    public enum ExceptionTypeEnum {

        EXCEPTION_TYPE_ADD_ERROR("AD", "新增异常"),

        EXCEPTION_TYPE_DELETE_ERROR("DE", "删除异常"),

        EXCEPTION_TYPE_UPDATE_ERROR("UP", "更新异常"),

        EXCEPTION_TYPE_SEARCH_ERROR("SC", "查询异常"),

        EXCEPTION_TYPE_CHECK_ERROR("VA", "校验异常"),

        EXCEPTION_TYPE_FEIGN_ERROR("FI", "调用异常"),

        EXCEPTION_TYPE_OTHER_ERROR("OT", "其他异常"),

        EXCEPTION_TYPE_DEVICE_TRADE_ERROR("DEVICE", "设备交易异常"),

        EXCEPTION_TYPE_CARD_TRADE_ERROR("CARD", "刷卡交易异常"),

        EXCEPTION_TYPE_QRCODE_TRADE_ERROR("QRCODE", "二维码交易异常"),

        EXCEPTION_TYPE_PAY_ALIPAY_ERROR("ALIPAY", "支付宝支付异常"),

        EXCEPTION_TYPE_PAY_WECHAT_ERROR("WECHAT", "微信支付异常"),
        ;

        private String code;

        private String message;

        ExceptionTypeEnum(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
