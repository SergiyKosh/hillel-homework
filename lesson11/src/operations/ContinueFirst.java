package operations;

public class ContinueFirst {
    public static void main(String[] arg) {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.println("Number=" + i);
        }
    }
}
