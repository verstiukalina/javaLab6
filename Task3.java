import javax.swing.*;
import javax.swing.table.*;
import java.awt.BorderLayout;

public class Task3 extends JFrame {
    public Task3() {
        super("Журнал успішності студентів");
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Студент", "Предмет", "Оцінка 1", "Оцінка 2", "Середній бал"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return column != 4;
            }

            public Class<?> getColumnClass(int column) {
                return column < 2 ? String.class : Double.class;
            }

            public Object getValueAt(int row, int column) {
                if (column == 4)
                    return ((Double) super.getValueAt(row, 2)
                            + (Double) super.getValueAt(row, 3)) / 2;
                return super.getValueAt(row, column);
            }

            public void setValueAt(Object value, int row, int column) {
                if (column == 2 || column == 3) {
                    if (!(value instanceof Double) || !((Double) value >= 0 && (Double) value <= 100)) {
                        JOptionPane.showMessageDialog(Task3.this, "Введіть оцінку від 0 до 100");
                        return;
                    }
                }
                super.setValueAt(value, row, column);
                fireTableRowsUpdated(row, row);
            }
        };
        JTable table = new JTable(model);
        table.putClientProperty("terminateEditOnFocusLost", true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        sorter.setSortsOnUpdates(true);
        table.setRowSorter(sorter);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Перегляд");
        JCheckBoxMenuItem excellent = new JCheckBoxMenuItem("Показати відмінників");
        excellent.addActionListener(e -> sorter.setRowFilter(excellent.isSelected()
                ? new RowFilter<DefaultTableModel, Integer>() {
                    public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                        return (Double) entry.getValue(4) >= 90;
                    }
                } : null));
        menu.add(excellent);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        JButton add = new JButton("Додати студента");
        JButton delete = new JButton("Видалити студента");
        add.addActionListener(e -> model.addRow(new Object[]{"Новий студент", "Предмет", 0.0, 0.0, null}));
        delete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) model.removeRow(table.convertRowIndexToModel(row));
        });
        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(delete);
        add(buttons, BorderLayout.SOUTH);

        setSize(650, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Task3::new);
    }
}