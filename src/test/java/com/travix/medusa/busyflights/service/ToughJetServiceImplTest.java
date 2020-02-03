package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ToughJetServiceImplTest {

    private ToughJetService toughJetService;
    private RestTemplate mockRestTemplate;

    @Before
    public void setUp() throws Exception {
        mockRestTemplate = mock(RestTemplate.class);
        toughJetService = new ToughJetServiceImpl(mockRestTemplate);
    }

    @Test
    public void givenValidRequest_whenCallingGetAvailableFlights_shouldReturnMatchingFlight() {
        String departureDate = LocalDate.of(2020, 3, 1).toString();
        String arrivalDate = LocalDate.of(2020, 3, 2).toString();
        String returnDate = LocalDate.of(2020, 3, 14).toString();

        ToughJetRequest toughJetRequest = new ToughJetRequest("LHR", "LAX", departureDate, returnDate, 4);

        ToughJetResponse toughJetResponse = new ToughJetResponse();

        toughJetResponse.setCarrier("American Airlines");
        toughJetResponse.setDepartureAirportName("LHR");
        toughJetResponse.setArrivalAirportName("LAX");
        toughJetResponse.setOutboundDateTime(departureDate);
        toughJetResponse.setInboundDateTime(arrivalDate);
        toughJetResponse.setBasePrice(542.42);
        toughJetResponse.setDiscount(10);
        toughJetResponse.setTax(12.5);

        List<ToughJetResponse> toughJetResponses = new ArrayList<>();
        toughJetResponses.add(toughJetResponse);

        ResponseEntity<List<ToughJetResponse>> responseEntity = new ResponseEntity<>(toughJetResponses, HttpStatus.OK);

        when(mockRestTemplate.exchange(any(String.class),
                any(HttpMethod.class),
                Matchers.<HttpEntity<ToughJetRequest>>any(),
                Matchers.<ParameterizedTypeReference<List<ToughJetResponse>>>any()))
                .thenReturn(responseEntity);

        List<ToughJetResponse> availableFlights = toughJetService.getAvailableFlights(toughJetRequest);

        assertEquals("American Airlines", availableFlights.get(0).getCarrier());
    }

    @Test
    public void givenNullResponse_whenCallingGetAvailableFlights_shouldReturnEmptyList() {
        String departureDate = LocalDate.of(2020, 3, 1).toString();
        String returnDate = LocalDate.of(2020, 3, 14).toString();

        ToughJetRequest toughJetRequest = new ToughJetRequest("LHR", "LAX", departureDate, returnDate, 4);

        when(mockRestTemplate.exchange(any(String.class),
                any(HttpMethod.class),
                Matchers.<HttpEntity<ToughJetRequest>>any(),
                Matchers.<ParameterizedTypeReference<List<ToughJetResponse>>>any()))
                .thenReturn(null);

        List<ToughJetResponse> availableFlights = toughJetService.getAvailableFlights(toughJetRequest);

        assertEquals(0, availableFlights.size());
    }
}