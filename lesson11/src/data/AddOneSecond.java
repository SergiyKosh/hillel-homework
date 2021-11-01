package data;

public class AddOneSecond {
    public static void main(String[] arg) {
//        int x = 10;
//        int a = ++x;
//        System.out.println("x=" + x);
//        System.out.println("a=" + a);
        int b = 5;
        int a = 12;
        int c = ++a - b++;
        System.out.println(c);
        int d = b++ * 2;
        System.out.println(d);
    }
}
