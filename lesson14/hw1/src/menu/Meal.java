package menu;

public class Meal {
    private final Menu menu;
    private final Drinks drinks;

    public Meal(Menu menu, Drinks drinks) {
        this.menu = menu;
        this.drinks = drinks;
    }

    public int getPrice() {
        return menu.getPrice() + drinks.getPrice();
    }

    public Menu getMenu() {
        return menu;
    }

    public Drinks getDrinks() {
        return drinks;
    }

    @Override
    public String toString() {
        return new StringBuilder("Meal")
                .append("\n-------------------\n")
                .append(getMenu())
                .append("\n-------------------\n")
                .append(getDrinks())
                .append("\n-------------------\n")
                .toString();
    }
}
