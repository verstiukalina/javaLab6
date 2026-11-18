import javax.swing.*;
import java.awt.*;

public class Task2 extends JFrame {
    public Task2() {
        super("Бронювання номера");
        JTextField name = new JTextField();
        JComboBox<String> room = new JComboBox<>(new String[]{"Стандарт", "Люкс"});
        JCheckBox breakfast = new JCheckBox("Сніданок");
        JCheckBox transfer = new JCheckBox("Трансфер");
        JCheckBox parking = new JCheckBox("Парковка");
        JRadioButton cash = new JRadioButton("Готівка", true);
        JRadioButton card = new JRadioButton("Картка");
        ButtonGroup payment = new ButtonGroup();
        payment.add(cash);
        payment.add(card);

        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));
        form.add(new JLabel("ПІБ гостя:"));
        form.add(name);
        form.add(new JLabel("Тип номера:"));
        form.add(room);
        form.add(breakfast);
        form.add(transfer);
        form.add(parking);
        form.add(new JLabel());
        form.add(cash);
        form.add(card);
        add(form, BorderLayout.CENTER);

        JButton book = new JButton("Забронювати");
        JButton reset = new JButton("Скинути");
        JPanel buttons = new JPanel();
        buttons.add(book);
        buttons.add(reset);
        add(buttons, BorderLayout.SOUTH);

        book.addActionListener(e -> {
            if (name.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Введіть ПІБ гостя");
                return;
            }
            JOptionPane.showMessageDialog(this,
                    "Гість: " + name.getText().trim() + "\nНомер: " + room.getSelectedItem()
                    + "\nСніданок: " + (breakfast.isSelected() ? "так" : "ні")
                    + "\nТрансфер: " + (transfer.isSelected() ? "так" : "ні")
                    + "\nПарковка: " + (parking.isSelected() ? "так" : "ні")
                    + "\nОплата: " + (cash.isSelected() ? "готівка" : "картка"),
                    "Підсумок бронювання", JOptionPane.INFORMATION_MESSAGE);
        });
        reset.addActionListener(e -> {
            name.setText("");
            room.setSelectedIndex(0);
            breakfast.setSelected(false);
            transfer.setSelected(false);
            parking.setSelected(false);
            cash.setSelected(true);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task2::new);
    }
}