package data;

public class LongBool {
    public static void main(String[] arg) {
        boolean a = true;
        int b = 0;

        boolean result = a | (++b > 0);
        System.out.println("B=" + b);
    }
}
