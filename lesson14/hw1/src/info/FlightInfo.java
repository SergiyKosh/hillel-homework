package info;

public class FlightInfo {
    private final int flightNumber;
    private final String departure;
    private final String destination;
    private final double time;
    private final int cost;

    public FlightInfo(int flightNumber, String departure, String destination, double time, int cost) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.time = time;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
//        final StringBuffer sb = new StringBuffer("FlightInfo{");
//        sb.append("flightNumber=").append(flightNumber);
//        sb.append(", departure='").append(departure).append('\'');
//        sb.append(", destination='").append(destination).append('\'');
//        sb.append(", time=").append(time);
//        sb.append(", cost=").append(cost);
//        sb.append('}');
//        return sb.toString();
        return new StringBuilder("-------------------\n")
                .append("Flight info")
                .append("\n-------------------")
                .append("\nflight number: ")
                .append(flightNumber)
                .append("\ndeparture: ")
                .append(departure)
                .append("\ndestination: ")
                .append(destination)
                .append("\ntime: ")
                .append(time)
                .append("\ncost: ")
                .append(getCost())
                .append('$')
                .toString();
    }
}
