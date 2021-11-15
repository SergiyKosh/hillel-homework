package entity;

public class Baggage {
    private final int amount;
    private final int customsFee;

    public Baggage(int amount, int customsFee) {
        this.amount = amount;
        this.customsFee = customsFee;
    }

    public int getFee() {
        return customsFee * amount;
    }

    @Override
    public String toString() {
        return new StringBuilder("\n-------------------\n")
                .append("Baggage:")
                .append("\namount: ")
                .append(amount)
                .append("\nfee: ")
                .append(getFee())
                .append('$')
                .append("\n-------------------\n")
                .toString();
    }
}
