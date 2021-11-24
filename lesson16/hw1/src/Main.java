import logic.Logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logic logic = new Logic();
        ArrayList<String> listArray = new ArrayList<>(List.of("ar0", "ar1", "ar2", "ar3", "ar4", "ar5", "ar6", "ar7", "ar8", "ar9"));
        List<String> listLink = logic.initLinkedList(new LinkedList<>(), 10);
        listLink = logic.fillTheListWithRandomValues(listLink, listLink.size(), 0);

        System.out.print("ArrayList: ");
        listArray.forEach(x -> System.out.print(x + " "));
        System.out.print("\nLinkedList: ");
        listLink.forEach(x -> System.out.print(x + " "));
        System.out.print("\nArrayList + LinkedList: ");

        logic.addElementByThirdTask(new LinkedList<>(),
                        listArray.listIterator(listArray.size()),
                        listLink.listIterator())
                .forEach(x -> System.out.print(x + " "));

        List<String> lst = new LinkedList<>();
        lst = logic.addByFourthTask(listLink.listIterator(), lst, listArray.listIterator(listArray.size()));

        System.out.print("\nArrayList + LinkedList 2: ");
        lst.forEach(x -> System.out.print(x + " "));
    }
}
