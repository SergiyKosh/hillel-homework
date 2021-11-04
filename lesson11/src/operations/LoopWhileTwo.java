package operations;

public class LoopWhileTwo {
    public static void main(String[] arg) {
        int count = 10;
        int i = 0;
        // Именно так - i++. Если так: ++i, то символов будет меньше
        // Почему - догадайтесь сами
        while (i++ < count) {
            System.out.print(i);
        }
        System.out.println();

    }
}
