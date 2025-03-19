package Library;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class GUI {
    protected static JTextField createTextField(int x, int y, JPanel panel) {
        JTextField textField = new JTextField(20);
        textField.setBounds(x, y, 300, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        panel.add(textField);
        return textField;
    }

    protected static void createLabel(String text,int x, int y, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(label);
    }

    protected static JButton createButton(String text, int x, int y, int w, int h,JPanel panel) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(button);
        return button;
    }

    protected static JButton createButtonBack(JFrame frame){
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);
        Back.setBackground(Color.RED);
        Back.setBounds(10, 10, 80, 30);
        frame.add(Back);
        return Back;
    }
    protected static void createTitle(JFrame frame, int x , int y, int w, String title){ {
        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(x, y, w, 40);
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);
        frame.add(panelTitle);
    }}

    protected static JPanel createInputPanel(JFrame frame, int x, int y, int w, int h){
        JPanel panelInput = new JPanel();
        panelInput.setBounds(x, y, w, h);
        panelInput.setLayout(null);
        frame.add(panelInput);
        return panelInput;
    }

    protected static JFrame createFrame(String title, int w, int h){
        JFrame frame = new JFrame(title);
        frame.setSize(w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        return frame;
    }

    protected static JTable createTable(JFrame frame, DefaultTableModel model, int x, int y, int w, int h) {
        // Create JTable with model
        JTable table = new JTable(model);

        // Set font and row height
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);

        // Set header font and background color
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);

        // Set grid color and selection background
        table.setGridColor(Color.GRAY);
        table.setSelectionBackground(Color.BLUE);
        table.setSelectionForeground(Color.WHITE);

        // Remove column reordering
        header.setReorderingAllowed(false);

        // Add table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(x, y, w, h);

        // Create JFrame to display table
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add scroll pane to frame
        frame.add(scrollPane, BorderLayout.CENTER);

        return table;
    }

    protected static JDialog createdialog(int w, int h){
        JDialog dialog = new JDialog();
        dialog.setSize(w, h);
        dialog.setLayout(null);
        return dialog;
    }

    protected static JTextField createTextFieldDialog(int x, int y, JPanel panel) {
        JTextField textField = new JTextField(20);
        textField.setBounds(x, y, 300, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(0, 10, 0, 10)));
        panel.add(textField);
        return textField;
    }

    protected static void createLabelDialog(String text,int x, int y, JPanel panel) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        label.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(label);
    }

    protected static JButton createButtonDialog(String text, int x, int y, int w, int h,JPanel panel) {
        JButton button = new JButton(text);
        button.setBounds(x, y, w, h);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(button);
        return button;
    }

    protected static JButton createButtonBackDialog(JDialog dialog){
        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.BOLD, 15));
        Back.setForeground(Color.WHITE);
        Back.setBackground(Color.RED);
        Back.setBounds(10, 10, 80, 30);
        dialog.add(Back);
        return Back;
    }
    protected static void createTitleDialog(JDialog dialog, int x , int y, int w, String title){ {
        JPanel panelTitle = new JPanel();
        panelTitle.setBounds(x, y, w, 40);
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        panelTitle.add(label);
        dialog.add(panelTitle);
    }}

    protected static JPanel createInputPanelDialog(JDialog dialog, int x, int y, int w, int h){
        JPanel panelInput = new JPanel();
        panelInput.setBounds(x, y, w, h);
        panelInput.setLayout(null);
        dialog.add(panelInput);
        return panelInput;
    }
}
