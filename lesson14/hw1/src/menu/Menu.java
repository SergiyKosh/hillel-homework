package menu;

public enum Menu {
    SOUP(0, "Soup", 300),
    VEGETABLES(1, "Vegetables", 30),
    MEAT(2, "Meat", 200),
    DESSERT(3, "Dessert", 100);

    private final int var;
    private final String description;
    private final int price;

    Menu(int var, String description, int price) {
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
        return new StringBuilder("Menu:\n")
                .append("var: ")
                .append(var)
                .append("\ndescription: ")
                .append(description)
                .append("\nprice: ")
                .append(price)
                .append('$').toString();
    }
}
