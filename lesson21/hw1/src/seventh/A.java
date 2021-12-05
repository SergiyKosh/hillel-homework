package seventh;

abstract class A
{
    //сначала будет инициализирован статический блок, потом обычный, потому сначала выведет 2, потом 1

    {
        System.out.println(1);
    }

    static
    {
        System.out.println(2);
    }
}
