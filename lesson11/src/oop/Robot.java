package oop;

public class Robot {
    private int x = 0;
    private int y = 0;
    private double course = 0;

    public void forward(int distance) {
        x = (int) (x + distance * Math.cos(course / 180 * Math.PI));
        y = (int) (y + distance * Math.sin(course / 180 * Math.PI));
    }
    public void printCoordinates() {
        System.out.println(x + "," + y);
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

}
