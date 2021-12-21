package service;

import entities.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductService {
    public List<Product> getProductListWithSomePredicate(
            List<Product> productList,
            Predicate<Product> predicate
    ) {
        return productList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<Product> getProductListWithPredicatAndFunction(
            List<Product> productList,
            Predicate<Product> predicate,
            Function<Product, Product> consumer
    ) {
        return productList.stream()
                .filter(predicate)
                .map(consumer)
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductWithMinPriceAndCategory(
            List<Product> productList,
            String category
    ) {
        return productList.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .min(Comparator.comparing(Product::getPrice));
    }
}
