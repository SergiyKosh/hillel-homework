package second;

public class MainClass {
    //в 3 случаях вывод 3131, т.к. инкремент постфиксный
    public static void main(String[] args) {
        System.out.println(X.x);

        System.out.println(X.Y.y);

        System.out.println(X.Y.Z.z);
    }
}
