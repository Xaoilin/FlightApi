package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsService;
import com.travix.medusa.busyflights.service.CrazyAirServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class BusyFlightsController {

    @Autowired
    BusyFlightsService busyFlightsService;

    @PostMapping(value = "/flights")
    public ResponseEntity<BusyFlightsResponse> getFlights(@RequestBody BusyFlightsRequest request) {
        BusyFlightsResponse busyFlightsResponse = busyFlightsService.getAggregatedFlights(request);
        

        return new ResponseEntity<>(busyFlightsResponse, HttpStatus.OK);
    }
}
