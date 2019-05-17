package com.jayce.jcgate.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@ApiModel(value = "CorsConfig", description = "跨域处理配置类")
public class CorsConfig {

    @ApiOperation(value = "buildConfig", notes = "配置跨域设置")
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许cookies跨域
        corsConfiguration.setAllowCredentials(true);
        // 允许向该服务器提交请求的URI，*表示全部允许。这里尽量限制来源域，比如http://xxxx:8080 ,以降低安全风险。
        corsConfiguration.addAllowedOrigin("*");
        // 允许访问的头信息,*表示全部
        corsConfiguration.addAllowedHeader("*");
        // 允许提交请求的方法，*表示全部允许，也可以单独设置GET、PUT等
        corsConfiguration.addAllowedMethod("*");
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        corsConfiguration.setMaxAge(18000L);
        return corsConfiguration;
    }

    @Bean
    @ApiOperation(value = "corsFilter", notes = "跨域配置注册")
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}
