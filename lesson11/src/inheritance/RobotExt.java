package inheritance;

import oop.Robot;

public class RobotExt extends Robot {
    public RobotExt(double x, double y) {
        super(x, y);
    }

    public void back(int distance) {
        forward(-distance);
    }
}
