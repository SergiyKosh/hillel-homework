package operations;

public class ContinueFirst {
    public static void main(String[] arg) {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {    // число четное -
                continue;       // переходим в начало цикла
            }
            System.out.println("Number=" + i);   // оператор исполняется только для нечетных чисел
        }
    }
}
