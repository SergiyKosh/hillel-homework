package interfaces;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MoveSquareFrame extends JFrame {
    public static final String UP = "UP";
    public static final String DOWN = "DOWN";

    public MoveSquareFrame() {
        SquareComponent sc = new SquareComponent();
        // Кладем компонент для рисования квадрата
        add(sc);

        // Создаем кнопку для посылки команды движения вверх
        JButton btnUp = new JButton(UP);
        // Устанавливаем ей идентификатор, по которому сможем узнать эту кнопку
        btnUp.setActionCommand(UP);
        // Устанавливаем ей слушатель - компонент для рисования квадрата
        btnUp.addActionListener(sc);
        // Кладем кнопку на самый верх формы - на север
        add(btnUp, BorderLayout.NORTH);

        // Создаем кнопку для посылки команды движения вниз
        JButton btnDown = new JButton(DOWN);
        // Устанавливаем ей идентификатор, по которому сможем узнать эту кнопку
        btnDown.setActionCommand(DOWN);
        // Устанавливаем ей слушатель - компонент для рисования квадрата
        btnDown.addActionListener(sc);
        // Кладем кнопку на самый низ формы - на юг
        add(btnDown, BorderLayout.SOUTH);

        // Устанавливаем координаты
        setBounds(100, 100, 400, 400);

    }
}
