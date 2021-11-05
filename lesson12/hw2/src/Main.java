import entity.Animal;
import factory.AnimalFactory;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Input animal name: ");
        String name = scanner.nextLine();
        Animal an = AnimalFactory.getAnimalByKey(name);
        if (an == null) {
            System.out.println("Не могу создать Animal");
            main(new String[0]);
        } else {
            System.out.println(an.getName());
        }
    }
}
