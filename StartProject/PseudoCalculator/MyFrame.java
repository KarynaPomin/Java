package Classes;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyFrame extends JFrame {
    JButton addButton = new JButton("Add");
    JButton subtractButton = new JButton("Subtract");
    JButton multiplyButton = new JButton("Multiply");
    JButton divideButton = new JButton("Divide");

    JTextField aInput = new JTextField();
    JTextField bInput = new JTextField();

    JLabel resultLabel = new JLabel("Result");

    Color frameBackground = new Color(220, 220, 220);
    Color buttonBackground = new Color(192, 192, 192);
    Color labelBackground = new Color(224, 224, 224);
    Color inputBackground = new Color(211, 211, 211);


    public MyFrame(){
        this.getContentPane().setBackground(frameBackground);

        this.add(addButton);
        this.add(subtractButton);
        this.add(multiplyButton);
        this.add(divideButton);

        addButton.setBounds(50, 50, 100, 50);
        addButton.setBackground(buttonBackground);
        addButton.addActionListener(
                (e) -> Calculating('+')
        );

        subtractButton.setBounds(170, 50, 100, 50);
        subtractButton.setBackground(buttonBackground);
        subtractButton.addActionListener(
                (e) -> Calculating('-')
        );

        multiplyButton.setBounds(50, 110, 100, 50);
        multiplyButton.setBackground(buttonBackground);
        multiplyButton.addActionListener(
                (e) -> Calculating('*')
        );

        divideButton.setBounds(170, 110, 100, 50);
        divideButton.setBackground(buttonBackground);
        divideButton.addActionListener(
                (e) -> Calculating('/')
        );

        this.add(aInput);
        this.add(bInput);

        aInput.setBounds(80, 195, 70, 30);
        aInput.setBackground(inputBackground);

        bInput.setBounds(170, 195, 70, 30);
        bInput.setBackground(inputBackground);

        this.add(resultLabel);

        resultLabel.setBounds(50, 250, 220, 50);
        resultLabel.setBackground(labelBackground);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        resultLabel.setBorder(border);

        Font font = new Font("Arial", Font.PLAIN, 14);
        addButton.setFont(font);
        subtractButton.setFont(font);
        multiplyButton.setFont(font);
        divideButton.setFont(font);

        aInput.setFont(font);
        bInput.setFont(font);

        resultLabel.setFont(font);

        this.setSize(340, 420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    void Calculating(char x){
        try{
            resultLabel.setForeground(Color.BLACK);
            if (aInput.getText().trim().isEmpty() || bInput.getText().trim().isEmpty())
                throw new NullPointerException();

            int a = Integer.parseInt(aInput.getText());
            int b = Integer.parseInt(bInput.getText());

            switch (x){
                case '+':
                    resultLabel.setText(String.valueOf(a + b));
                    break;
                case '-':
                    resultLabel.setText(String.valueOf(a - b));
                    break;
                case '*':
                    resultLabel.setText(String.valueOf(a * b));
                    break;
                case '/':
                    if (a == 0 || b == 0) {
                        resultLabel.setForeground(Color.RED);
                        resultLabel.setText("Error: Cannot be divided with 0!");
                        return;
                    }
                    resultLabel.setText(String.valueOf(a / b));
                    break;
                default:
                    resultLabel.setForeground(Color.RED);
                    resultLabel.setText("Error!");
                    break;
            }
        }catch (NullPointerException ex){
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Error: Field cannot be empty!");
        }catch (NumberFormatException ex){
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Error: Number format exception!");
        }catch (ArithmeticException ex){
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Error: Arithmetic exception!");
        }
    }
}
