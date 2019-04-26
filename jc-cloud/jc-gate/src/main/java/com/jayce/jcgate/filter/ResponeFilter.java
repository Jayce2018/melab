package com.jayce.jcgate.filter;

import com.netflix.util.Pair;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
@Slf4j
@ApiModel(value = "ResponeFilter", description = "返回结果报文头处理")
public class ResponeFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            HttpServletResponse httpServletResponse = context.getResponse();
            boolean hasHeader = false;
            //解析返回报文头
            List<Pair<String, String>> pairList = context.getZuulResponseHeaders();
            for (Pair<String, String> pair : pairList) {
                String key = pair.first();
                //校验是否包含businessStatus的报文头
                if ("businessStatus".equals(key)) {
                    hasHeader = true;
                    httpServletResponse.setStatus(HttpStatus.OK.value());
                }
            }
            //不包含businessStatus报文头
            if (!hasHeader) {
                //校验业务逻辑是否成功
                if (httpServletResponse.getStatus() >= 200 && httpServletResponse.getStatus() < 300) {
                    context.addZuulResponseHeader("businessStatus", httpServletResponse.getStatus() + "");
                    context.addZuulResponseHeader("business-status", httpServletResponse.getStatus() + "");
                    context.addZuulResponseHeader("message", "");
                }
            }
            //设置允许返回操作的报文头
            context.addZuulResponseHeader("Access-Control-Expose-Headers", "business-status,businessStatus,message,pictureToken");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
