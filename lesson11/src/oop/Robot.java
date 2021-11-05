package oop;

import interfaces.RobotListener;
import relations.Operator;
import robot_visual.RobotLine;

import java.util.ArrayList;

public class Robot {
    //    private double x = 0;
//    private double y = 0;
//    protected double course = 0;
//    private String name;
//
//    public Robot(String name) {
//        this.name = name;
//    }
//
//    public void forward(int distance) {
//        x = x + distance * Math.cos(course / 180 * Math.PI);
//        y = y + distance * Math.sin(course / 180 * Math.PI);
//    }
//
//    public Robot(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public void printCoordinates() {
//        System.out.println(x + "," + y);
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public double getCourse() {
//        return course;
//    }
//
//    public void setCourse(double course) {
//        this.course = course;
//    }
//
//    @Override
//    public String toString() {
//        return "name: " + name;
//    }
//    private double x = 0;
//    private double y = 0;
//    protected double course = 0;
//
//    public Robot(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public void forward(int distance) {
//        x = x + distance * Math.cos(course / 180 * Math.PI);
//        y = y + distance * Math.sin(course / 180 * Math.PI);
//    }
//
//    public void printCoordinates() {
//        System.out.println(x + "," + y);
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public double getCourse() {
//        return course;
//    }
//
//    public void setCourse(double course) {
//        this.course = course;
//    }

//    private double x = 0;
//    private double y = 0;
//    protected double course = 0;
//    private Operator operator;
//
//    public Robot(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public Operator getOperator() {
//        return operator;
//    }
//
//    public void setOperator(Operator operator) {
//        this.operator = operator;
//    }
//
//    public void forward(int distance) {
//        x = x + distance * Math.cos(course / 180 * Math.PI);
//        y = y + distance * Math.sin(course / 180 * Math.PI);
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public double getCourse() {
//        return course;
//    }
//
//    public void setCourse(double course) {
//        this.course = course;
//    }
//
//    public void printCoordinates() {
//        System.out.println(x + "," + y);
//    }

    //    private double x = 0;
//    private double y = 0;
//    protected double course = 0;
//    private ArrayList<RobotLine> lines = new ArrayList<RobotLine>();
//
//    public Robot(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public void forward(int distance) {
//        final double xOld = x;
//        final double yOld = y;
//        x += distance * Math.cos(course / 180 * Math.PI);
//        y += distance * Math.sin(course / 180 * Math.PI);
//        lines.add(new RobotLine(xOld, yOld, x, y));
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public double getCourse() {
//        return course;
//    }
//
//    public void setCourse(double course) {
//        this.course = course;
//    }
//
//    public ArrayList<RobotLine> getLines() {
//        return lines;
//    }
//    private double x = 0;
//    private double y = 0;
//    protected double course = 0;
//
//    public Robot(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public void forward(int distance) {
//        x = x + distance * Math.cos(course / 180 * Math.PI);
//        y = y + distance * Math.sin(course / 180 * Math.PI);
//    }
//
//    public void printCoordinates() {
//        System.out.println(x + "," + y);
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public double getCourse() {
//        return course;
//    }
//
//    public void setCourse(double course) {
//        this.course = course;
//    }

    private double x = 0;
    private double y = 0;
    protected double course = 0;
    private ArrayList<RobotLine> lines = new ArrayList<RobotLine>();
    // Ссылка на слушателя событий от робота
    // Обратите внимание, что это ссылка на ИНТЕРФЕЙС
    private RobotListener listener;

    public Robot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Метод для установки реального слушателя.
    public void setListener(RobotListener listener) {
        this.listener = listener;
    }

    public void forward(int distance) {
        // Вызываем слушателя (если он установлен) в начале
        if(listener !=null) {
            listener.startMove(x, y);
        }
        // Запоминаем координаты робота перед перемещением
        final double xOld = x;
        final double yOld = y;
        // Меняем координаты
        x += distance * Math.cos(course / 180 * Math.PI);
        y += distance * Math.sin(course / 180 * Math.PI);

        // Вызываем слушателя (если он установлен) после остановки
        if(listener !=null) {
            listener.endMove(x, y);
        }

        // Запоминаем координаты пройденного пути в списке
        // Класс List позволяет добавить объект и хранить его
        lines.add(new RobotLine(xOld, yOld, x, y));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public ArrayList<RobotLine> getLines() {
        return lines;
    }
}
