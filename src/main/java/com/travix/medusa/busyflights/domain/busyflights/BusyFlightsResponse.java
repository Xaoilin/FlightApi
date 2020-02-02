package com.travix.medusa.busyflights.domain.busyflights;

import java.math.BigDecimal;
import java.util.Date;

// Java builder pattern to initialise the many variables in this class
public class BusyFlightsResponse {

    private String airline;
    private String supplier;
    private BigDecimal fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private Date departureDate;
    private Date arrivalDate;

    public BusyFlightsResponse() {
    }

    public BusyFlightsResponse setAirline(String airline) {
        this.airline = airline;
        return this;
    }

    public BusyFlightsResponse setSupplier(String supplier) {
        this.supplier = supplier;
        return this;
    }

    public BusyFlightsResponse setFare(BigDecimal fare) {
        this.fare = fare;
        return this;
    }

    public BusyFlightsResponse setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
        return this;
    }

    public BusyFlightsResponse setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode;
        return this;
    }

    public BusyFlightsResponse setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
        return this;
    }

    public BusyFlightsResponse setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
        return this;
    }

    public String getAirline() {
        return airline;
    }

    public String getSupplier() {
        return supplier;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }
}
