package com.travix.medusa.busyflights.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class CrazyAirServiceImpl implements CrazyAirService {

    private RestTemplate restTemplate;

    // Constructor to allow mocking
    public CrazyAirServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CrazyAirServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public List<CrazyAirResponse> getAvailableFlights(CrazyAirRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<CrazyAirRequest> entity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<List<CrazyAirResponse>> crazyAirResponse = restTemplate
                .exchange("crazyAirApi", HttpMethod.POST, entity, new ParameterizedTypeReference<List<CrazyAirResponse>>() {});

        if (crazyAirResponse != null) {
            return crazyAirResponse.getBody();
        }

        return Collections.emptyList();
    }
}
