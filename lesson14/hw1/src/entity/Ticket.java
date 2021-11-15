package entity;

import info.FlightInfo;

public class Ticket {
    private final int sitNumber;
    private final FlightInfo flightInfo;
    private final Passenger passenger;

    public Ticket(int sitNumber, FlightInfo flightInfo, Passenger passenger) {
        this.sitNumber = sitNumber;
        this.flightInfo = flightInfo;
        this.passenger = passenger;
    }

    public int getSitNumber() {
        return sitNumber;
    }

    public int getCost() {
        return this.flightInfo.getCost();
    }

    @Override
    public String toString() {
        return new StringBuilder("Ticket\n")
                .append("-------------------\n")
                .append("sit number: ")
                .append(getSitNumber())
                .append("\n")
                .append(flightInfo)
                .append(passenger)
                .toString();
    }
}
