package operations;

public class LoopForFour {
    public static void main(String[] arg) {
        int count = 10;

        for (int i = 0; i < count; i++) {
            for (int k = 0; k < count; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
