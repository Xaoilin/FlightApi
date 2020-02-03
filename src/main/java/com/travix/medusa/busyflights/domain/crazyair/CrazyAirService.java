package com.travix.medusa.busyflights.domain.crazyair;

import java.util.List;

public interface CrazyAirService {
    List<CrazyAirResponse> getAvailableFlights(CrazyAirRequest request);
}
