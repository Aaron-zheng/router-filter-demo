package com.sightcorner.zuul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    RouteLocator routeLocator;


    @Override
    public void refresh() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        applicationEventPublisher.publishEvent(routesRefreshedEvent);
    }
}
