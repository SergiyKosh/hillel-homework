package operations;

public class LoopForWithIfThree {
    public static void main(String[] arg) {
        int count = 11;
        for (int i = 0; i < count; i++) {
            for (int k = 0; k < count - i + count - 1; k++) {
                if ((i == count - count / 2 - 1 && k < count) || (i == 0 && k == count / 2) || k == count / 2 - i || k == count / 2 + i && i < count / 2) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
