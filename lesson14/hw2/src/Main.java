import flowers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Flowers> flowersList = List.of(
                new Chamomile("Chamomile", 15.0),
                new Lavender("Lavender", 30.0),
                new Rose("Rose", 45.0),
                new Tulip("Tulip", 20.0)
        );

        AtomicReference<Double> cost = new AtomicReference<>(0.0);

        System.out.print("Input flowers count: ");
        Flowers[] bouquet = new Flowers[Integer.parseInt(scanner.nextLine())];

        for (int i = 0; i < bouquet.length; i++) {
            Flowers fl = Flowers.getRandomFlowers(flowersList);
            bouquet[i] = fl;
        }
        Arrays.stream(bouquet)
                .forEach(System.out::println);
        Arrays.stream(bouquet)
                .forEach(x -> cost.set(cost.get() + x.getCost()));
        System.out.println("\nBouquet cost is " + cost);
    }
}
