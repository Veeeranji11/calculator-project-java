package com.calculator;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI {
    private Calculator calc;  // backend connection
    private JTextArea historyArea;
    private JTextField inputField;

    public CalculatorGUI() {
        calc = new Calculator(); // connect backend

        JFrame frame = new JFrame("Calculator");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        inputField = new JTextField(10);
        historyArea = new JTextArea(12, 40);
        historyArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.add(inputField);

        // Buttons
        JButton addBtn = new JButton("+");
        JButton subBtn = new JButton("-");
        JButton mulBtn = new JButton("*");
        JButton divBtn = new JButton("/");
        JButton showBtn = new JButton("Show History");
        JButton saveBtn = new JButton("Save History");
        JButton quitBtn = new JButton("Quit");

        // Add buttons to panel
        panel.add(addBtn);
        panel.add(subBtn);
        panel.add(mulBtn);
        panel.add(divBtn);
        panel.add(showBtn);
        panel.add(saveBtn);
        panel.add(quitBtn);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(new JScrollPane(historyArea), BorderLayout.CENTER);

        // Button actions call backend methods
        addBtn.addActionListener(e -> calculate('+'));
        subBtn.addActionListener(e -> calculate('-'));
        mulBtn.addActionListener(e -> calculate('*'));
        divBtn.addActionListener(e -> calculate('/'));
        showBtn.addActionListener(e -> updateHistory());
        saveBtn.addActionListener(e -> calc.saveHistoryToFile());
        quitBtn.addActionListener(e -> System.exit(0));

        // Keyboard shortcuts: + - * /
        inputField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                char key = evt.getKeyChar();
                if (key == '+' || key == '-' || key == '*' || key == '/') {
                    calculate(key);
                }
            }
        });

        frame.setVisible(true);
    }

    private void calculate(char op) {
        try {
            double num = Double.parseDouble(inputField.getText());
            switch (op) {
                case '+': calc.add(num); break;
                case '-': calc.subtract(num); break;
                case '*': calc.multiply(num); break;
                case '/': calc.divide(num); break;
            }
            updateHistory();
            inputField.setText("");  // clear for next number
        } catch (NumberFormatException ex) {
            historyArea.append("Invalid input!\n");
            inputField.setText("");  // clear if invalid
        }
    }

    private void updateHistory() {
        historyArea.setText("");
        for (String h : calc.getHistory()) {  // use backend getter
            historyArea.append(h + "\n");
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
