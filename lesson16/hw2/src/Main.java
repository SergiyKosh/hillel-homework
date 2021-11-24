import generator.MyNumGenerator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        MyNumGenerator myNumGenerator = new MyNumGenerator(10, 10, 25);
        List<Integer> lst = myNumGenerator.generateList();
        System.out.print("List: ");
        lst.forEach(x -> System.out.print(x + " "));

        System.out.print("\nSet:  ");
        Set<Integer> set = myNumGenerator.generateSet();
        set.forEach(x -> System.out.print(x + " "));
    }
}
