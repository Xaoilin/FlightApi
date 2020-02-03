package com.travix.medusa.busyflights.domain.busyflights;

import java.math.BigDecimal;
import java.util.Date;

// Java builder pattern to initialise the many variables in this class
public final class BusyFlightsResponseBuilder {

    private String airline;
    private String supplier;
    private BigDecimal fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;

    public BusyFlightsResponseBuilder withAirline(String airline) {
        this.airline = airline;
        return this;
    }

    public BusyFlightsResponseBuilder withSupplier(String supplier) {
        this.supplier = supplier;
        return this;
    }

    public BusyFlightsResponseBuilder withFare(BigDecimal fare) {
        this.fare = fare;
        return this;
    }

    public BusyFlightsResponseBuilder withDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
        return this;
    }

    public BusyFlightsResponseBuilder withDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
        return this;
    }

    public BusyFlightsResponseBuilder withDepartureDate(String departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public BusyFlightsResponseBuilder withArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public BusyFlightsResponse build() {
        return new BusyFlightsResponse(airline,  supplier, fare, departureAirportCode, destinationAirportCode, departureDate, arrivalDate);
    }
}
