package inheritance;

import oop.Robot;

public class RobotExt extends Robot {
    public RobotExt(double x, double y, double course) {
        super(x, y);
        setCourse(course);
    }

    public void back(int distance) {
        forward(-distance);
    }
}
