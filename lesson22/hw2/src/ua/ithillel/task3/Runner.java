package ua.ithillel.task3;

import java.util.Arrays;

public class Runner {
    public void run() {
        Integer[] intArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MyMixer<Integer> mixInt = new MyMixer<>(intArr);
        mixInt.shuffle();
        Arrays.stream(intArr).forEach(System.out::println);

        System.out.println();

        Double[] doubleArr = {2.5, 4.3, 1.2, 3.4, 7.6, 9.8, 0.3};
        MyMixer<Double> mixDouble = new MyMixer<>(doubleArr);
        mixDouble.shuffle();
        Arrays.stream(doubleArr).forEach(System.out::println);
    }
}
