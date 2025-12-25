package com.sixoneseven.blocktrain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    /**
     * 最简单的CORS配置 - 放行所有请求
     * 这个方法优先级最高，会处理所有请求
     */
    @Bean
    public CorsFilter corsFilter() {
        // 创建CORS配置
        CorsConfiguration config = new CorsConfiguration();

        // 1. 允许所有域名访问（最关键的配置）
        config.addAllowedOriginPattern("*");

        // 2. 允许所有请求方法
        config.addAllowedMethod("*");

        // 3. 允许所有请求头
        config.addAllowedHeader("*");

        // 4. 允许携带凭证（如cookies）
        config.setAllowCredentials(true);

        // 5. 预检请求的有效期，1小时
        config.setMaxAge(3600L);

        // 6. 暴露所有响应头给前端
        config.addExposedHeader("*");

        // 创建配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 7. 对所有路径生效（/** 表示所有请求）
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}