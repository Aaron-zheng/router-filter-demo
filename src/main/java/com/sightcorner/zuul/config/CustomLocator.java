package com.sightcorner.zuul.config;


import com.esotericsoftware.yamlbeans.YamlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomLocator extends SimpleRouteLocator implements RefreshableRouteLocator{

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLocator.class);

    private ZuulProperties properties;

    public CustomLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
        LOGGER.info("CustomLocator 构造函数初始化");
    }


    @Override
    protected Map<String, ZuulRoute> locateRoutes() {
        LOGGER.info("进入 CustomLocator 的 locateRoutes");

        LinkedHashMap<String, ZuulRoute> routes = new LinkedHashMap<>();


        //默认的从application.yml加载
        routes.putAll(super.locateRoutes());

        //自定义的从特定的yml加载
        routes.putAll(locateCustomRoutes());

        return routes;
    }

    private Map<? extends String,? extends ZuulRoute> locateCustomRoutes() {
        Map<String , ZuulRoute> routes = new LinkedHashMap<>();

        try {
            File file = new ClassPathResource("custom-route.yml").getFile();
            YamlReader yamlReader = new YamlReader(new FileReader(file));
            List<ZuulRoute> list = yamlReader.read(List.class, ZuulRoute.class);
            for(ZuulRoute route : list) {
                routes.put(route.getPath(), route);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return routes;
    }


    @Override
    public void refresh() {
        LOGGER.info("进入 CustomLocator 的 refresh");
        super.doRefresh();
    }
}
