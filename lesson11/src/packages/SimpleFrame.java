package packages;

import javax.swing.JFrame;

public class SimpleFrame {
    public static void main(String[] args) {
        JFrame sf = new JFrame();
        sf.setTitle("First window");
        sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sf.setBounds(100,100,400,200);
        sf.setVisible(true);
    }
}
