package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ToughJetServiceImpl implements ToughJetService {

    private RestTemplate restTemplate;

    // Constructor to allow mocking
    public ToughJetServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ToughJetServiceImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public List<ToughJetResponse> getAvailableFlights(ToughJetRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<ToughJetRequest> entity = new HttpEntity<>(request, httpHeaders);

        ResponseEntity<List<ToughJetResponse>> toughJetResponse = restTemplate
                .exchange("toughJetApi", HttpMethod.POST, entity, new ParameterizedTypeReference<List<ToughJetResponse>>() {});

        if (toughJetResponse != null) {
            return toughJetResponse.getBody();
        }

        return Collections.emptyList();
    }
}
