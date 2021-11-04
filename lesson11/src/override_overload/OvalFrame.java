package override_overload;

import javax.swing.JFrame;

public class OvalFrame extends JFrame {
    public OvalFrame() {
        OvalComponent oc = new OvalComponent();
        add(oc);
        setBounds(200, 200, 300, 250);
    }
}
