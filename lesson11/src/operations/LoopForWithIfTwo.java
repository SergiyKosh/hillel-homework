package operations;

public class LoopForWithIfTwo {
    public static void main(String[] arg) {
        int count = 10;

        for (int i = 0; i < count; i++) {
            for (int k = 0; k < i + 1; k++) {
                if (k == 0 || k == i || i == count - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
