package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xiaodm on 2018/5/2.
 */
@RestController
public class IncidentController {
    @Autowired
    IncidentService appealService;

    Logger log = LoggerFactory.getLogger(IncidentController.class);

    @RequestMapping("/incidents")
    public List<Incident> getIncidentList() {
        log.info("start to getIncidentList");
        return appealService.getIncidents();
    }

}
