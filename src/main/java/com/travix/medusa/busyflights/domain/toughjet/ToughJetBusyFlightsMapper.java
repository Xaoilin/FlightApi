package com.travix.medusa.busyflights.domain.toughjet;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponseBuilder;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;

import java.math.BigDecimal;
import java.util.function.Function;

import static com.travix.medusa.busyflights.util.DateUtils.convertIsoInstantToIsoLocalDate;
import static com.travix.medusa.busyflights.util.DateUtils.convertLocalDateTimeToIsoLocalDate;

public final class ToughJetBusyFlightsMapper implements Function<ToughJetResponse, BusyFlightsResponse> {

    @Override
    public BusyFlightsResponse apply(ToughJetResponse toughJetResponse) {

        String departureDate = convertIsoInstantToIsoLocalDate(toughJetResponse.getOutboundDateTime());
        String arrivalDate = convertIsoInstantToIsoLocalDate(toughJetResponse.getInboundDateTime());

        BigDecimal fare = BigDecimal.valueOf(toughJetResponse.getBasePrice())
                .multiply(BigDecimal.valueOf(toughJetResponse.getTax()))
                .multiply(BigDecimal.valueOf(toughJetResponse.getDiscount() / 100));

        return new BusyFlightsResponseBuilder()
                .withAirline(toughJetResponse.getCarrier())
                .withDepartureAirportCode(toughJetResponse.getDepartureAirportName())
                .withDestinationAirportCode(toughJetResponse.getArrivalAirportName())
                .withDepartureDate(departureDate)
                .withArrivalDate(arrivalDate)
                .withFare(fare)
                .build();
    }
}
