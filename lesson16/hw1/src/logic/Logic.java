package logic;

import java.util.*;

public class Logic {
    public List<String> initLinkedList(List<String> listLink, int size) {
        for (int i = 0; i < size; i++) {
            listLink.add(null);
        }
        return listLink;
    }

    private int getRandomIndex(int size) {
        return new Random()
                .ints(0, size)
                .limit(1)
                .toArray()[0];
    }

    public List<String> fillTheListWithRandomValues(List<String> listLink, int size, int counter) {
        if (listLink.contains(null)) {
            int index = getRandomIndex(size);
            if (listLink.get(index) == null) {
                listLink.set(index, "li" + counter);
                counter++;
            }
            return fillTheListWithRandomValues(listLink, size, counter);
        }
        return listLink;
    }

    public List<String> addElementByThirdTask(List<String> lst, ListIterator<String> itArr, ListIterator<String> itLink) {
        while (itArr.hasPrevious() || itLink.hasNext()) {
            if (lst.size() % 2 == 0) {
                lst.listIterator().add(itArr.previous());
            } else {
                lst.listIterator().add(itLink.next());
            }
        }
        lst = reverseList(lst.listIterator(), new LinkedList<>());
        return lst;
    }

    private List<String> reverseList(ListIterator<String> it, List<String> lst) {
        while (it.hasNext()) {
            lst.listIterator().add(it.next());
        }

        return lst;
    }

    public List<String> addByFourthTask(ListIterator<String> itLink, List<String> lst, ListIterator<String> itArr) {
        while (itArr.hasPrevious() || itLink.hasNext()) {
            if (lst.size() % 3 != 0 && itLink.hasNext()) {
                lst.listIterator().add(itLink.next());
            } else {
                lst.listIterator().add(itArr.previous());
            }
        }
        lst = reverseList(lst.listIterator(), new LinkedList<>());
        return lst;
    }
}
