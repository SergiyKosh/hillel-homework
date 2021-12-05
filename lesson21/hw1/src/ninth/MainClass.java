package ninth;

public class MainClass {
    public static void main(String[] args) {
        //Вывод:
        //AAA
        //BBB
        //BBB
        A a = new A();

        System.out.println(a.s); //a.s - статическая строка в классе А

        A.B b = a.new B();

        System.out.println(b.s); //b.s - строка во внутреннем классе В со значением ВВВ

        b.methodB(); //выводит ВВВ, потому что в классе В проинициализировано поле В значением ВВВ
    }
}
