import javax.swing.*;
import java.awt.GridLayout;

public class Task1 extends JFrame {
    public Task1() {
        super("Калькулятор ІМТ");
        JTextField height = new JTextField();
        JTextField weight = new JTextField();
        JButton calculate = new JButton("Розрахувати ІМТ");
        JLabel result = new JLabel("Введіть зріст і вагу");

        setLayout(new GridLayout(3, 2, 5, 5));
        add(new JLabel("Зріст (см):"));
        add(height);
        add(new JLabel("Вага (кг):"));
        add(weight);
        add(calculate);
        add(result);

        calculate.addActionListener(e -> {
            try {
                double h = Double.parseDouble(height.getText().trim().replace(',', '.'));
                double w = Double.parseDouble(weight.getText().trim().replace(',', '.'));
                if (!(h >= 50 && h <= 250 && w >= 10 && w <= 300)) {
                    result.setText("Зріст: 50–250 см, вага: 10–300 кг");
                    return;
                }

                double bmi = w / ((h / 100) * (h / 100));
                String verdict;
                if (bmi < 18.5) verdict = "Недостатня вага";
                else if (bmi < 25) verdict = "Норма";
                else if (bmi < 30) verdict = "Надлишкова вага";
                else verdict = "Ожиріння";
                result.setText(String.format("ІМТ: %.1f — %s", bmi, verdict));
            } catch (NumberFormatException ex) {
                result.setText("Введіть числові значення");
            }
        });

        setSize(600, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task1::new);
    }
}