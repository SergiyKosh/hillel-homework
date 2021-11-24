package entity;

import java.util.Objects;
import java.util.TreeMap;

public class Product {
    private String productName;
    private Integer count;
    private TreeMap<String, Integer> products;

    public Product(String productName, Integer count) {
        Objects.requireNonNull(productName);
        Objects.requireNonNull(count);
        this.productName = productName;
        this.count = count;
        products = new TreeMap<>();
        products.put(this.productName, this.count);
    }

    public String getProductName() {
        return productName;
    }

    public Integer getCount() {
        return count;
    }
}
