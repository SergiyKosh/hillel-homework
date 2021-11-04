package operations;

public class BreakFirst {
    public static void main(String[] arg) {
        int number = 1;   // Начальное число
        int sum = 0;      // Переменная для хранения суммы
        int count = 0;    // Количество чисел - в начале ни одного
        int max = 300;    // Предел суммы
        for (; ; ) {
            sum += number;    // Увеличиваем сумму
            if (sum > 300) {   // Проверяем сумму - если больше
                break;            // То эта точка является точкой выхода
            } else {
                count++;          // А здесь просто увеличиваем количество чисел
            }
            number += 10;    // Увеличиваем число на 10
        }
        System.out.println("Count=" + count);
    }
}
