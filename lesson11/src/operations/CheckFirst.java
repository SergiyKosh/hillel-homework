package operations;

public class CheckFirst {
    public static void main(String[] arg) {
        int count = 10;
        int i = 0;
        // Обычный цикл while для вывода строки
        while (i++ < count) {
            System.out.print("*");
        }
        // Цикл while с пустым оператором
        while (++i < count) ;  // ; - и есть пустой оператор
        System.out.println();
    }
}
