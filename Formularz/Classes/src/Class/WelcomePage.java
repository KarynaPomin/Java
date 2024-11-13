package Class;

import javax.swing.*;
import java.awt.*;

public class WelcomePage {
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel();

    ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\karin\\OneDrive\\T19\\Java\\Classes\\cuteSad.jpg")
            .getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH));
    JLabel imageLabel = new JLabel(imageIcon);

    WelcomePage(String userID) {
        welcomeLabel.setText("<html><center>Hello " + userID + "!<br>Don't be so critical, please.<br>This is my first program in JAVA.</center></html>");
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setVerticalAlignment(JLabel.TOP);

        imageLabel.setLayout(new BorderLayout());
        imageLabel.add(welcomeLabel, BorderLayout.NORTH);

        frame.add(imageLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 300);
        frame.setVisible(true);
    }
}
