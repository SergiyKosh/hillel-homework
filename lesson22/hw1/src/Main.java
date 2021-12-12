import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        DataStructure dataStructure = new DataStructure<>(arr);
        Iterator it = dataStructure.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }

        String[] str = {"a", "b", "c", "d", "e"};
        DataStructure stringDS = new DataStructure<>(str);
        Iterator strIterator = stringDS.iterator();

        while(strIterator.hasNext()) {
            System.out.println(strIterator.next());
        }
    }
}
