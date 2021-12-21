package utils;

import entities.Customer;
import entities.Order;
import entities.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utils.Constants.PATH_TO_ORDERS;
import static utils.Constants.PATH_TO_PRODUCTS;

public class InitLists {
    public List<Product> readProductListFromFile() throws IOException {
        return Files.lines(Paths.get(PATH_TO_PRODUCTS))
                .map(line -> line.split(","))
                .map(lines -> new Product(
                                Long.parseLong(lines[0]),
                                lines[1],
                                lines[2],
                                Double.parseDouble(lines[3])
                        )
                )
                .collect(Collectors.toList());
    }

    public List<Order> readOrderListFromFile() throws IOException {
        return Files.lines(Paths.get(PATH_TO_ORDERS))
                .map(line -> line.split(","))
                .map(lines -> new Order(
                                Long.parseLong(lines[0]),
                                lines[1],
                                LocalDate.parse(lines[2]),
                                LocalDate.parse(lines[3]),
                                Arrays.stream(lines[4].split(";"))
                                        .collect(Collectors.toList()).stream()
                                        .map(line -> line
                                                .replaceAll("]", "")
                                                .replaceAll("\\[", "")
                                                .split("\\?")
                                        )
                                        .map(line -> new Product(
                                                        Long.parseLong(line[0]),
                                                        line[1],
                                                        line[2],
                                                        Double.parseDouble(line[3])
                                                )
                                        )
                                        .collect(Collectors.toList()),
                                new Customer(
                                        Long.parseLong(
                                                lines[5]
                                                        .replaceAll("\\[", "")
                                                        .replaceAll("]", "")
                                                        .split("=")[0]),
                                        lines[5].split("=")[1],
                                        Integer.parseInt(
                                                lines[5]
                                                        .replaceAll("\\[", "")
                                                        .replaceAll("]", "")
                                                        .split("=")[2])
                                )
                        )
                )
                .collect(Collectors.toList());
    }
}
