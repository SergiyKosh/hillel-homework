package entities;

public class Customer {
    private final Long id;
    private final String name;
    private final Integer tier;

    public Customer(Long id, String name, Integer tier) {
        this.id = id;
        this.name = name;
        this.tier = tier;
    }

    public Integer getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return String.format(
                "id: %d\nname: %s\ntier: %d",
                id,
                name,
                tier
        );
    }
}
