package com.jayce.jcgate.filter;

import com.jayce.feign.feign.service.test.TestService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
@Slf4j
public class AccessFilter extends ZuulFilter {

    @Value("${gate.ignore.startWith.uri}")
    private String ignoreStartWithUri;

    @Value("${gate.user.trade.time.expireSecond:300}")
    private Long userTradeTimeExpireSecond;

    @Value("${zuul.prefix}")
    private String zuulUriPrefix;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Autowired
    private TestService feignService;

    @Override
    public Object run() throws ZuulException {
        //String info = feignService.feign();
        //System.out.println("权限校验信息：" + info);
        //获取请求地址
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //去掉公共请求网关路径部分
        String[] urlSplits = request.getRequestURI().split(zuulUriPrefix);
        final String requestUri = urlSplits[1];
        log.info("===>send [{}], request to [{}]\n", request.getMethod(), requestUri);

        /**
         * TODO 对地址做拦截放过 startWith 需要动态配置
         */
        boolean ignoreFilterUrl = false;
        for (String s : ignoreStartWithUri.split(",")) {
            if (requestUri.startsWith(s)) {
                ignoreFilterUrl = true;
                break;
            }
        }
        if (ignoreFilterUrl) {
            return null;
        }
        //校验报文头
        ctx.addZuulRequestHeader("zuul-request", "true");
        ctx.addZuulRequestHeader("zuul-requesturi", requestUri);
        ctx.addZuulRequestHeader("zuul-real-ip", request.getHeader("X-Real-IP"));
        return null;
    }

    @ApiOperation(value = "toUtf8", notes = "转码")
    private static String toUtf8(String string) {
        String var = "token不能为空";
        try {
            String message = URLEncoder.encode(var, "UTF-8");
            return message;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
