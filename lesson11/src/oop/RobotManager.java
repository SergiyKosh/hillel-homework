package oop;

import interfaces.SimpleRobotListener;
import robot_visual.RobotFrame;

import javax.swing.JFrame;

public class RobotManager {
//    public static void main(String[] args) {
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

    //        Robot robot = new RobotTotal(0, 0);
//        System.out.println(robot.getClass().getName());
//    }
//
//    private void changeCourse(Robot robot) {
//        robot.setCourse(180);
//    }
//        public static void main (String[]args){
//            // Количество сторон многоугольника
//            final int COUNT = 12;
//            // Длина стороны
//            final int SIDE = 100;
//
//            Robot robot = new Robot(200, 50);
//            // Создаем замкнутую фигуру с количеством углов COUNT
//            for (int i = 0; i < COUNT; i++) {
//                robot.forward(SIDE);
//                robot.setCourse(robot.getCourse() + 360 / COUNT);
//            }
//
//            // Создаем форму для отрисовки пути нашего робота
//            RobotFrame rf = new RobotFrame(robot);
//            rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            rf.setVisible(true);
//        }
//    public static void main(String[] args) {
//        Robot[] rbts = new Robot[10];
//        for (int i = 0; i < rbts.length; i++) {
//            rbts[i] = new Robot(i * 10, i * 10);
//        }
//        for (int i = 0; i < rbts.length; i++) {
//            rbts[i].printCoordinates();
//        }
//    }
    public static void main(String[] args) {
        final int COUNT = 4;
        final int SIDE = 100;

        Robot robot = new Robot(200, 50);
        SimpleRobotListener srl = new SimpleRobotListener();
        robot.setListener(srl);
        for (int i = 0; i < COUNT; i++) {
            robot.forward(SIDE);
            robot.setCourse(robot.getCourse() + 360 / COUNT);
        }

        RobotFrame rf = new RobotFrame(robot);
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rf.setVisible(true);
    }
}
