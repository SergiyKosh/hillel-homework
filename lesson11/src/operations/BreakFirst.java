package operations;

public class BreakFirst {
    public static void main(String[] arg) {
        int number = 1;
        int sum = 0;
        int count = 0;
        int max = 300;
        for (; ; ) {
            sum += number;
            if (sum > max) {
                break;
            } else {
                count++;
            }
            number += 10;
        }
        System.out.println("Count=" + count);
    }
}
