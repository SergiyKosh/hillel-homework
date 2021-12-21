package entities;

import java.util.Objects;

public class Product {
    private final Long id;
    private final String name;
    private final String category;
    private final Double price;

    public Product(Long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) && name.equals(product.name) && category.equals(product.category) && price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price);
    }

    @Override
    public String toString() {
        return String.format(
                "id: %d\nname: %s\ncategory: %s\nprice: %f UAH\n",
                id,
                name,
                category,
                price
        );
    }
}
