package utils;

import entity.Customer;
import entity.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Reader {
    public Map<String, TreeMap<String, Integer>> loadCustomersList() throws IOException {
        Map<String, TreeMap<String, Integer>> map = new TreeMap<>();

        Files.lines(Paths.get("src/resources/customers.txt"))
                .forEach(x -> {
                            if (!x.equals("")) {
                                String[] line = x.split(" ");
                                Customer customer = new Customer(line[0], new Product(line[1], Integer.parseInt(line[2])));
                                putToMap(customer);
                            }
                        }
                );
        return map;
    }

    private void putToMap(Customer customer) {
        Product product = customer.getProduct();
        TreeMap<String, TreeMap<String, Integer>> customers = customer.getCustomers();
        TreeMap<String, Integer> products = customer.getProducts();
        String name = customer.getName();
        Integer count = product.getCount();
        String productName = product.getProductName();

        if (!customers.containsKey(name)) {
            customers.put(name, products);
        } else if (!customers.get(name).containsKey(productName)) {
            TreeMap<String, Integer> tmpMap = customers.get(name);
            tmpMap.put(productName, count);
            customers.replace(name, tmpMap);
        } else {
            count = customers.get(name).get(productName);
            Integer tmpCount = products.get(productName);
            Integer res = tmpCount + count;
            customers.get(name).replace(productName, res);
        }
    }

}
