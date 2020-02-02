package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsService;
import org.springframework.stereotype.Service;

@Service
public class BusyFlightsServiceImpl implements BusyFlightsService {

    @Override
    public BusyFlightsResponse getAggregatedFlights(BusyFlightsRequest request) {
        return null;
    }
}
