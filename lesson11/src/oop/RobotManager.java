package oop;

import inheritance.RobotExt;

public class RobotManager {
    public static void main(String[] args) {
        RobotExt robot = new RobotExt(0, 0, 0);

        robot.forward(20);
        robot.printCoordinates();

        robot.setCourse(90);
        robot.forward(20);
        robot.printCoordinates();

        robot.setCourse(45);
        robot.forward(20);
        robot.printCoordinates();
        robot.back(10);
        robot.printCoordinates();
    }

    private void changeCourse(Robot robot) {
        robot.setCourse(180);
    }
}
