package connectfourv2;

import javax.swing.*;

/**
 * Created by Nam on 12/1/2015.
 */
public class ConnectFourWindow extends JFrame {

    public static final int HEIGHT = 700 + 30;
    public static final int WIDTH = 700 + 7;
    public ConnectFourWindow(String player1Name, String player2Name) {

        super("Play Connect Four");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        add(new ConnectFour(player1Name,player2Name));
        requestFocus();
    }
}
