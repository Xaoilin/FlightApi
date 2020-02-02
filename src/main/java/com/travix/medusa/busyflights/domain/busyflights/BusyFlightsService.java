package com.travix.medusa.busyflights.domain.busyflights;

public interface BusyFlightsService {
    BusyFlightsResponse getAggregatedFlights(BusyFlightsRequest request);
}
