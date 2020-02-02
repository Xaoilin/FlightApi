package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BusyFlightsControllerTest {

    @Autowired
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void givenAValidFlightRequest_whenCallingFlightsEndpoint_shouldReturnResponse200OK() throws Exception {
        BusyFlightsRequest busyFlightsRequest = new BusyFlightsRequest("LHR", "LAX", "", "", 4);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(busyFlightsRequest);

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