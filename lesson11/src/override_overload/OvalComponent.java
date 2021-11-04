package override_overload;

import java.awt.Graphics;
import javax.swing.JComponent;

public class OvalComponent extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawOval(5, 5, getWidth() - 10, getHeight() - 10);
    }
}
