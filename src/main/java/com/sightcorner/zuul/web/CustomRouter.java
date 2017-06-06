package com.sightcorner.zuul.web;


import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/route")
public class CustomRouter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRouter.class);


    public CustomRouter() {
        LOGGER.info("CustomRouter 构造函数初始化");
    }

    @RequestMapping("/**")
    public ModelAndView index(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOGGER.info("进入 CustomRouter 的 index");


        String requestURI = httpServletRequest.getRequestURI();
        String path = requestURI.substring(requestURI.indexOf("/route/") + "/route/".length());
        ModelAndView modelAndView = new ModelAndView("forward:/" + path);
        return modelAndView;
    }


}
