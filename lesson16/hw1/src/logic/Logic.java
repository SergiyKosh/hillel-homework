package logic;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Logic {
    public LinkedList<String> initLinkedList(List<String> listLink, int size) {
        for (int i = 0; i < size; i++) {
            listLink.add(null);
        }
        return (LinkedList<String>) listLink;
    }

    private int getRandomIndex(int size) {
        return new Random()
                .ints(0, size)
                .limit(1)
                .toArray()[0];
    }

    public LinkedList<String> fillTheListWithRandomValues(List<String> listLink, int size, int counter) {
        if (listLink.contains(null)) {
            int index = getRandomIndex(size);
            if (listLink.get(index) == null) {
                listLink.set(index, "li" + counter);
                counter++;
            }
            return fillTheListWithRandomValues(listLink, size, counter);
        }
        return (LinkedList<String>) listLink;
    }

    public LinkedList<String> addElementByThirdTask(List<String> listLink, List<String> listArray, int size, ListIterator listIterator, int counter) {
        if (listIterator.hasPrevious()) {
            listLink.add(counter, (String) listIterator.previous());
            counter += 2;
            return addElementByThirdTask(listLink, listArray, listArray.size(), listIterator, counter);
        }
        return (LinkedList<String>) listLink;
    }

    public LinkedList<String> addArrayListByFourthTask(ListIterator listIterator, List<String> lst, int counter) {
        if (listIterator.hasPrevious()) {
            if (lst.get(counter) == null)
                lst.set(counter, (String) listIterator.previous());
            counter++;
            return addArrayListByFourthTask(listIterator, lst, counter);
        }
        return (LinkedList<String>) lst;
    }

    public LinkedList<String> addLinkedListByFourthTask(ListIterator listIterator, List<String> lst, int counter) {
        if (listIterator.hasNext()) {
            if (counter % 3 != 0) {
                lst.set(counter, (String) listIterator.next());
            }
            counter++;
            return addLinkedListByFourthTask(listIterator, lst, counter);
        }
        return (LinkedList<String>) lst;
    }
}
