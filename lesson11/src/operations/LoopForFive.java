package operations;

public class LoopForFive {
    public static void main(String[] arg) {
        int count = 10;
        // Внешний цикл
        for (int i = 0; i < count; i++) {
            // Внутренний цикл для печати одной строки
            // Обратите внимание на выражение k < i+1
            // В нем вся хитрость треугольника
            for (int k = 0; k < i + 1; k++) {
                System.out.print("*");
            }
            // Переход на следующую строку
            System.out.println();
        }
    }
}
