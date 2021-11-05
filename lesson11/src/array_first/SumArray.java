package array_first;

public class SumArray {
    public static void main(String[] args) {
        int[] sample = {12, 56, 7, 34, 89, 43, 23, 9};
        int summa = 0;

        for (int i = 0; i < sample.length; i++) {
            summa += sample[i];
        }

        System.out.println("TOTAL:" + summa);
    }
}
