package operations;

public class LoopForWithIfThree {
    public static void main(String[] arg) {
        int count = 11;
        for (int i = 0; i < count; i++) {
            for (int k = 0; k < count - i + count - 1; k++) {
                if (i == count - 1 || i == count - k - 1 || k == count - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
