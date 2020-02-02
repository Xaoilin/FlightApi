package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class BusyFlightsController {

    @PostMapping(value = "/flights")
    public ResponseEntity<BusyFlightsResponse> getFlights(@RequestBody BusyFlightsRequest request) {
        BusyFlightsResponse busyFlightsResponse = new BusyFlightsResponse();



        return new ResponseEntity<>(busyFlightsResponse, HttpStatus.OK);
    }
}
