import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    
    private JTextField textField;
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";
    
    public Calculator() {
        // Create a new frame
        JFrame frame = new JFrame("Calculator");
        
        // Create text field to display input and result
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        
        // Create panel to hold buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        
        // Create buttons for digits and operators
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };
        
        // Add buttons to panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }
        
        // Set up the frame layout
        frame.setLayout(new BorderLayout());
        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        
        // Configure frame settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }
    
    // Inner class to handle button click events
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String command = source.getText();
            
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                // Append digit to text field
                textField.setText(textField.getText() + command);
            } else if (command.charAt(0) == 'C') {
                // Clear text field and reset variables
                textField.setText("");
                num1 = num2 = result = 0;
                operator = "";
            } else if (command.charAt(0) == '=') {
                // Perform calculation and display result
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            textField.setText("Error");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
                num1 = result; // Prepare for subsequent operations
                operator = "";
            } else {
                // Store operator and number
                operator = command;
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        }
    }
    
    public static void main(String[] args) {
        // Create and display the calculator
        new Calculator();
    }
}
