package oop;

public class Robot {
    // Текущая координата X
    int x = 0;
    // Текущая координата Y
    int y = 0;
    // Текущий курс (в градусах)
    int course = 0;

    void printCoordinates() {
        System.out.println(x + "," + y);
    }

    void forward(int distance) {
        x = (int) (x + distance * Math.cos(course / 180 * Math.PI));
        y = (int) (y + distance * Math.sin(course / 180 * Math.PI));
    }
}
