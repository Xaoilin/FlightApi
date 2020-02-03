package com.travix.medusa.busyflights.domain.crazyair;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponseBuilder;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.travix.medusa.busyflights.util.DateUtils.convertLocalDateTimeToIsoLocalDate;

public final class CrazyAirBusyFlightsMapper implements Function<CrazyAirResponse, BusyFlightsResponse> {

    @Override
    public BusyFlightsResponse apply(CrazyAirResponse crazyAirResponse) {

        String departureDate = convertLocalDateTimeToIsoLocalDate(crazyAirResponse.getDepartureDate());
        String arrivalDate = convertLocalDateTimeToIsoLocalDate(crazyAirResponse.getArrivalDate());

        return new BusyFlightsResponseBuilder()
                .withAirline(crazyAirResponse.getAirline())
                .withDepartureAirportCode(crazyAirResponse.getDepartureAirportCode())
                .withDestinationAirportCode(crazyAirResponse.getDestinationAirportCode())
                .withDepartureDate(departureDate)
                .withArrivalDate(arrivalDate)
                .withFare(BigDecimal.valueOf(crazyAirResponse.getPrice()))
                .build();
    }
}
