package tenth;

class A
{
    //класс В является локальным внутри метода methodOne,
    // поэтому его объект можно создать только внутри данного метода
    void methodOne()
    {
        class B
        {
            void methodTwo()
            {
                System.out.println("Method Two");
            }
        }
    }

    void methodThree()
    {
        new B().methodTwo();
    }
}
