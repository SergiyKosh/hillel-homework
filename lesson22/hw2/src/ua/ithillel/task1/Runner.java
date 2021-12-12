package ua.ithillel.task1;

public class Runner {
    public void run() {
        Integer[] intArr = {2, 3, 4, 5, 6, 7, 8, 9};
        Double[] doubleArr = {2.5, 1.3, 4.5, 7.8, 9.0};
        System.out.println(MyTestMethod.calcNum(intArr, 4));
        System.out.println(MyTestMethod.calcNum(doubleArr, 2.5));
    }
}
