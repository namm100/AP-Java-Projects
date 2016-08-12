import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
public class TicTacToe {
  static Scanner s = new Scanner(System.in);
  static Random r = new Random();
  String[] columns;
  String[] rows;
  String[] diagonals;
  char[][] board;
  String whoWon;
  int turns;
  
  public static int findX(String a) {
    int numX = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) == 'X') {
        numX++;
      }
    }
    return numX;
  }
  public static int findO(String a) {
    int numO = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) == 'O') {
        numO++;
      }
    }
    return numO;
  }
  public static void print(String a) {
    System.out.print(a);
  }
  public static int inputInt() {
    
    try {
      int a = s.nextInt();
      return a;
    } catch (InputMismatchException e) {
      s.next();
      return 6;
    }
  }
  public TicTacToe() {
    board = new char[3][3];
    columns = new String[3];
    rows = new String[3];
    diagonals = new String[2];
    whoWon = "no one";
    turns = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = ' ';
      }
    }
    
  }
  public void printBoard() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(" " + Character.toString(board[i][j]) + " |");
      }
      System.out.println("\n-------------");
    }
  }
  
  public void putInto(char a, int row, int column) {
    
    if (a != 'X' && a != 'O') a = ' ';
    if (row < board.length && column < board[0].length) {
      if (board[row][column] == 'O' || board[row][column] == 'X') {
        print("Loose turn for trying to place in already placed spot\n");
      } else {
        board[row][column] = a;
        turns++;
        // this is a problem
        for (int i = 0; i < columns.length; i++) {
          columns[i] = Character.toString(board[0][i]) + Character.toString(board[1][i]) + Character.toString(board[2][i]) + "";
        }
        for (int j = 0; j < rows.length; j++) {
          rows[j] = Character.toString(board[j][0]) + Character.toString(board[j][1]) + Character.toString(board[j][2]) + "";
        }
        
        diagonals[0] = Character.toString(board[0][0]) + Character.toString(board[1][1]) + Character.toString(board[2][2]) + "";
        diagonals[1] = Character.toString(board[0][2]) + Character.toString(board[1][1]) + Character.toString(board[2][0]) + "";
        printBoard();
       
      }
    } else {
      System.err.println("Index out of bounds");
      print("Loose turn for out of bounds!\n");
    }
    
  }
  
  public boolean didItWin() {
    boolean didWin = false;
    for (int i = 0; i < columns.length; i++) {
      
      if (findX(columns[i]) == 3) {
        whoWon = "X player won";
        didWin = true;
      }
      if (findO(columns[i]) == 3) {
        whoWon = "O player won";
        didWin = true;
      }
    }
    for (int i = 0; i < rows.length; i++) {
      if (findX(rows[i]) == 3) {
        whoWon = "X player won";
        didWin = true;
      }
      if (findO(rows[i]) == 3) {
        whoWon = "O player won";
        didWin = true;
      }
    }
    for (int i = 0; i < diagonals.length; i++) {
      if (findX(diagonals[i]) == 3) {
        whoWon = "X player won";
        didWin = true;
      }
      if (findO(diagonals[i]) == 3) {
        whoWon = "O player won";
        didWin = true;
      }
    }
    if (!didWin && turns == 9) {
      didWin = true;
      whoWon = "no one";
    }
   
    return didWin;
  }
  public String getWhoWon() {
    return whoWon;
  }
  public boolean isSpaceTaken(int row, int column) {
    if (row > 2 || column > 2) {
      return false;
    }
    if (board[row][column] == 'X' || board[row][column] == 'O') 
      return true;
    else 
      return false;
  }
  public void printStuff() {
    System.out.println();
    print("columns: \n");
    for (int i = 0; i < columns.length; i++) {
      System.out.println(columns[i]);
    }
    print("rows: \n");
    for (int i = 0; i < rows.length; i++) {
      System.out.println(rows[i]);
    }
    print("diagonals: \n");
    for (int i = 0; i < diagonals.length; i++) {
      System.out.println(diagonals[i]);
    }
    
  }
  public static void main(String[] a) {
    
    print("Welcome to tic tac toe\n");
    print("Do you want one or two players? ");
    TicTacToe game = new TicTacToe();
    int choice = inputInt();
    if (choice == 2) {
      print("Welcome to two player game, X goes first\n");
      
      do {
        print("X player enter row (0 - 2): ");
        int rowX = inputInt();
        print("X player enter column (0 - 2)");
        int colX = inputInt();
        game.putInto('X',rowX,colX);
        
        // game.printStuff();
        if (game.didItWin()) continue;
        print("O player enter row (0 - 2): ");
        int rowO = inputInt();
        print("O player enter column (0 - 2): ");
        int colO = inputInt();
        game.putInto('O',rowO, colO);
        
        game.printStuff();
      } while(!game.didItWin());
      print("Alright! Nice job " + game.getWhoWon() + "! YOU WON!");
    } else {
      // one player game
      print("Welcome to one player game!\n");
      do {
        
         print("X player enter row (0 - 2): ");
         int rowX = inputInt();
         print("X player enter column (0 - 2)");
         int colX = inputInt();
         game.putInto('X',rowX,colX);
         
         if (game.didItWin()) continue;
         print("Comp goes\n");
         int compRow = 4, compCol = 4;
         // comp logic here
         // order: win, stop player, advance position, finally, random
         boolean foundASpace = false;
         // win
         // columns
         for (int i = 0; i < game.columns.length; i++) {
           if (findO(game.columns[i]) == 2 && findX(game.columns[i]) == 0) {
             compRow = game.columns[i].indexOf(' ');
             compCol = i;
             foundASpace = true;
           }
         }
         for (int i = 0; i < game.rows.length; i++) {
           if (findO(game.rows[i]) == 2 && findX(game.rows[i]) == 0) {
             compRow = i;
             compCol = game.rows[i].indexOf(' ');
             foundASpace = true;
           }
         }
         // diagonals
         if (findO(game.diagonals[0]) == 2 && findX(game.diagonals[0]) == 0) {
           compRow = game.diagonals[0].indexOf(' ');
           compCol = game.diagonals[0].indexOf(' ');
           foundASpace = true;
         }
         if (findO(game.diagonals[1]) == 2 && findX(game.diagonals[1]) == 0) {
           compRow = game.diagonals[1].indexOf(' ');
           compCol = 2 - game.diagonals[1].indexOf(' ');
           foundASpace = true;
         }
         // end won
         if (!foundASpace) {
           // prevent X from winning
           for (int i = 0; i < game.columns.length; i++) {
             if (findX(game.columns[i]) == 2 && findO(game.columns[i]) == 0) {
               compRow = game.columns[i].indexOf(' ');
               compCol = i;
               foundASpace = true;
             }
           }
           for (int i = 0; i < game.rows.length; i++) {
             if (findX(game.rows[i]) == 2 && findO(game.rows[i]) == 0) {
               compRow = i;
               compCol = game.rows[i].indexOf(' ');
               foundASpace = true;
             }
           }
           // diagonals
           if (findX(game.diagonals[0]) == 2 && findO(game.diagonals[0]) == 0) {
             compRow = game.diagonals[0].indexOf(' ');
             compCol = game.diagonals[0].indexOf(' ');
             foundASpace = true;
           }
           if (findX(game.diagonals[1]) == 2 && findO(game.diagonals[1]) == 0) {
             compRow = game.diagonals[1].indexOf(' ');
             compCol = 2 - game.diagonals[1].indexOf(' ');
             foundASpace = true;
           }
           
         }
         
         if (!foundASpace || game.isSpaceTaken(compRow, compCol)) {
           print("\nRandomized\n");
           do {
             compRow = r.nextInt(3);
             compCol = r.nextInt(3);
           } while (game.isSpaceTaken(compRow, compCol));
         }
         // ends here
         System.out.println("Spot is: " + compRow + " " + compCol);
         game.putInto('O', compRow, compCol);
        
      
        
      } while(!game.didItWin());
      print("Alright! Nice job " + game.getWhoWon());
    }
  s.close();
  }
}