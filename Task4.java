import javax.swing.*;
import java.awt.*;

public class Task4 extends JFrame {
    private int x = 0, speed = 3, direction = 1;
    private final int step = 1;

    public Task4() {
        super("Рух точки");
        JPanel field = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.fillOval(x, getHeight() / 2 - 5, 10, 10);
            }
        };
        field.setPreferredSize(new Dimension(400, 200));
        add(field, BorderLayout.CENTER);

        JButton faster = new JButton("Швидше");
        JButton slower = new JButton("Повільніше");
        faster.addActionListener(e -> speed += step);
        slower.addActionListener(e -> speed = Math.max(0, speed - step));
        JPanel buttons = new JPanel();
        buttons.add(faster);
        buttons.add(slower);
        add(buttons, BorderLayout.SOUTH);

        new Timer(20, e -> {
            x += speed * direction;
            if (x <= 0 || x >= field.getWidth() - 10) {
                x = Math.max(0, Math.min(x, field.getWidth() - 10));
                direction = -direction;
            }
            field.repaint();
        }).start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task4::new);
    }
}