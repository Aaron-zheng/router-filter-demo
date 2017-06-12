package com.sightcorner.zuul.web;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class CustomFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFilter.class);


    public CustomFilter() {
        LOGGER.info("CustomFilter 构造函数初始化");
    }

    /*
        pre：可以在请求被路由之前调用
        route：在路由请求时候被调用
        post：在routing和error过滤器之后被调用
        error：处理请求时发生错误时被调用
    */
    @Override
    public String filterType() {
        LOGGER.info("进入 CustomFilter 的 filterType");
        return "pre";
    }

    @Override
    public int filterOrder() {
        LOGGER.info("进入 CustomFilter 的 filterOrder");
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        LOGGER.info("进入 CustomFilter 的 shouldFilter");
        //如果为true，则进入run方法
        return true;
    }

    @Override
    public Object run() {
        LOGGER.info("进入 CustomFilter 的 run");
        //自己的逻辑
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        LOGGER.info("httpServletRequest.getContextPath(): " + httpServletRequest.getContextPath());
        LOGGER.info("httpServletRequest.getRequestURI(): " + httpServletRequest.getRequestURI());
        LOGGER.info("httpServletRequest.getRequestURL(): " + httpServletRequest.getRequestURL());
        //执行

        return null;
    }
}
