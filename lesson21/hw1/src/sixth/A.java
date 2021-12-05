package sixth;

class A {
    //код написан некорректно.
    // Вызвать нестатический метод внешнего класса во внутреннем статическом нельзя без создания объекта
    String s = "AAA";

    void methodA() {
        System.out.println(s);
    }

    static class B {
        void methodB() {
            new A().methodA();
        }
    }
}
