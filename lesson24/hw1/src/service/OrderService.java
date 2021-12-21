package service;

import entities.Order;
import entities.Product;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService {
    public List<Order> getListWithCategory(List<Order> orders, String category) {
        return orders.stream()
                .map(order -> List.of(
                                new Order(
                                        order.getId(),
                                        order.getStatus(),
                                        order.getOrderDate(),
                                        order.getDeliveryDate(),
                                        order.getProducts().stream()
                                                .filter(el -> el.getCategory().equals(category))
                                                .collect(Collectors.toList()),
                                        order.getCustomer()
                                )
                        )
                )
                .flatMap(List::stream)
                .filter(order -> !order.getProducts().isEmpty())
                .collect(Collectors.toList());
    }

    public List<Product> getOrdersWithTierAndPeriod(
            List<Order> orders,
            LocalDate startDate,
            LocalDate endDate) {
        return orders.stream()
                .filter(order -> order.getCustomer().getTier() == 2 &&
                        order.getOrderDate().compareTo(startDate) >= 0 &&
                        order.getOrderDate().compareTo(endDate) <= 0)
                .map(order -> List.of(order.getProducts()))
                .flatMap(List::stream)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

    }

    public List<Order> getLastOrders(List<Order> orders, int count) {
        return orders.stream()
                .dropWhile(id -> id.getId() <= orders.size() - count) //Я наконец нашел применение dropWhile))
                .collect(Collectors.toList());
    }

    public List<Product> filterOrdersByDateAndGetProducts(List<Order> orders, LocalDate date) {
        return orders.stream()
                .filter(order -> order.getOrderDate().equals(date))
                .peek(System.out::println)
                .map(order -> List.of(order.getProducts()))
                .flatMap(List::stream)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<Order, Double> getOrdersAndTotalCostOfProducts(List<Order> orders) {
        return orders.stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(),
                                order -> order.getProducts().stream()
                                        .mapToDouble(Product::getPrice)
                                        .sum()
                        )
                );
    }
}
