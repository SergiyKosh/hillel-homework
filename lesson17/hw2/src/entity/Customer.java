package entity;

import java.util.TreeMap;
import java.util.stream.Collectors;

public class Customer {
    private String name;
    private Product product;
    private static TreeMap<String, TreeMap<String, Integer>> customers = new TreeMap<>() {
        @Override
        public String toString() {
            return entrySet().stream()
                    .map(x -> x.getKey() + ":\n" + x.getValue() + "\n")
                    .collect(Collectors.joining());
        }
    };

    public Customer() {
    }

    public Customer(String name, Product product) {
        this.name = name != null ? name : "NULL";
        this.product = product != null ? product : new Product("NULL", 0);
    }

    public TreeMap<String, TreeMap<String, Integer>> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }

    public Product getProduct() {
        return product;
    }

    public TreeMap<String, Integer> getProducts() {
        return getProduct().getProducts();
    }
}
