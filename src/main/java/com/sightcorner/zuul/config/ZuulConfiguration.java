package com.sightcorner.zuul.config;


import com.netflix.zuul.ZuulFilter;
import com.sightcorner.zuul.web.CustomFilter;
import com.sightcorner.zuul.web.CustomLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZuulConfiguration.class);

    @Autowired
    ZuulProperties zuulProperties;

    @Autowired
    ServerProperties serverProperties;


    public ZuulConfiguration() {
        LOGGER.info("ZuulConfiguration 构造函数初始化");
    }

    @Bean
    public RouteLocator routeLocator() {
        LOGGER.info("进入 ZuulConfiguration 的 routeLocator");

        CustomLocator customLocator = new CustomLocator(this.serverProperties.getServletPrefix(), zuulProperties);
        return customLocator;
    }


    @Bean
    public ZuulFilter routeFilter() {
        LOGGER.info("进入 ZuulConfiguration 的 routeFilter");

        return new CustomFilter();
    }

}
