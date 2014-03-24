package map;

import java.awt.Color;
/**
 * This class provides the map containing all possible positions (Boxes) and it initializes it at the beginning of the game.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class Map {
	private static Box[][][] board;
	/**
	 * Create a new Map object of 7*11*8 Boxes (X*Y*Z) and initializes it.
	 */
	public Map() {
		board = new Box[6][10][7];
		this.initialisation();
	}
	/**
	 * Create and place all chess pieces of the game on the board.
	 */
	private void initialisation() {
		int i=0,j=0,k=0;
		/* i from left to right => 0<=i<6
		 * j from down to up on Y => 0<=j<10
		 * z from down to up on Z => 0<=k<7
		 */
		/*Definition of the three fix boards*/
		k=1;
		for (i=1; i<5; i++)
			for (j=1; j<5; j++)
				board[i][j][k] = new Box(board, i, j, k);
		k=3;
		for (i=1; i<5; i++)
			for (j=3; j<7; j++)
				board[i][j][k] = new Box(board, i, j, k);
		k=5;
		for (i=1; i<5; i++)
			for (j=5; j<9; j++)
				board[i][j][k] = new Box(board, i, j, k);
		/*Definition of the four movable boards*/
		k=0;
		for (i=0; i<2; i++)
			for(j=0; j<2; j++)
				board[i][j][k] = new Box(board, i, j, k);
		k=0;
		for (i=4; i<6; i++)
			for(j=0; j<2; j++)
				board[i][j][k] = new Box(board, i, j, k);
		k=4;
		for (i=0; i<2; i++)
			for (j=8; j<10; j++)
				board[i][j][k] = new Box(board, i, j, k);
		k=4;
		for (i=4; i<6; i++)
			for (j=8; j<10; j++)
				board[i][j][k] = new Box(board, i, j, k);
		
                board[0][4][0] = new Box(board, 0, 4, 0);
                board[1][4][0] = new Box(board, 1, 4, 0);				
                board[0][5][0] = new Box(board, 0, 5, 0);
                board[1][5][0] = new Box(board, 1, 5, 0);

                board[4][4][0] = new Box(board, 4, 4, 0);
                board[5][4][0] = new Box(board, 5, 4, 0);
                board[4][5][0] = new Box(board, 4, 5, 0);
                board[5][5][0] = new Box(board, 5, 5, 0);
                
                board[0][2][2] = new Box(board, 0, 2, 2);
                board[1][2][2] = new Box(board, 1, 2, 2);
                board[0][3][2] = new Box(board, 0, 3, 2);
                board[1][3][2] = new Box(board, 1, 3, 2);
                
                board[4][2][2] = new Box(board, 4, 2, 2);
                board[5][2][2] = new Box(board, 5, 2, 2);
                board[4][3][2] = new Box(board, 4, 3, 2);
                board[5][3][2] = new Box(board, 5, 3, 2);
 
                board[0][6][2] = new Box(board, 0, 6, 2);
                board[1][6][2] = new Box(board, 1, 6, 2);
                board[0][7][2] = new Box(board, 0, 7, 2);
                board[1][7][2] = new Box(board, 1, 7, 2);

                board[4][6][2] = new Box(board, 4, 6, 2);
                board[5][6][2] = new Box(board, 5, 6, 2);
                board[4][7][2] = new Box(board, 4, 7, 2);
                board[5][7][2] = new Box(board, 5, 7, 2);
                
                board[0][4][4] = new Box(board, 0, 4, 4);
                board[1][4][4] = new Box(board, 1, 4, 4);
                board[0][5][4] = new Box(board, 0, 5, 4);
                board[1][5][4] = new Box(board, 1, 5, 4);
                
                board[4][4][4] = new Box(board, 4, 4, 4);
                board[5][4][4] = new Box(board, 5, 4, 4);
                board[4][5][4] = new Box(board, 4, 5, 4);
                board[5][5][4] = new Box(board, 5, 5, 4);
                
                
		for (i=0; i<6; i++)
			for (j=0; j<10; j++)
				for (k=0; k<7; k++) 
					/*Disable all boxes, add listeners on it and set their color to match the classical game design.*/
					if (board[i][j][k]!=null)
					{
                                            board[i][j][k].addActionListener(board[i][j][k]);
                                            board[i][j][k].setEnabled(false);
                                            if ((i+j)%2==0)
                                                    board[i][j][k].setDefinitiveColor(Color.BLACK);
                                            else
                                                    board[i][j][k].setDefinitiveColor(Color.WHITE);
					}
		/*Creating and placing white team's chess pieces of the game, also giving the white team the first turn*/  
		board[0][0][0].setPiece(new Rook(Color.WHITE, board[0][0][0]));
                board[0][0][0].setEnabled(true);
        board[1][0][0].setPiece(new Queen(Color.WHITE, board[1][0][0]));
                board[1][0][0].setEnabled(true);
        board[4][0][0].setPiece(new King(Color.WHITE, board[4][0][0]));
                board[4][0][0].setEnabled(true);
        board[5][0][0].setPiece(new Rook(Color.WHITE, board[5][0][0]));
                board[5][0][0].setEnabled(true);
        board[0][1][0].setPiece(new Pawn(Color.WHITE, board[0][1][0]));
                board[0][1][0].setEnabled(true);
		board[1][1][0].setPiece(new Pawn(Color.WHITE, board[1][1][0]));
                board[1][1][0].setEnabled(true);
		board[4][1][0].setPiece(new Pawn(Color.WHITE, board[4][1][0]));
                board[4][1][0].setEnabled(true);
		board[5][1][0].setPiece(new Pawn(Color.WHITE, board[5][1][0]));
                board[5][1][0].setEnabled(true);
		board[1][1][1].setPiece(new Knight(Color.WHITE, board[1][1][1]));
                board[1][1][1].setEnabled(true);
		board[2][1][1].setPiece(new Bishop(Color.WHITE, board[2][1][1]));
                board[2][1][1].setEnabled(true);
		board[3][1][1].setPiece(new Bishop(Color.WHITE, board[3][1][1]));
                board[3][1][1].setEnabled(true);
		board[4][1][1].setPiece(new Knight(Color.WHITE, board[4][1][1]));
                board[4][1][1].setEnabled(true);
		board[1][2][1].setPiece(new Pawn(Color.WHITE, board[1][2][1]));
                board[1][2][1].setEnabled(true);
		board[2][2][1].setPiece(new Pawn(Color.WHITE, board[2][2][1]));
                board[2][2][1].setEnabled(true);
		board[3][2][1].setPiece(new Pawn(Color.WHITE, board[3][2][1]));
                board[3][2][1].setEnabled(true);
		board[4][2][1].setPiece(new Pawn(Color.WHITE, board[4][2][1]));
                board[4][2][1].setEnabled(true);
	
        /*Creating and placing white team's chess pieces of the game*/  
		board[0][9][4].setPiece(new Rook(Color.BLACK, board[0][9][4]));
		board[1][9][4].setPiece(new Queen(Color.BLACK, board[1][9][4]));
		board[4][9][4].setPiece(new King(Color.BLACK, board[4][9][4]));
		board[5][9][4].setPiece(new Rook(Color.BLACK, board[5][9][4]));
		board[0][8][4].setPiece(new Pawn(Color.BLACK, board[0][8][4]));
		board[1][8][4].setPiece(new Pawn(Color.BLACK, board[1][8][4]));
		board[4][8][4].setPiece(new Pawn(Color.BLACK, board[4][8][4]));
		board[5][8][4].setPiece(new Pawn(Color.BLACK, board[5][8][4]));
		board[1][8][5].setPiece(new Knight(Color.BLACK, board[1][8][5]));
		board[2][8][5].setPiece(new Bishop(Color.BLACK, board[2][8][5]));
		board[3][8][5].setPiece(new Bishop(Color.BLACK, board[3][8][5]));
		board[4][8][5].setPiece(new Knight(Color.BLACK, board[4][8][5]));
		board[1][7][5].setPiece(new Pawn(Color.BLACK, board[1][7][5]));
		board[2][7][5].setPiece(new Pawn(Color.BLACK, board[2][7][5]));
		board[3][7][5].setPiece(new Pawn(Color.BLACK, board[3][7][5]));
		board[4][7][5].setPiece(new Pawn(Color.BLACK, board[4][7][5]));
		
	}
	/**
	 * Return the map.
	 * 
	 * @return the map of the game
	 * 
	 */
    public static Box[][][] getBoard() {
        return board;
    }
}