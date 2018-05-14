package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * Created by xiaodm on 2018/5/2.
 */
@Service
public class IncidentService {
    Logger log = LoggerFactory.getLogger(IncidentController.class);

    @Autowired
    private RestTemplate restTemplate;

    List<Incident> appeals = new ArrayList<>();

    @Value("${RequestUrl.appealService}")
    String appealUrl;

    @Value("${Incident.preName}")
    String IncidentPreName;

    //    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public List<Incident> getIncidents() {
        List<Incident> list = new ArrayList<>();

        ResponseEntity<List<Appeal>> rateResponse =
                restTemplate.exchange(appealUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Appeal>>() {
                        });
        List<Appeal> appeals = rateResponse.getBody();

        for (int id = 0; id < 10; ++id) {
            Incident incident = new Incident();
            incident.setId(id);
            incident.setAddr(IncidentPreName + " addr" + id);
            incident.setTel(IncidentPreName + " Tel" + id);
            incident.setIncidentName(IncidentPreName + " Name" + id);
            incident.setAppealTime(new Date());
            incident.setIncidentStatus(IncidentPreName + " status" + id);

            for (Appeal appeal : appeals) {
                if (appeal.getId() == id) {
                    incident.setAppeal(appeal);
                }
            }

            list.add(incident);
        }

        return list;
    }
}
