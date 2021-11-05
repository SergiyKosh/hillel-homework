package robot_visual;

import oop.Robot;

import javax.swing.JFrame;

public class RobotFrame extends JFrame {
    public RobotFrame(Robot robot) {
        setTitle("Robot Frame");
        add(new RobotPathComponent(robot));
        setBounds(100, 100, 500, 500);
    }
}
