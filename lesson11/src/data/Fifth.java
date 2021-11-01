package data;

/*
Пример для иллюстрации логических (булевых)
операций
*/
public class Fifth {
    public static void main(String[] arg) {
        boolean a = true;    // Объявление переменной a
        boolean b = false;
        boolean c = true;
        boolean result = a && (b || c);
        // Вывод результата на печать
        System.out.println(result);
    }
}
