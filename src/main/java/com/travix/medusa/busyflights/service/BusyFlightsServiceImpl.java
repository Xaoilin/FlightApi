package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsService;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirBusyFlightsMapper;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirService;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetBusyFlightsMapper;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusyFlightsServiceImpl implements BusyFlightsService {

    @Autowired
    private CrazyAirService crazyAirService;

    @Autowired
    private ToughJetService toughJetService;

    @Override
    public List<BusyFlightsResponse> getAggregatedFlights(BusyFlightsRequest request) {
        String origin = request.getOrigin();
        String destination = request.getDestination();
        String departureDate = request.getDepartureDate();
        String returnDate = request.getReturnDate();
        int numberOfPassengers = request.getNumberOfPassengers();

        CrazyAirRequest crazyAirRequest = new CrazyAirRequest(origin,destination,departureDate,returnDate,numberOfPassengers);
        List<CrazyAirResponse> crazyAirAvailableFlights = crazyAirService.getAvailableFlights(crazyAirRequest);

        ToughJetRequest toughJetRequest = new ToughJetRequest(origin,destination,departureDate,returnDate,numberOfPassengers);
        List<ToughJetResponse> toughJetAvailableFlights = toughJetService.getAvailableFlights(toughJetRequest);

        List<BusyFlightsResponse> busyFlightsResponses = new LinkedList<>(); // Do not need read operations, insert operations are O(1)

        busyFlightsResponses.addAll(mapCrazyAirFlights(crazyAirAvailableFlights));
        busyFlightsResponses.addAll(mapToughJetFlights(toughJetAvailableFlights));

        return busyFlightsResponses;
    }

    private List<BusyFlightsResponse> mapCrazyAirFlights(List<CrazyAirResponse> availableFlights) {
        return availableFlights.stream()
                .map(new CrazyAirBusyFlightsMapper())
                .collect(Collectors.toList());
    }

    private List<BusyFlightsResponse> mapToughJetFlights(List<ToughJetResponse> availableFlights) {
        return availableFlights.stream()
                .map(new ToughJetBusyFlightsMapper())
                .collect(Collectors.toList());
    }
}
