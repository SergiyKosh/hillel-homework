import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        menu(new MyTranslator());
    }

    private static void menu(MyTranslator translator) {
        System.out.print("Menu\n" +
                "1 - Add new word\n" +
                "2 - Translate phrase\n" +
                "-> ");

        switch (scanner.nextLine()) {
            case "1":
                System.out.print("Input english word: ");
                translator.setEnglishWord(scanner.nextLine());
                System.out.print("Input translate: ");
                translator.setRussianWord(scanner.nextLine());
                translator.addWord();
                dialog(translator);
                break;
            case "2":
                System.out.print("Input phrase: ");
                List<String> lst = Arrays
                        .stream(scanner.nextLine().split(" "))
                        .collect(Collectors.toList());
                System.out.println(translator.translate(lst, 0));
                dialog(translator);
                break;
            default:
                System.out.println("This operation does not exists! Try again!");
                menu(translator);
        }
    }

    private static void dialog(MyTranslator translator) {
        System.out.print("Do you want to continue working with app? (y/n) -> ");

        switch(scanner.nextLine().toLowerCase()) {
            case "y":
                menu(translator);
                break;
            case "n":
                break;
            default:
                System.out.println("This operation does not exists! Try again!");
                menu(translator);
        }
    }
}
