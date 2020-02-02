package com.travix.medusa.busyflights.domain.busyflights;

import java.time.LocalDate;

public class BusyFlightsRequest {

    private String origin;
    private String destination;
    private String departureDate; // Refactor to LocalDate for more accurate variable use
    private String returnDate;
    private int numberOfPassengers;

    // Add constructor to enable easy initialisation of request
    public BusyFlightsRequest(String origin, String destination, String departureDate, String returnDate, int numberOfPassengers) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberOfPassengers = numberOfPassengers;
    }

    public BusyFlightsRequest() {
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(final String destination) {
        this.destination = destination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(final String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final String returnDate) {
        this.returnDate = returnDate;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(final int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    // override to string method to print the objects variables visually as opposed to the default memory address implementation
    @Override
    public String toString() {
        return "BusyFlightsRequest{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", numberOfPassengers=" + numberOfPassengers +
                '}';
    }
}
