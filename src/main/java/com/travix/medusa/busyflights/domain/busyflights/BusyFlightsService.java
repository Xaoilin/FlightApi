package com.travix.medusa.busyflights.domain.busyflights;

import java.util.List;

public interface BusyFlightsService {
    List<BusyFlightsResponse> getAggregatedFlights(BusyFlightsRequest request);
}
