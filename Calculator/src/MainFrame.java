import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    JTextField textField = new JTextField();

    JButton buttonRemove = new JButton("<<");

    Color frameBackground = new Color( 230, 230, 250);
    Color textFieldBackground = new Color( 216, 191, 216);
    Color buttonBackground = new Color(216, 191, 216);
    Color buttonBorderHover = new Color(224, 165, 224, 150);
    Color border = new Color(133, 118, 133);

    public MainFrame(){
        Image iconImage = Toolkit.getDefaultToolkit().getImage("iconCalculator.png");
        this.setIconImage(iconImage);
        this.setTitle("Calculator");


        this.setSize(415, 550);
        this.getContentPane().setBackground(frameBackground);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

        this.add(textField);
        textField.setBounds(25, 20, 250, 80);
        textField.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        textField.setForeground(Color.DARK_GRAY);
        textField.setBackground(textFieldBackground);
        textField.setBorder(BorderFactory.createLineBorder(border, 2));

        this.add(buttonRemove);
        buttonRemove.setBounds(285, 20, 90, 80);
        buttonRemove.setFocusPainted(false);
        buttonRemove.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        buttonRemove.setForeground(Color.DARK_GRAY); 
        buttonRemove.setBackground(buttonBackground);
        buttonRemove.setBorder(BorderFactory.createLineBorder(border, 2));

        buttonRemove.addActionListener(
                (e) -> CalculatorManager('<')
        );

        buttonRemove.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                buttonRemove.setBorder(BorderFactory.createLineBorder(buttonBorderHover, 2));
            }

            @Override
            public void mouseExited(MouseEvent e){
                buttonRemove.setBorder(BorderFactory.createLineBorder(border, 2));
            }
        });

        String[] buttonTexts = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        int x = 25, y = 120;
        int buttonHeight = 80, buttonWeight = 80;
        int buttonPadding = 10;

        for (int i = 0; i <= buttonTexts.length; i++) {
            JButton button = new JButton(buttonTexts[i]);
            this.add(button);
            button.setBounds(x, y, buttonWeight, buttonHeight);
            button.setFocusPainted(false);
            button.setFont(new Font(Font.SANS_SERIF,Font.BOLD, 20));
            button.setBackground(buttonBackground);
            button.setForeground(Color.DARK_GRAY);
            button.setBorder(BorderFactory.createLineBorder(border, 2));

            // hover
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e){
                    button.setBorder(BorderFactory.createLineBorder(buttonBorderHover, 2));
                }

                // Reset the border to its default style.
                @Override
                public void mouseExited(MouseEvent e){
                    button.setBorder(BorderFactory.createLineBorder(border, 2));
                }
            });

            char charValue = buttonTexts[i].charAt(0);
            button.addActionListener(
                    (e) -> CalculatorManager(charValue)
            );

            if ((i + 1) % 4 == 0){
                x = 25;
                y += buttonPadding + buttonHeight;
            }
            else
                x += buttonPadding + buttonWeight;
        }

    }

    void CalculatorManager(char x){
        try{
            String textFromArea = textField.getText();
            if (x == 'C')
                textField.setText("");
            else if (x == '<'){
                textFromArea = textFromArea.substring(0, textFromArea.length() - 1);
                textField.setText(textFromArea);
            }
            else if (x == '='){
                String result = String.valueOf(CalculatingResult(textFromArea));
                textField.setText(result);
            }
            else{
                textFromArea += x;
                textField.setText(textFromArea);
            }
        }catch (Exception ex){
            textField.setText("Error!");
        }
    }

    double CalculatingResult(String calc) {
        double result = 0.0;

        String[] C = calc.split("");
        List<Double> AllValues = new ArrayList<>();
        List<String> AllChar = new ArrayList<>();

        try {
            if (!C[0].matches("\\d+")) {
                throw new Exception("First element has to be a number!");
            }

            String tempValue = "";
            for (int i = 0; i < C.length; i++) {
                if (C[i].matches("\\d+")) {
                    tempValue += C[i];
                } else {
                    AllValues.add(Double.parseDouble(tempValue));
                    tempValue = "";
                    AllChar.add(C[i]);
                }
            }

            if (!tempValue.isEmpty()) {
                AllValues.add(Double.parseDouble(tempValue));
            }

            for (int i = 0; i < AllChar.size(); i++) {
                String operator = AllChar.get(i);
                if (operator.equals("*") || operator.equals("/")) {
                    double left = AllValues.get(i);
                    double right = AllValues.get(i + 1);
                    double partialResult = 0.0;

                    if (operator.equals("*")) {
                        partialResult = left * right;
                    } else {
                        if (right == 0) {
                            throw new ArithmeticException("Cannot divide by zero!");
                        }
                        partialResult = left / right;
                    }

                    AllValues.set(i, partialResult);
                    AllValues.remove(i + 1);
                    AllChar.remove(i);
                    i--;
                }
            }

            result = AllValues.get(0);
            for (int i = 0; i < AllChar.size(); i++) {
                String operator = AllChar.get(i);
                double nextValue = AllValues.get(i + 1);

                if (operator.equals("+")) {
                    result += nextValue;
                } else if (operator.equals("-")) {
                    result -= nextValue;
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Error in calculation: " + e.getMessage());
        }

        return result;
    }
}