package operations;

public class LoopForFour {
    public static void main(String[] arg) {
        int count = 10;
        // Внешний цикл
        for(int i=0; i < count; i++) {
            // Внутренний цикл для печати одной строки
            for(int k=0; k < count; k++) {
                System.out.print("*");
            }
            // Переход на следующую строку
            System.out.println();
        }
    }
}
