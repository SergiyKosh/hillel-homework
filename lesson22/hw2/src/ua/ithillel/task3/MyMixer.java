package ua.ithillel.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyMixer<T> {
    private T[] arr;

    public MyMixer(T[] arr) {
        this.arr = arr;
    }

    public void shuffle() {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            indexes.add(i);
            int index = ThreadLocalRandom.current().nextInt(0, arr.length - 1);
            T temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
    }
}
