package flowers;

import java.util.List;
import java.util.Random;

abstract public class Flowers {
    protected String name;
    protected Double cost;

    public Flowers(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public static Flowers getRandomFlowers(List<Flowers> lst) {
        return lst.get(new Random()
                .ints(0, lst.size())
                .limit(1)
                .toArray()[0]);
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ").append(cost);
        return sb.toString();
    }
}
