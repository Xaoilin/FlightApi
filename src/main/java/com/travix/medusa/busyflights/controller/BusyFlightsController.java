package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsService;
import com.travix.medusa.busyflights.service.BusyFlightsServiceImpl;
import com.travix.medusa.busyflights.service.CrazyAirServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class BusyFlightsController {

    @Autowired
    private BusyFlightsService busyFlightsService;

    @PostMapping(value = "/flights")
    public ResponseEntity<List<BusyFlightsResponse>> getFlights(@RequestBody BusyFlightsRequest request) {

        if (request.getNumberOfPassengers() > 4) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
        }

        List<BusyFlightsResponse> busyFlightsResponse = busyFlightsService.getAggregatedFlights(request);

        busyFlightsResponse = busyFlightsResponse.stream()
                .sorted(Comparator.comparing(BusyFlightsResponse::getFare))
                .collect(Collectors.toList());

        return new ResponseEntity<>(busyFlightsResponse, HttpStatus.OK);
    }
}
