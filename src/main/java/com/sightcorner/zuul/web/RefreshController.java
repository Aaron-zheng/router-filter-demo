package com.sightcorner.zuul.web;


import com.sightcorner.zuul.service.RouteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("refresh")
public class RefreshController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshController.class);

    @Autowired
    private RouteService routeService;

    @RequestMapping("update")
    @ResponseBody
    public String refresh() {
        LOGGER.info("进入 RefreshController 的 refresh");
        this.routeService.refresh();
        return "ok";
    }
}
