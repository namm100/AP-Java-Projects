package connectfourv2;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ConnectFour extends JPanel implements MouseListener{

    String player1Name, player2Name;
    private Piece[][] board;

    private String[] rows;
    private String[] columns;
    private String[] diagonals1;
    private String[] diagonals2;
    private int[] columnsCounts;

    private State gameState;
    private Turn gameTurn;
    private int turns;


    private JLabel turnLabel;
    private JButton resetButton;
    private JLabel winnerLabel;

    BufferedImage p1Piece, p2Piece;
    public ConnectFour(String one, String two) {

        player1Name = one;
        player2Name = two;
        setLayout(null);
        initializeComponents();
        loadImages();

        addMouseListener(this);

        // initialize board with empty
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
        for (int i = 0; i <this.columnsCounts.length; i++) {
            columnsCounts[i] = 5;
        }
        resetArrays();
        updateArrays();

        gameState = State.IN_PROGRESS;
        gameTurn = Turn.PLAYER_ONE_TURN;
        turns = 0;

        add(turnLabel);
        add(resetButton);
        add(winnerLabel);
    }
    enum Piece {
        PLAYER_ONE('1'),
        PLAYER_TWO('2'),
        EMPTY('0');
        char numOfPiece;
        Piece(char charRep) {
            this.numOfPiece = charRep;
        }
        public char getCharNumOfPiece() {
            return this.numOfPiece;
        }
    }
    enum State {
        PLAYER_ONE_WON,
        PLAYER_TWO_WON,
        IN_PROGRESS,
        DRAW
    }
    enum Turn {
        PLAYER_ONE_TURN, PLAYER_TWO_TURN
    }
    private void loadImages() {
        try {
            p1Piece = ImageIO.read(new File("playerOnePiece.png"));
        } catch (IOException e) {
            System.err.println("Couldn't load player one piece image");
        }
        try {
            p2Piece = ImageIO.read(new File("playerTwoPiece.png"));
        } catch (IOException e) {
            System.err.println("Couldn't load player two piece image");
        }
    }
    private void initializeComponents() {
        this.board = new Piece[6][7];
        this.columns = new String[7];
        this.rows = new String[6];
        this.diagonals1 = new String[6];
        this.diagonals2 = new String[6];
        columnsCounts = new int[7];
        turnLabel = new JLabel(player1Name + "'s turn!");
        turnLabel.setLocation(5,5);
        turnLabel.setSize((player1Name.length() > player2Name.length())?10 * player1Name.length() + 30:10 * player2Name.length() + 30,40);
        resetButton = new JButton("Reset");
        resetButton.setLocation(500,10);
        resetButton.setSize(80,30);
        resetButton.addActionListener(new ResetButtonAction());
        winnerLabel = new JLabel();
        winnerLabel.setLocation(300,10);
        winnerLabel.setSize(150,40);
    }
    private void resetArrays() {
        for (int i = 0; i < columns.length; i++)
            columns[i] = " ";
        for (int i = 0; i < rows.length; i++)
            rows[i] = " ";
        for (int i = 0; i < diagonals1.length; i++)
            diagonals1[i] = " ";
        for (int i = 0; i < diagonals2.length; i++)
            diagonals2[i] = " ";
    }
    private void updateArrays() {
        resetArrays();
        // initializing the rows
        for (int i = 0; i < this.rows.length; i++) {
            for (int j = 0; j < this.columns.length; j++) {
                rows[i] += Character.toString(this.board[i][j].getCharNumOfPiece());
                System.out.println("Row is " + rows[i] + " at index " + i + " with char " + Character.toString(this.board[i][j].getCharNumOfPiece()));
            }
        }
        // initializing the columns
        for (int i = 0; i < this.columns.length; i++) {
            for (int j = 0; j < this.rows.length; j++) {
                columns[i] += Character.toString(this.board[j][i].getCharNumOfPiece());
            }
        }
        // initializing diagonals1
        for (int i = 0; i < 4; i++) {
            this.diagonals1[0] += Character.toString(this.board[i+2][i].getCharNumOfPiece());
        }
        for (int i = 0; i < 5; i++) {
            this.diagonals1[1] += Character.toString(this.board[i+1][i].getCharNumOfPiece());
        }
        for (int i = 0; i < 6; i++) {
            this.diagonals1[2] += Character.toString(this.board[i][i].getCharNumOfPiece());
        }
        for (int i = 0; i < 6; i++) {
            this.diagonals1[3] += Character.toString(this.board[i][i+1].getCharNumOfPiece());
        }
        for (int i = 0; i < 5; i++) {
            this.diagonals1[4] += Character.toString(this.board[i][i+2].getCharNumOfPiece());
        }
        for (int i = 0; i < 4; i++) {
            this.diagonals1[5] += Character.toString(this.board[i][i+3].getCharNumOfPiece());
        }
        // initializing diagonals2
        for (int i = 0; i < 4; i++) {
            this.diagonals2[0] += Character.toString(this.board[i][3-i].getCharNumOfPiece());
        }
        for (int i = 0; i < 5; i++) {
            this.diagonals2[1] += Character.toString(this.board[i][4-i].getCharNumOfPiece());
        }
        for (int i = 0; i < 6; i++) {
            this.diagonals2[2] += Character.toString(this.board[i][5-i].getCharNumOfPiece());
        }
        for (int i = 0; i < 6; i++) {
            this.diagonals2[3] += Character.toString(this.board[i][6-i].getCharNumOfPiece());
        }
        for (int i = 0; i < 5; i++) {
            this.diagonals2[4] += Character.toString(this.board[i+1][6-i].getCharNumOfPiece());
        }
        for (int i = 0; i < 4; i++) {
            this.diagonals2[5] += Character.toString(this.board[i+2][6-i].getCharNumOfPiece());
        }
    }
    private void putPieceInto(int column, Piece piece) {
        if (column < 7 && column > -1) {
            if (columnsCounts[column] > 0) {
                board[columnsCounts[column]][column] = piece;

            }
        } else {
            System.err.println("Index out of bounds in putPieceInto");
            System.err.println("at column: " + column);
        }
    }
    private void reset() {
        Graphics g = this.getGraphics();
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                board[i][j] = Piece.EMPTY;
            }
        }
        for (int i = 0; i <this.columnsCounts.length; i++) {
            columnsCounts[i] = 6;
        }
        for (int i = 0; i < columnsCounts.length; i++) {
            columnsCounts[i] = 5;
        }
        updateArrays();
        gameState = State.IN_PROGRESS;
        gameTurn = Turn.PLAYER_ONE_TURN;
        turns = 0;
        winnerLabel.setText("no one has won");
        g.clearRect(0,0,ConnectFourWindow.WIDTH,ConnectFourWindow.HEIGHT);
        repaint();
    }
    private void updateState() {

        for (int i = 0; i < columns.length; i++) {
            if (get1(columns[i]) >= 4)
                gameState = State.PLAYER_ONE_WON;
            if (get2(columns[i]) >= 4)
                gameState = State.PLAYER_TWO_WON;
        }
        for (int i = 0; i < rows.length; i++) {
            if (get1(rows[i]) >= 4)
                gameState = State.PLAYER_ONE_WON;
            if (get2(rows[i]) >= 4)
                gameState = State.PLAYER_TWO_WON;
        }
        for (int i = 0; i < diagonals1.length; i++) {
            if (get1(diagonals1[i]) >= 4)
                gameState = State.PLAYER_ONE_WON;
            if (get2(diagonals1[i]) >= 4)
                gameState = State.PLAYER_TWO_WON;
        }
        for (int i = 0; i < diagonals2.length; i++) {
            if (get1(diagonals2[i]) >= 4)
                gameState = State.PLAYER_ONE_WON;
            if (get2(diagonals2[i]) >= 4)
                gameState = State.PLAYER_TWO_WON;
        }
        if (turns >= rows.length * columns.length && gameState == State.IN_PROGRESS) {
            System.out.println("Draw at turn " + turns);
            gameState = State.DRAW;
        }

    }
    public void printArrays() {
        System.out.println("Columns:");
        for (int i = 0; i < columns.length; i++) {
            System.out.println(columns[i]);
        }
        System.out.println("Rows:");
        for (int i = 0; i < rows.length; i++) {
            System.out.println(rows[i]);
        }
        System.out.println("Diagonals1:");
        for (int i = 0; i < diagonals1.length; i++) {
            System.out.println(diagonals1[i]);
        }
        System.out.println("Diagonals2:");
        for (int i = 0; i < diagonals2.length; i++) {
            System.out.println(diagonals2[i]);
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // side to side lines
        for (int i = 0; i < 6; i++) {
            g.drawLine(0,(i+1) * 100, 700, (i+1) * 100);
        }
        // up and down lines
        for (int i = 0; i < 6; i++) {
            g.drawLine((i+1) * 100, 100, (i+1) * 100, 700);
        }

    }
    public void mouseClicked(MouseEvent e) {
        Graphics g = getGraphics();
        int mx = e.getX();
        int my = e.getY();
        System.out.println("Panel clicked on (" + mx + "," + my + ")");
        int col = mx / 100;
        if (mx == 700) col = 6;

        if (gameState == State.IN_PROGRESS) {
            winnerLabel.setText("No one has won");
            switch (gameTurn) {
                case PLAYER_ONE_TURN:
                    if (columnsCounts[col] < 0 ){
                        System.err.println("Player1 tried to place in a column that was full");
                    } else {
                        // normal turn for player one
                        System.out.println("Player 1 goes");
                        putPieceInto(col,Piece.PLAYER_ONE);

                        updateArrays();
                        g.drawImage(p1Piece,((mx / 100) * 100) + 10, (columnsCounts[col] * 100) + 110, this);
                        gameTurn = Turn.PLAYER_TWO_TURN;
                        updateState();
                        columnsCounts[col]--;
                        turns++;
                        turnLabel.setText(player2Name + "'s turn!");
                        printArrays();

                    }
                    break;
                case PLAYER_TWO_TURN:
                    if (columnsCounts[col] < 0) {
                        System.err.println("Player1 tried to place in a column that was full");
                    } else {
                        // norm turn for player 2
                        System.out.println("Player 2 goes");
                        putPieceInto(col, Piece.PLAYER_TWO);

                        updateArrays();
                        g.drawImage(p2Piece,((mx / 100) * 100) + 10, (columnsCounts[col] * 100) + 110, this);
                        gameTurn = Turn.PLAYER_ONE_TURN;
                        updateState();
                        columnsCounts[col]--;
                        turns++;
                        turnLabel.setText(player1Name + "'s turn!");
                        printArrays();
                    }
                    break;
            }
        }
        switch (gameState) {
            case PLAYER_ONE_WON:
                System.out.println("Player 1 won");
                winnerLabel.setText(player1Name + " has won!");
                break;
            case PLAYER_TWO_WON:
                System.out.println("Player 2 won");
                winnerLabel.setText(player2Name + " has won!");
                break;
            case DRAW:
                System.out.println("Its a draw");
                winnerLabel.setText("Its a draw");
                break;
        }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public static int get1(String a) {
        ArrayList<Integer> runs = new ArrayList<Integer>();
        for (int i = 0; i < a.length(); i++) {
            if (i == a.length() - 2) break;
            int count = i;
            int amount = 1;
            while (a.charAt(count) == a.charAt(count+1) && a.charAt(count) == '1') {
                amount= amount +1;
                count = count +1;
                if (count == a.length() - 1) break;
            }
            runs.add(amount);
        }
        int max = runs.get(0);
        for (int i = 1; i < runs.size(); i++) {
            max = Math.max(max, runs.get(i));
        }

        return max;
    }
    public static int get2(String a) {
        ArrayList<Integer> runs = new ArrayList<Integer>();
        for (int i = 0; i < a.length(); i++) {
            if (i == a.length() - 2) break;
            int count = i;
            int amount = 1;
            while (a.charAt(count) == a.charAt(count+1) && a.charAt(count) == '2') {
                amount= amount +1;
                count = count +1;
                if (count == a.length() - 1) break;
            }
            runs.add(amount);
        }
        int max = runs.get(0);
        for (int i = 1; i < runs.size(); i++) {
            max = Math.max(max, runs.get(i));
        }

        return max;
    }

    class ResetButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            reset();
        }
    }

}
