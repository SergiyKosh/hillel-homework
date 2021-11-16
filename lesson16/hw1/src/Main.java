import logic.Logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logic logic = new Logic();
        ArrayList<String> listArray = new ArrayList<>(List.of("ar0", "ar1", "ar2", "ar3", "ar4", "ar5", "ar6", "ar7", "ar8", "ar9"));
        LinkedList<String> listLink = logic.initLinkedList(new LinkedList<>(), 10);
        listLink = logic.fillTheListWithRandomValues(listLink, listLink.size(), 0, 0);

        System.out.print("ArrayList: ");
        listArray.forEach(x -> System.out.print(x + " "));
        System.out.print("\nLinkedList: ");
        listLink.forEach(x -> System.out.print(x + " "));
        System.out.print("\nArrayList + LinkedList: ");

        logic.addElementByThirdTask(new LinkedList<>(listLink),
                new ArrayList<>(listArray),
                listArray.size(),
                listArray.listIterator(listArray.size()),
                0)
                .forEach(x -> System.out.print(x + " "));

        List<String> lst = logic.initLinkedList(new LinkedList<>(), listArray.size() + listLink.size());
        lst = logic.addLinkedListByFourthTask(listLink.listIterator(0), lst, 0);
        lst = logic.addArrayListByFourthTask(listArray.listIterator(listArray.size()), lst, 0);

        System.out.print("\nArrayList + LinkedList 2: ");
        lst.forEach(x -> System.out.print(x + " "));

    }
}
