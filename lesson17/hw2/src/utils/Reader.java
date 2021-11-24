package utils;

import entity.Customer;
import entity.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Reader {
    public Map<String, TreeMap<String, Integer>> loadCustomersList() throws IOException {
        return Files.lines(Paths.get("src/resources/customers.txt"))
                .filter(line -> !line.isEmpty())
                .map(line -> {
                    String[] str = line.split(" ");
                    return new Customer(str[0], new Product(str[1], Integer.parseInt(str[2])));
                })
                .collect(Collectors.toMap(Customer::getName,
                        x -> {
                            TreeMap<String, Integer> map = new TreeMap<>();
                            map.put(x.getProduct().getProductName(), x.getProduct().getCount());
                            return map;
                        }, (firstMap, secondMap) -> {
                            secondMap.keySet().forEach(secondMapKey ->
                                    firstMap.compute(
                                            secondMapKey,
                                            (k, v) -> Optional.ofNullable(v).orElse(0) + secondMap.get(secondMapKey)
                                    )
                            );
                            return firstMap;
                        }, TreeMap::new
                ));
    }
}
