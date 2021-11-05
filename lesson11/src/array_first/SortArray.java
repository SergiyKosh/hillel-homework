package array_first;

public class SortArray {
    public static void main(String[] args) {
        int[] sample = {12, 56, 7, 34, 89, 43, 23, 9};
        boolean changed = true;

        while (changed) {
            changed = false;
            for (int i = 0; i < sample.length - 1; i++) {
                if (sample[i] > sample[i + 1]) {
                    int tmp = sample[i];
                    sample[i] = sample[i + 1];
                    sample[i + 1] = tmp;
                    changed = true;
                }
            }
        }
        for (int i = 0; i < sample.length; i++) {
            System.out.println(sample[i]);
        }
    }
}
