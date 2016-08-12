package tictactoe;
import javax.swing.*;

public class TicTacToeWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static final int BOARD_WIDTH = 300;
	public static final int BOARD_HEIGHT = 300;
	public static final String TITLE = "Tic Tac Toe";
	public TicTacToeWindow() {
		
		setSize(BOARD_WIDTH+7,BOARD_HEIGHT+30);
		setTitle(TITLE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(new TicTacToeGame());
		
		setVisible(true);
		requestFocus();
	}
}
