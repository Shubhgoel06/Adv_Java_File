import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.*;

public class ThirdCalculator extends JFrame implements ActionListener {
    // Components for the calculator
    private JTextField display;
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decButton, equButton, clrButton, delButton;
    private JButton logButton, squareButton;

    // Variables for calculation
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    private DecimalFormat df = new DecimalFormat("#.#####"); // Format for display

    public ThirdCalculator() {
        // Frame settings
        setTitle("Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Times New Roman", Font.BOLD, 36)); // Bigger font size
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Border for display
        add(display, BorderLayout.NORTH);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5)); // 5 rows and 4 columns
        buttonPanel.setBackground(new Color(220, 220, 220));

        // Number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = createButton(String.valueOf(i), new Color(100, 150, 255)); // Blue color for numbers
        }

        // Operator buttons
        addButton = createButton("+", new Color(255, 165, 0)); // Orange for addition
        subButton = createButton("-", new Color(255, 165, 0)); // Orange for subtraction
        mulButton = createButton("*", new Color(255, 165, 0)); // Orange for multiplication
        divButton = createButton("/", new Color(255, 165, 0)); // Orange for division

        // Additional function buttons
        logButton = createButton("log", new Color(0, 200, 150)); // Teal for log
        squareButton = createButton("x²", new Color(0, 200, 150)); // Teal for square

        // Decimal, Equals, Clear buttons
        decButton = createButton(".", new Color(100, 150, 255)); // Blue color for decimal
        equButton = createButton("=", new Color(255, 100, 100)); // Red for equals
        clrButton = createButton("C", new Color(255, 100, 100)); // Red for clear
	delButton = createButton("DEL", new Color(255, 100, 100)); // Create the DEL button


        // Add buttons to the panel in specified layout
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(addButton);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
 	buttonPanel.add(numberButtons[7]);
        buttonPanel.add(subButton);
        buttonPanel.add(numberButtons[9]);
 	buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(divButton);
        buttonPanel.add(clrButton);
	buttonPanel.add(numberButtons[0]);
	buttonPanel.add(decButton);
       	buttonPanel.add(mulButton);
        buttonPanel.add(logButton);
        buttonPanel.add(squareButton);
 	buttonPanel.add(equButton);
	buttonPanel.add(delButton);

        // Add button panel to frame
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(color);
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Number button action
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText() + i);
            }
        }

        // Decimal point action
        if (e.getSource() == decButton) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }

        // Operator actions
        if (e.getSource() == addButton || e.getSource() == subButton || 
            e.getSource() == mulButton || e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operator = ((JButton) e.getSource()).getText().charAt(0);
            display.setText(display.getText() + operator);
        }

        // Equals action
        if (e.getSource() == equButton) {
            String[] parts = display.getText().split("\\+|\\-|\\*|\\/");
            if (parts.length < 2) return; // Ensure we have two numbers
            
            num1 = Double.parseDouble(parts[0]);
            num2 = Double.parseDouble(parts[1]);

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(df.format(result));
        }

        // Clear action
        if (e.getSource() == clrButton) {
            display.setText("");
        }
	
	 if (e.getSource() == delButton) {
        String text = display.getText();
        if (text.length() > 0) {
            display.setText(text.substring(0, text.length() - 1)); // Remove last character
        }
    }

        // Logarithm action
        if (e.getSource() == logButton) {
            num1 = Double.parseDouble(display.getText());
            result = Math.log(num1);
            display.setText(df.format(result));
        }

        // Square action
        if (e.getSource() == squareButton) {
            num1 = Double.parseDouble(display.getText());
            result = Math.pow(num1, 2);
            display.setText(df.format(result));
        }
    }

    public static void main(String[] args) {
        new ThirdCalculator();
    }
}