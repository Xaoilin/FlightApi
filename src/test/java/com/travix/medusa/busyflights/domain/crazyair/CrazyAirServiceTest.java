package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.service.CrazyAirServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CrazyAirServiceTest {

    private CrazyAirService crazyAirService;
    private RestTemplate mockRestTemplate;

    @Before
    public void setUp() throws Exception {
        mockRestTemplate = mock(RestTemplate.class);
        crazyAirService = new CrazyAirServiceImpl(mockRestTemplate);
    }

    @Test
    public void givenValidRequest_whenCallingGetAvailableFlights_shouldReturnMatchingFlight() {
        String departureDate = LocalDate.of(2020, 3, 1).toString();
        String returnDate = LocalDate.of(2020, 3, 14).toString();
        String arrivalDate = LocalDate.of(2020, 3, 2).toString();

        CrazyAirRequest crazyAirRequest = new CrazyAirRequest("LHR", "LAX", departureDate, returnDate, 4);

        CrazyAirResponse crazyAirResponse = new CrazyAirResponse();

        crazyAirResponse.setAirline("American Airlines");
        crazyAirResponse.setCabinclass("Economy");
        crazyAirResponse.setDepartureAirportCode("LHR");
        crazyAirResponse.setDestinationAirportCode("LAX");
        crazyAirResponse.setDepartureDate(departureDate);
        crazyAirResponse.setArrivalDate(arrivalDate);
        crazyAirResponse.setPrice(542.42);

        List<CrazyAirResponse> crazyAirResponses = new ArrayList<>();
        crazyAirResponses.add(crazyAirResponse);

        ResponseEntity<List<CrazyAirResponse>> responseEntity = new ResponseEntity<>(crazyAirResponses, HttpStatus.OK);

        when(mockRestTemplate.exchange(any(String.class),
                any(HttpMethod.class),
                Matchers.<HttpEntity<CrazyAirRequest>>any(),
                Matchers.<ParameterizedTypeReference<List<CrazyAirResponse>>>any()))
                .thenReturn(responseEntity);

        List<CrazyAirResponse> availableFlights = crazyAirService.getAvailableFlights(crazyAirRequest);

        assertEquals("American Airlines", availableFlights.get(0).getAirline());
    }

    @Test
    public void givenNullResponse_whenCallingGetAvailableFlights_shouldReturnEmptyList() {
        String departureDate = LocalDate.of(2020, 3, 1).toString();
        String returnDate = LocalDate.of(2020, 3, 14).toString();

        CrazyAirRequest crazyAirRequest = new CrazyAirRequest("LHR", "LAX", departureDate, returnDate, 4);

        when(mockRestTemplate.exchange(any(String.class),
                any(HttpMethod.class),
                Matchers.<HttpEntity<CrazyAirRequest>>any(),
                Matchers.<ParameterizedTypeReference<List<CrazyAirResponse>>>any()))
                .thenReturn(null);

        List<CrazyAirResponse> availableFlights = crazyAirService.getAvailableFlights(crazyAirRequest);

        assertEquals(0, availableFlights.size());
    }
}