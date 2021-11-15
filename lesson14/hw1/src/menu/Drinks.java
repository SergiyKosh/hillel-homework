package menu;

public enum Drinks {
    YES(0, "Yes", 100),
    NO(1, "No", 50);

    private final int var;
    private final String description;
    private final int price;

    Drinks(int var, String description, int price) {
        this.var = var;
        this.description = description;
        this.price = price;
    }

    public int getVar() {
        return var;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return new StringBuilder("Drinks:\n")
                .append("var: ")
                .append(var)
                .append("\ndescription: ")
                .append(description)
                .append("price: ")
                .append(price)
                .append('$').toString();
    }
}
