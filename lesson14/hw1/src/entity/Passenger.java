package entity;

public class Passenger {
    private final String name;
    private final String lastName;
    public long passport;

    public Passenger(String name, String lastName, long passport) {
        this.name = name;
        this.lastName = lastName;
        this.passport = passport;
    }

    @Override
    public String toString() {
        return new StringBuilder("\n-------------------\n")
                .append("Passenger")
                .append("\n-------------------")
                .append("\nname: ")
                .append(name)
                .append("\nlast name: ")
                .append(lastName)
                .append("\npassport: ")
                .append(passport)
                .append("\n-------------------\n")
                .toString();
    }
}
