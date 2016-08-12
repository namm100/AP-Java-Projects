package tictactoe;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TicTacToeGame extends JPanel implements MouseListener {
	
	private java.util.Random r = new java.util.Random();
	enum GameState {
		PLAYER_WON, COMPUTER_WON, DRAW, IN_PROGRESS
	}
	enum Spot {
		X('X'),O('O'),EMPTY(' ');
		char piece;
		Spot(char piece) {
			this.piece = piece;
		}
		public char getPiece() {
			return this.piece;
		}
	}
	private static final long serialVersionUID = 1L;
	
	
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;
	public static final int DIAGONALS = 2;
	
	
	public static BufferedImage xPiece;
	public static BufferedImage oPiece;
	
	private GameState stateOfGame;
	private int turns;
	
	private Spot[][] board;
	private String[] columns;
	private String[] rows;
	private String[] diagonals;
	
	private JButton resetButton;
	
	public TicTacToeGame() {
		
		loadImages();
		addMouseListener(this);
		this.setSize(300,300);
		setLayout(null);
		
		board = new Spot[ROWS][COLUMNS];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = Spot.EMPTY;
			}
		}
		rows = new String[ROWS];
		columns = new String[COLUMNS];
		diagonals = new String[DIAGONALS];
		updateArrays();
		
		turns = 0;
		stateOfGame = GameState.IN_PROGRESS;
		resetButton = new JButton("Play again?");
		
		resetButton.addActionListener(new ResetButtonAction());
		resetButton.setVisible(false);
		resetButton.setSize(100, 20);
		resetButton.setLocation(150,150); 
		add(resetButton);
		
	}
	
	private void reset() {
		Graphics g = this.getGraphics();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = Spot.EMPTY;
			}
		}
		updateArrays();
		turns = 0;
		stateOfGame = GameState.IN_PROGRESS;
		
		g.clearRect(0, 0, TicTacToeWindow.BOARD_WIDTH, TicTacToeWindow.BOARD_HEIGHT);
		// finally
		
		
		repaint();
		resetButton.setVisible(false);
	}
	private void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(" " + Character.toString(board[i][j].getPiece()) + " |");
			}
			System.out.println("\n-------------");
		}
	}
	private void loadImages() {
		try {
			xPiece = ImageIO.read(new File("xPiece.png"));
			
		} catch (IOException e) {
			System.err.println("Could not find xPiece Image");;
		}
		try {
			oPiece = ImageIO.read(new File("oPiece.png"));
		} catch (IOException e) {
			System.err.println("Could not find oPiece Image");
		}
	}
	private void updateArrays() {
		for (int i = 0; i < rows.length; i++) {
			rows[i] = Character.toString(board[i][0].getPiece()) + Character.toString(board[i][1].getPiece()) +
					Character.toString(board[i][2].getPiece());
		}
		for (int i = 0; i < columns.length; i++) {
			columns[i] = Character.toString(board[0][i].getPiece()) + Character.toString(board[1][i].getPiece()) +
					Character.toString(board[2][i].getPiece());
		}
		diagonals[0] = Character.toString(board[0][0].getPiece()) + Character.toString(board[1][1].getPiece()) +
				Character.toString(board[2][2].getPiece());
		diagonals[1] = Character.toString(board[0][2].getPiece()) + Character.toString(board[1][1].getPiece()) +
				Character.toString(board[2][0].getPiece());
		
	}
	
	
	
	public static int findX(String a) {
		int numOfX = 0;
		for (int i = 0; i < a.length(); i++) 
			if (a.charAt(i) == 'X') 
				numOfX++;
		return numOfX;
	}
	
	public static int findO(String a) {
		int numOfO = 0;
		for (int i = 0; i <a.length(); i++) 
			if (a.charAt(i) == 'O')
				numOfO++;
		return numOfO;
	}
	
	public boolean isTaken(int row, int column) {
		
		try {
			if (board[row][column] == Spot.EMPTY) {
				return false;
			}
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Index out of bounds in method isTaken(int, int): boolean");
		}
		return true;
	}
	
	public boolean isFinished() {
		boolean didWin = false;
		if (turns >= 9) {
			stateOfGame = GameState.DRAW;
			didWin = true;
		}
		for (int i = 0; i < columns.length; i++) {
			if (findX(columns[i]) == 3) {
				stateOfGame = GameState.PLAYER_WON;
				didWin = true;
			}
			if (findO(columns[i])== 3) {
				stateOfGame = GameState.COMPUTER_WON;
				didWin = true;
			}
		}
		for (int i = 0; i < rows.length; i++) {
			if (findX(rows[i]) == 3) {
				stateOfGame = GameState.PLAYER_WON;
				didWin = true;
			}
			if (findO(rows[i]) == 3) {
				stateOfGame = GameState.COMPUTER_WON;
				didWin = true;
			}
		}
		for (int i = 0; i <diagonals.length; i++) {
			if (findX(diagonals[i]) == 3) {
				stateOfGame = GameState.PLAYER_WON;
				didWin = true;
			}
			if (findO(diagonals[i]) == 3) {
				stateOfGame = GameState.COMPUTER_WON;
				didWin = true;
			}
		}
		
		return didWin;
	}
	private int[] getComputerMove() {
		int[] coords = new int[2];
		boolean foundASpace = false;
		int compRow = 4;
		int compCol = 4;
		for (int i = 0; i < columns.length;i++) {
			if (findO(columns[i]) == 2 && findX(columns[i]) == 0) {
				compRow = columns[i].indexOf(' ');
				compCol = i;
				foundASpace = true;
				System.out.println("Going through columns to win at: " + i);
			}
		}
		for (int i = 0; i < rows.length;i++) {
			if (findO(rows[i]) == 2 && findX(rows[i]) == 0) {
				compRow = i;
				compCol = rows[i].indexOf(' ');
				foundASpace = true;
				System.out.println("Going through rows to win at: " + i);
			}
		}
		// diagonals
		if (findO(diagonals[0]) == 2 && findX(diagonals[0]) == 0) {
			compRow = diagonals[0].indexOf(' ');
			compCol = diagonals[0].indexOf(' ');
			foundASpace = true;
			System.out.println("Going through diagonals to win at: " + 0);
		}
		if (findO(diagonals[1]) == 2 && findX(diagonals[1]) == 0) {
			compRow = diagonals[1].indexOf(' ');
			compCol = 2 - diagonals[1].indexOf(' ');
			foundASpace = true;
			System.out.println("Going through diagonals to win at: " + 1);
		}
		// end winning sequence
		if (!foundASpace) {
			// prevent X from winning
			for (int i = 0; i < columns.length; i++) {
				if (findX(columns[i]) == 2 && findO(columns[i]) == 0) {
					compRow = columns[i].indexOf(' ');
					compCol = i;
					foundASpace = true;
					System.out.println("Going through columns to block at: " + i);
				}
			}
			for (int i = 0; i < rows.length; i++) {
				if (findX(rows[i]) == 2 && findO(rows[i]) == 0) {
					compRow = i;
					compCol = rows[i].indexOf(' ');
					foundASpace = true;
					System.out.println("Going through rows to block at: " + i);
				}
			}
			// diagonals
			if (findX(diagonals[0]) == 2 && findO(diagonals[0]) == 0) {
				compRow = diagonals[0].indexOf(' ');
				compCol = diagonals[0].indexOf(' ');
				foundASpace = true;
				System.out.println("Going through diagonals to block at: " + 0);
			}
			if (findX(diagonals[1]) == 2 && findO(diagonals[1]) == 0) {
				compRow = diagonals[1].indexOf(' ');
				compCol = 2 - diagonals[1].indexOf(' ');
				foundASpace = true;
				System.out.println("Going through diagonals to block at: " + 1);
			}
			// maybe computer sticking to a block
		}
		if (!foundASpace || isTaken(compRow, compCol)) {
			System.out.println("Randomized");
			do {
				compRow = r.nextInt(3);
				compCol = r.nextInt(3);
			} while (isTaken(compRow, compCol));
		}
		System.out.println("Spot is: " + compRow + " " + compCol);
		coords[0] = compRow;
		coords[1] = compCol;
		return coords;
	}
	public void paintComponent(Graphics g) {
		// the initial painting
		super.paintComponent(g);
		g.drawLine(100, 0, 100, TicTacToeWindow.BOARD_HEIGHT);
		g.drawLine(200, 0, 200, TicTacToeWindow.BOARD_HEIGHT);
		g.drawLine(0, 100, TicTacToeWindow.BOARD_WIDTH, 100);
		g.drawLine(0, 200, TicTacToeWindow.BOARD_WIDTH, 200);
		
		
	}
	public void mouseClicked(MouseEvent e) {
		Graphics g = this.getGraphics(); // so can draw images
		
		int mx = e.getX();
		int my = e.getY();
		System.out.println("Clicked on: (" + mx + "," + my + ")");
		
		int currentCol = 3;
		int currentRow = 3;
		// row 0
		if ((mx > 0 && mx < 100) && (my > 0 && my < 100)) {
			// This is space [0][0] 
			System.out.println("[0][0] spot clicked on");
			currentRow = 0;
			currentCol = 0;
		}
		if ((mx > 100 && mx < 200) && (my > 0 && my < 100)) {
			// This is space [0][1] 
			System.out.println("[0][1] spot clicked on");
			currentRow = 0;
			currentCol = 1;
		}
		if ((mx > 200 && mx < 300) && (my > 0 && my < 100)) {
			// This is space [0][2] 
			System.out.println("[0][2] spot clicked on");
			currentRow = 0;
			currentCol = 2;
		}
		
		// row 1
		if ((mx < 100 && mx > 0) && (my > 100 && my < 200)) {
			// This is space [1][0]
			System.out.println("[1][0] spot clicked on");
			currentRow = 1;
			currentCol = 0;
		}
		if ((mx < 200 && mx > 100) && (my < 200 && my > 100)) {
			// This is space [1][1]
			System.out.println("[1][1] spot clicked on");
			currentRow = 1;
			currentCol = 1;
		}
		if ((mx < 300 && mx > 200) && (my > 100 && my < 200)) {
			// This is space [1][2]
			System.out.println("[1][2] spot clicked on");
			currentRow = 1;
			currentCol = 2;
		}
		
		// row 2
		if ((mx > 0 && mx < 100) && (my > 200 && my < 300)) {
			// this is space [2][0]
			System.out.println("[2][0] spot clicked on");
			currentRow = 2;
			currentCol = 0;
		}
		if ((mx > 100 && mx < 200) && (my > 200 && my < 300)) {
			// this is space [2][1]
			System.out.println("[2][1] spot clicked on");
			currentRow = 2;
			currentCol = 1;
		}
		if ((mx > 200 && mx < 300) && (my > 200 && my < 300)) {
			// this is space [2][2]
			System.out.println("[2][2] spot clicked on");
			currentRow = 2;
			currentCol = 2;
		}
		// after checking which square has been taken
		if (!isTaken(currentRow, currentCol) && !isFinished()) {
			// here is if the space is not occupied, 
			// player's turn
			board[currentRow][currentCol] = Spot.X;
			updateArrays();
			g.drawImage(xPiece, ((mx / 100) * 100)+5, ((my / 100) * 100)+5, this);
			printBoard();
			turns++;
			if (!isFinished()) {
				// computer's turn
				
				int[] compMove = getComputerMove();
				int compRow = compMove[0];
				int compCol = compMove[1];
				board[compRow][compCol] = Spot.O;
				updateArrays();
				g.drawImage(oPiece, (compCol * 100) + 10, (compRow * 100) + 10, this);
				printBoard();
				turns++;
			}
			if (isFinished()) {
				// code for finishing
				switch (stateOfGame) {
				case PLAYER_WON:
					System.out.println("Player won!");
					g.drawString("Yay Player Has Won!",0, TicTacToeWindow.BOARD_HEIGHT /2 + 50);
					break;
				case COMPUTER_WON:
					System.out.println("Computer won!");
					g.drawString("Darn, The Computer Has Beaten You", 0, TicTacToeWindow.BOARD_HEIGHT / 2 + 50);
					break;
				case DRAW:
					System.out.println("It's a draw!");
					g.drawString("It Is A Draw!",0, TicTacToeWindow.BOARD_HEIGHT / 2 + 50);
					break;
				default:
					System.err.println("Something is wrong!");
					break;
				}
				// finally
				resetButton.requestFocus();
				resetButton.setVisible(true);
			}
			
		} else {
			
			System.err.println("Player attempted to click on a space already placed");
		}
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
	class ResetButtonAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			reset();
		}
		
	}
	
}
