package operations;

public class LoopDoOne {
    public static void main(String[] arg) {
        int count = 10;
        int i = 0;
        do {
            System.out.print("*");
            i++;
        } while (i < count);
        System.out.println();
    }
}
