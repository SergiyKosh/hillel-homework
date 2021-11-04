package oop;

public class RobotManager {
    public static void main(String[] args) {
        Robot r1 = new Robot();
        Robot r2 = new Robot();
        r1.x = 99;
        r2.x = 123;
        System.out.println(r1.x);
        System.out.println(r2.x);
    }
}
