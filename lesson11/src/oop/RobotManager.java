package oop;

import override_overload.RobotTotal;

public class RobotManager {
    public static void main(String[] args) {
//        RobotExt robot = new RobotExt(0, 0, 0);
//
//        robot.forward(20);
//        robot.printCoordinates();
//
//        robot.setCourse(90);
//        robot.forward(20);
//        robot.printCoordinates();
//
//        robot.setCourse(45);
//        robot.forward(20);
//        robot.printCoordinates();
//        robot.back(10);
//        robot.printCoordinates();
//        Robot robot1 = new Robot("Test Robot 1");
//        Robot robot2 = new Robot("Test Robot 2");
//        System.out.println(robot1.toString());
//        System.out.println(robot2.toString());
//        Robot robot = new RobotTotal(0, 0);
//
//        robot.forward(20);
//        robot.setCourse(90);
//        robot.forward(20);
//        robot.setCourse(90);
//        robot.forward(50);
//        // Напечатать координаты
//        robot.printCoordinates();
        Robot robot = new RobotTotal(0, 0);
        System.out.println(robot.getClass().getName());
    }

    private void changeCourse(Robot robot) {
        robot.setCourse(180);
    }
}
