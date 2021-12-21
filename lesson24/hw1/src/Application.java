import entities.Order;
import entities.Product;
import service.OrderService;
import service.ProductService;
import utils.InitLists;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws IOException {
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        InitLists initList = new InitLists();
        List<Product> productList = initList.readProductListFromFile();
        List<Order> orderList = initList.readOrderListFromFile();

        System.out.println("First task:\n----------------");
        doAndPrintFirstTask(productService, productList);
        System.out.println("Second task:\n----------------");
        doAndPrintSecondTask(orderService, orderList, "Детские");
        System.out.println("Third task:\n----------------");
        doAndPrintThirdTask(productService, productList);
        System.out.println("Fourth task:\n----------------");
        doAndPrintFourthTask(
                orderService,
                orderList,
                LocalDate.parse("2021-10-01"),
                LocalDate.parse("2021-11-01")
        );
        System.out.println("Fifth task:\n----------------");
        doAndPrintFifthTask(productService, productList, "Книги");
        System.out.println("Sixth task:\n----------------");
        doAndPrintSixthTask(orderService, orderList, 3);
        System.out.println("Seventh task:\n----------------");
        doAndPrintSeventhTask(orderService, orderList, LocalDate.parse("2021-10-01"));
        System.out.println("Eighth task:\n----------------");
        doAndPrintEightTask(orderService, orderList);
    }

    private static void doAndPrintFourthTask(
            OrderService orderService,
            List<Order> orderList,
            LocalDate startDate,
            LocalDate endDate) {
        orderService.getOrdersWithTierAndPeriod(
                        orderList,
                        startDate,
                        endDate
                )
                .forEach(System.out::println);
    }

    private static void doAndPrintFirstTask(ProductService productService, List<Product> productList) {
        productService.getProductListWithSomePredicate(
                productList,
                product -> product.getPrice() > 100 && product.getCategory().equalsIgnoreCase("Книги")
        ).forEach(System.out::println);
    }

    private static void doAndPrintThirdTask(ProductService productService, List<Product> productList) {
        productService.getProductListWithPredicatAndFunction(
                        productList,
                        product -> product.getCategory().equalsIgnoreCase("Игрушки"),
                        product -> new Product(
                                product.getId(),
                                product.getName(),
                                product.getCategory(),
                                product.getPrice() / 100 * 90
                        )
                )
                .forEach(System.out::println);
    }

    private static void doAndPrintFifthTask(
            ProductService productService,
            List<Product> productList,
            String category) {
        System.out.println(
                String.valueOf(
                                productService
                                        .getProductWithMinPriceAndCategory(productList, category)
                        )
                        .replaceAll("]", "")
                        .replaceAll("\\[", "")
                        .replaceAll("Optional", "")
        );
    }

    public static void doAndPrintSecondTask(
            OrderService orderService,
            List<Order> orderList,
            String category
    ) {
        orderService.getListWithCategory(orderList, category)
                .forEach(System.out::println);
    }

    public static void doAndPrintSixthTask(
            OrderService orderService,
            List<Order> orderList,
            int count
    ) {
        orderService.getLastOrders(orderList, count)
                .forEach(System.out::println);
    }

    public static void doAndPrintSeventhTask(
            OrderService orderService,
            List<Order> orderList,
            LocalDate orderDate
    ) {
        orderService.filterOrdersByDateAndGetProducts(orderList, orderDate)
                .forEach(System.out::println);
    }

    public static void doAndPrintEightTask(
            OrderService orderService,
            List<Order> orderList
    ) {
        orderService.getOrdersAndTotalCostOfProducts(orderList)
                .forEach((key, value) -> System.out.printf("%s\nsum = %f\n", key, value));
    }
}
