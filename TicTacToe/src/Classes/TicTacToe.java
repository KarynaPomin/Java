package Classes;

import javax.swing.*;
import javax.swing.plaf.TreeUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean playerTurn_1;

    public TicTacToe(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(204, 229, 255));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(216, 191, 216));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]){
                if (playerTurn_1) {
                    if (buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(255, 204, 204));
                        buttons[i].setText("X");
                        playerTurn_1 = false;
                        textfield.setText("O turn");
                        Check();
                    }
                }
                else {
                    if (buttons[i].getText() == ""){
                        buttons[i].setForeground(new Color(186, 231, 196));
                        buttons[i].setText("O");
                        playerTurn_1 = true;
                        textfield.setText("X turn");
                        Check();
                    }
                }
            }
        }
    }

    public void FirstTurn(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0){
            playerTurn_1 = true;
            textfield.setText("X turn");
        }
        else {
            playerTurn_1 = false;
            textfield.setText("O turn");
        }
    }

    public void Check(){
        if (    (buttons[0].getText() == "X") &&
                (buttons[1].getText() == "X") &&
                (buttons[2].getText() == "X")
        ) xWins(0, 1, 2);
        if (    (buttons[3].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[5].getText() == "X")
        ) xWins(3, 4, 5);
        if (    (buttons[6].getText() == "X") &&
                (buttons[7].getText() == "X") &&
                (buttons[8].getText() == "X")
        ) xWins(6, 7, 8);
        if (    (buttons[0].getText() == "X") &&
                (buttons[3].getText() == "X") &&
                (buttons[6].getText() == "X")
        ) xWins(0, 3, 6);
        if (    (buttons[1].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[7].getText() == "X")
        ) xWins(1, 4, 7);
        if (    (buttons[2].getText() == "X") &&
                (buttons[5].getText() == "X") &&
                (buttons[8].getText() == "X")
        ) xWins(2, 5, 8);
        if (    (buttons[0].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[8].getText() == "X")
        ) xWins(0, 4, 8);
        if (    (buttons[2].getText() == "X") &&
                (buttons[4].getText() == "X") &&
                (buttons[6].getText() == "X")
        ) xWins(2, 4, 6);

        if (    (buttons[0].getText() == "O") &&
                (buttons[1].getText() == "O") &&
                (buttons[2].getText() == "O")
        ) oWins(0, 1, 2);
        if (    (buttons[3].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[5].getText() == "O")
        ) oWins(3, 4, 5);
        if (    (buttons[6].getText() == "O") &&
                (buttons[7].getText() == "O") &&
                (buttons[8].getText() == "O")
        ) oWins(6, 7, 8);
        if (    (buttons[0].getText() == "O") &&
                (buttons[3].getText() == "O") &&
                (buttons[6].getText() == "O")
        ) oWins(0, 3, 6);
        if (    (buttons[1].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[7].getText() == "O")
        ) oWins(1, 4, 7);
        if (    (buttons[2].getText() == "O") &&
                (buttons[5].getText() == "O") &&
                (buttons[8].getText() == "O")
        ) oWins(2, 5, 8);
        if (    (buttons[0].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[8].getText() == "O")
        ) oWins(0, 4, 8);
        if (    (buttons[2].getText() == "O") &&
                (buttons[4].getText() == "O") &&
                (buttons[6].getText() == "O")
        ) oWins(2, 4, 6);
    }

    public void xWins(int a, int b, int c){
        buttons[a].setBackground(new Color(255, 255, 204));
        buttons[b].setBackground(new Color(255, 255, 204));
        buttons[c].setBackground(new Color(255, 255, 204));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textfield.setText("X wins");
    }

    public void oWins(int a, int b, int c){
        buttons[a].setBackground(new Color(255, 255, 204));
        buttons[b].setBackground(new Color(255, 255, 204));
        buttons[c].setBackground(new Color(255, 255, 204));

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        textfield.setText("O wins");
    }
}

