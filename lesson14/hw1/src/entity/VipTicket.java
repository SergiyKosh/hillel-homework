package entity;

import info.FlightInfo;
import menu.Meal;

public class VipTicket extends Ticket {
    private Meal meal;
    private Baggage baggage;

    public VipTicket(int sitNumber, FlightInfo flightInfo, Passenger passenger, Meal meal, Baggage baggage) {
        super(sitNumber, flightInfo, passenger);
        this.meal = meal;
        this.baggage = baggage;
    }

    public int getCost() {
        return meal.getPrice() + super.getCost();
    }

    @Override
    public String toString() {
        return new StringBuilder("Vip ticket:\n")
                .append("-------------------\n")
                .append("sit number: ")
                .append(getSitNumber())
                .append("\n-------------------\n")
                .append(meal)
                .append("total price: ")
                .append(meal.getPrice())
                .append('$')
                .append(baggage)
                .append("total price: ")
                .append(getCost())
                .append('$').append("\n-------------------").toString();
    }
}
