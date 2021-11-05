package interfaces;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MoveSquareFrame extends JFrame {
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";

    public MoveSquareFrame() {
        SquareComponent sc = new SquareComponent();
        add(sc);

        JButton btnUp = new JButton(UP);
        btnUp.setActionCommand(UP);
        btnUp.addActionListener(sc);
        add(btnUp, BorderLayout.NORTH);

        JButton btnDown = new JButton(DOWN);
        btnDown.setActionCommand(DOWN);
        btnDown.addActionListener(sc);
        add(btnDown, BorderLayout.SOUTH);

        setBounds(100, 100, 400, 400);

    }
}
