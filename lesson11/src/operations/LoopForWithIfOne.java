package operations;

public class LoopForWithIfOne {
    public static void main(String[] arg) {
        int count = 10;

        for (int i = 0; i < count; i++) {
            for (int k = 0; k < count; k++) {
                if (k == 0 || k == count - 1 || i == 0 || i == count - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
