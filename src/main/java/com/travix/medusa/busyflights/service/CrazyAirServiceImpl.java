package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirService;
import org.springframework.stereotype.Service;

@Service
public class CrazyAirServiceImpl implements CrazyAirService {

    @Override
    public CrazyAirResponse getAvailableFlights(CrazyAirRequest request) {
        return null;
    }
}
