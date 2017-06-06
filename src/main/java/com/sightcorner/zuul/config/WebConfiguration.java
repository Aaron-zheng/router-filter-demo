package com.sightcorner.zuul.config;


import com.sightcorner.zuul.web.CustomInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {


    private static final Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);

    public WebConfiguration() {

        LOGGER.info("WebConfiguration 构造函数初始化");

    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("进入 WebConfiguration 的 addInterceptors");
        registry.addInterceptor(new CustomInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
