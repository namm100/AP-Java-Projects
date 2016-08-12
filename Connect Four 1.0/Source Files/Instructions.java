package connectfourv2;

import javax.swing.*;
import java.awt.event.*;


public class Instructions extends JFrame {
    private JTextField player1NameTextField;
    private JTextField player2NameTextField;
    private JLabel titleLabel;

    private JLabel playerTwoLabel;
    private JLabel playerOneLabel;
    private JLabel instructionsTitleLabel;
    private JLabel enterNamesLabel;
    private JTextArea instructionsTextArea;
    private JButton startGameButton;

    private String player1Name = "player 1", player2Name = "player 2";

    public Instructions() {
        super("Connect Four Menu");
        initializeComponents();
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        player1NameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player1Name = player1NameTextField.getText();
                System.out.println("Player one's name is: " + player1Name);
            }
        });
        player2NameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player2Name = player2NameTextField.getText();
                System.out.println("Player two's name is: " + player2Name);
            }
        });
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // start the game
                ConnectFourWindow theGame = new ConnectFourWindow(player1Name,player2Name);
                theGame.setVisible(true);
            }
        });
        instructionsTextArea.setEditable(false);
        /* instructionsTextArea.setText("The first player will be prompted to press a column.\nPress the column in " +
                "where your piece goes.\nThen the next player click" +
                "the same way.\n The first player who gets four pieces in a row \n" +
                ", in a column or diagonally, wins. Have fun! "); */

        Box mainBox = Box.createVerticalBox();
        mainBox.add(titleLabel);
        mainBox.add(enterNamesLabel);
        mainBox.add(playerOneLabel);
        mainBox.add(player1NameTextField);
        mainBox.add(playerTwoLabel);
        mainBox.add(player2NameTextField);
        mainBox.add(instructionsTitleLabel);
        mainBox.add(instructionsTextArea);
        mainBox.add(startGameButton);
        add(mainBox);

    }
    private void initializeComponents() {

        titleLabel = new JLabel("Welcome To Connect Four Version 2.0");
        enterNamesLabel = new JLabel("Please enter your names below: ");
        playerOneLabel = new JLabel("Player one");
        playerTwoLabel = new JLabel("Player two");
        instructionsTitleLabel = new JLabel("Instructions:");
        player1NameTextField = new JTextField("player 1",10);
        player2NameTextField = new JTextField("player 2",10);
        instructionsTextArea = new JTextArea(8,10);
        startGameButton = new JButton("Let's Play!");
    }


}
