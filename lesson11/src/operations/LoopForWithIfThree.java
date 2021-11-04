package operations;

public class LoopForWithIfThree {
    public static void main(String[] arg) {
        int count = 11;
        int j = -1 * (count / 2) + 1;
        for (int i = 0; i < count; i++, j++) {
            for (int k = 0; k < count - i + count - 1; k++) {
                if ((i == 0 && k == count / 2) || k == count / 2 - i || k == count / 2 + i && i <= count / 2
                        || (i == count - 1 && k == count / 2) || k == i - count / 2
                        || i >= count / 2 && (k > count / 2 && i > count / 2) && k == count - j) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
