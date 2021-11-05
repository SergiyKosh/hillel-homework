package array_first;

public class ForEachExample {
    public static void main(String[] args) {
//        int[] sample = {12, 56, 7, 34, 89, 43, 23, 9};
//
//        for (int x : sample) {
//            System.out.println(x);
//        }

        int[] sample = new int[5];

        System.out.println("До foreach");

        for (int t : sample) {
            System.out.println(t);
        }

        for (int t : sample) {
            t = 99;
        }

        System.out.println("После foreach");

        for (int t : sample) {
            System.out.println(t);
        }
    }
}
