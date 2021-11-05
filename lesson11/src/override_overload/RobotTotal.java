package override_overload;

import oop.Robot;

public class RobotTotal extends Robot {
    //    private double totalDistance = 0;
//    public RobotTotal(double x, double y) {
//        super(x, y);
//    }
//
//    @Override
//    public void forward(int distance) {
//        super.forward(distance);
//        totalDistance += distance;
//    }
//
//    public double getTotalDistance() {
//        return totalDistance;
//    }
    private double totalDistance = 0;

    public RobotTotal() {
        super(0, 0);
    }

    public RobotTotal(double x, double y) {
        super(x, y);
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    @Override
    public void forward(int distance) {
        super.forward(distance);
        totalDistance += distance;
        System.out.println("RobotTotal");
    }
}
