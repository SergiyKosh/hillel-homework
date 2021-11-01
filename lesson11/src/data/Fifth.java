package data;

/*
Пример для иллюстрации логических (булевых)
операций
*/
public class Fifth {
    public static void main(String[] arg) {
        boolean a = true;
        boolean b = false;
        boolean c = true;
        boolean result = a && (b || c);
        System.out.println(result);
    }
}
