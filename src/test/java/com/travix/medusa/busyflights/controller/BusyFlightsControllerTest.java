package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsService;
import org.apache.tomcat.jni.Local;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BusyFlightsControllerTest {

    private MockMvc mvc;

    @Mock
    private BusyFlightsService busyFlightsServiceMock;

    @InjectMocks
    private BusyFlightsController busyFlightsController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(busyFlightsController).build();

    }

    @Test
    public void givenAValidFlightRequest_whenCallingFlightsEndpoint_shouldReturnResponse200OK() throws Exception {
        LocalDate departureDate = LocalDate.of(2020, 3, 1);
        LocalDate returnDate = LocalDate.of(2020, 3, 14);

        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest("LHR", "LAX",
                departureDate.toString(), returnDate.toString(), 4);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(busyFlightsRequest);

        when(busyFlightsServiceMock.getAggregatedFlights(any())).thenReturn(Collections.emptyList());

        String response = mvc.perform(post("/customer/flights")
                .header(CONTENT_TYPE, "application/json")
                .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertNotNull(response);
    }
}