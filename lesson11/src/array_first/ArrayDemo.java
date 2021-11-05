package array_first;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] demo = new int[10];
        for (int i = 0; i < demo.length; i++) {
            System.out.println(demo[i]);
            demo[i] = 10 * (i + 1);
        }
        for (int i = 0; i < demo.length; i++) {
            System.out.println(demo[i]);
        }
    }
}
