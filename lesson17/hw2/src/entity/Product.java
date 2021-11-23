package entity;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class Product {
    private String productName;
    private Integer count;
    private TreeMap<String, Integer> products;

    public Product(String productName, Integer count) {
        this.productName = productName != null ? productName : "NULL";
        this.count = count != null ? count : 0;
        products = new TreeMap<>() {
            @Override
            public String toString() {
                return entrySet().stream()
                        .map(x -> x.getKey() + " " + x.getValue() + "\n")
                        .collect(Collectors.joining());
            }
        };
        products.put(this.productName, this.count);
    }

    TreeMap<String, Integer> getProducts() {
        return products;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getCount() {
        return count;
    }
}
