package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import map.Box;
import map.ChessPiece;
import map.Map;

/**
 * This class allows to display the game in a frame.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class GameWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
    /*All possible positions for the movable boards*/
    ArrowButton ab1;
    ArrowButton ab2;
    ArrowButton ab3;
    ArrowButton ab4;
    ArrowButton ab5;
    ArrowButton ab6;
    ArrowButton ab7;
    ArrowButton ab8;
    ArrowButton ab9;
    ArrowButton ab10;
    ArrowButton ab11;
    ArrowButton ab12;
    
    /**
     * Create a new GameWindow and fill the window with the map.
     */
    public GameWindow(){
 
        this.setTitle("Star Trek 3D Chess Simulation");
        this.setSize(550,750);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(this.panel);
        
        fillBoard();
        this.setVisible(true);
    }
   /**
    * Fill the window with the map. It sets the arrowbuttons and it links the board to the window.
    */
   public void fillBoard(){
        int x = 18, y = 10;
        GridLayout layout = new GridLayout(x,y);
       
        this.panel.setLayout(layout);
        this.panel.setBackground(Color.WHITE);
		new Map();
        Box[][][] board = Map.getBoard();
        
        ab1 = new ArrowButton("=>",Color.RED,Color.WHITE);
        ab2 = new ArrowButton("<=",Color.BLUE,Color.WHITE);
        ab3 = new ArrowButton("=>");
        ab4 = new ArrowButton("<=");
        ab5 = new ArrowButton("=>");
        ab6 = new ArrowButton("<=");
        ab7 = new ArrowButton("=>");
        ab8 = new ArrowButton("<=");
        ab9 = new ArrowButton("=>");
        ab10 = new ArrowButton("<=");
        ab11 = new ArrowButton("=>",Color.ORANGE, Color.BLACK);
        ab12 = new ArrowButton("<=",Color.MAGENTA,Color.BLACK);
        ab1.setNeighbors(ab2, ab4, ab3);
        ab2.setNeighbors(ab1, ab3, ab4);
        ab3.setNeighbors(ab1, ab2, ab4, ab6, ab5);
        ab4.setNeighbors(ab2, ab1, ab3, ab5, ab6);
        ab5.setNeighbors(ab3, ab4, ab6, ab8, ab7);
        ab6.setNeighbors(ab4, ab3, ab5, ab7, ab8);
        ab7.setNeighbors(ab5, ab6, ab8, ab10, ab9);
        ab8.setNeighbors(ab6, ab5, ab7, ab9, ab10);
        ab9.setNeighbors(ab7, ab8, ab10, ab12, ab11);
        ab10.setNeighbors(ab8, ab7, ab9, ab11, ab12);
        ab11.setNeighbors(ab9, ab10, ab12);
        ab12.setNeighbors(ab11, ab9, ab10);
        
        //First Line
        panel.add(board[0][0][0]);
        panel.add(board[1][0][0]);
        for (int k=2; k<8; k++)
            panel.add(new JLabel());
        panel.add(board[4][0][0]);
        panel.add(board[5][0][0]);
        //Second line
        panel.add(board[0][1][0]);
        panel.add(board[1][1][0]);
        ab1.addActionListener(ab1);
        this.panel.add(ab1);
        for (int k=1; k<5; k++)
            panel.add(board[k][1][1]);
        ab2.addActionListener(ab2);
        this.panel.add(ab2);
        panel.add(board[4][1][0]);
        panel.add(board[5][1][0]);
        /*Third line*/
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        for (int i=1; i<5; i++)
            panel.add(board[i][2][1]);
        for (int i=0; i<3; i++)
            panel.add(new JLabel()); 
        /*Fourth line*/
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        for (int i=1; i<5; i++)
            panel.add(board[i][3][1]);
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        /*Fifth line*/
        for (int i=0; i<2; i++)
            panel.add(board[i][4][0]);
        ab3.addActionListener(ab3);
        this.panel.add(ab3);
        for (int i=1; i<5; i++)
            panel.add(board[i][4][1]);
        ab4.addActionListener(ab4);
        this.panel.add(ab4);
        for (int i=0; i<2; i++)
            panel.add(board[i+4][4][0]);
        /*Sixth line*/
        for (int i=0; i<2; i++)
            panel.add(board[i][5][0]);
        for (int i =0; i<6; i++)
            panel.add(new JLabel());
        for (int i=0; i<2; i++)
            panel.add(board[i+4][5][0]);

        for (int i=0; i<2; i++)
            panel.add(board[i][2][2]);
        for (int i =0; i<6; i++)
            panel.add(new JLabel());
        for (int i=0; i<2; i++)
            panel.add(board[i+4][2][2]);
        /*Seventh line*/
        for (int i=0; i<2; i++)
            panel.add(board[i][3][2]);
        ab5.addActionListener(ab5);
        this.panel.add(ab5);
        for (int i=1; i<5; i++)
            panel.add(board[i][3][3]);
        ab6.addActionListener(ab6);
        this.panel.add(ab6);
        for (int i=0; i<2; i++)
            panel.add(board[i+4][3][2]);
        /*Eighth line*/
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        for (int i=1; i<5; i++)
            panel.add(board[i][4][3]);
        for (int i=0; i<3; i++)
            panel.add(new JLabel()); 
        /*Ninth line*/
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        for (int i=1; i<5; i++)
            panel.add(board[i][5][3]);
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        /*Tenth line*/
        for (int i=0; i<2; i++)
            panel.add(board[i][6][2]);
        ab7.addActionListener(ab7);
        this.panel.add(ab7);
        for (int i=1; i<5; i++)
            panel.add(board[i][6][3]);
        ab8.addActionListener(ab8);
        this.panel.add(ab8);
        for (int i=0; i<2; i++)
            panel.add(board[i+4][6][2]);
        /*Eleventh line*/
        for (int i=0; i<2; i++)
            panel.add(board[i][7][2]);
        for (int i =0; i<6; i++)
            panel.add(new JLabel());
        for (int i=0; i<2; i++)
            panel.add(board[i+4][7][2]);
        for (int i=0; i<2; i++)
            panel.add(board[i][4][4]);
        for (int i =0; i<6; i++)
            panel.add(new JLabel());
        for (int i=0; i<2; i++)
            panel.add(board[i+4][4][4]);
        /*Twelfth line*/
        for (int i=0; i<2; i++)
            panel.add(board[i][5][4]);
        ab9.addActionListener(ab9);
        this.panel.add(ab9);
        for (int i=1; i<5; i++)
            panel.add(board[i][5][5]);
        ab10.addActionListener(ab10);
        this.panel.add(ab10);
        for (int i=0; i<2; i++)
            panel.add(board[i+4][5][4]);
        /*Thirteenth line*/
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        for (int i=1; i<5; i++)
            panel.add(board[i][6][5]);
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        /*Fourteenth line*/
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        for (int i=1; i<5; i++)
            panel.add(board[i][7][5]);
        for (int i=0; i<3; i++)
            panel.add(new JLabel());
        /*Fifteenth line*/
        panel.add(board[0][8][4]);
        panel.add(board[1][8][4]);
        ab11.addActionListener(ab11);
        ab11.setEnabled(false);
        this.panel.add(ab11);
        for (int k=1; k<5; k++)
            panel.add(board[k][8][5]);
        ab12.addActionListener(ab12);
        ab12.setEnabled(false);
        this.panel.add(ab12);
        panel.add(board[4][8][4]);
        panel.add(board[5][8][4]);
        /*Sixteenth line*/
        panel.add(board[0][9][4]);
        panel.add(board[1][9][4]);
        for (int k=2; k<8; k++)
            panel.add(new JLabel());
        panel.add(board[4][9][4]);
        panel.add(board[5][9][4]);
        //Link boxes of movable boards to their respective arrowbuttons 
        ab1.setLinkedBoxes(board[0][0][0],board[1][0][0], board[0][1][0],board[1][1][0]);
        ab2.setLinkedBoxes(board[4][0][0],board[5][0][0], board[4][1][0],board[5][1][0]);
        
        ab3.setLinkedBoxes(board[0][4][0],board[1][4][0], board[0][5][0],board[1][5][0]);
        ab4.setLinkedBoxes(board[4][4][0],board[5][4][0], board[4][5][0],board[5][5][0]);
        
        ab5.setLinkedBoxes(board[0][2][2],board[1][2][2], board[0][3][2],board[1][3][2]);
        ab6.setLinkedBoxes(board[4][2][2],board[5][2][2], board[4][3][2],board[5][3][2]);
        
        ab7.setLinkedBoxes(board[0][6][2],board[1][6][2], board[0][7][2],board[1][7][2]);
        ab8.setLinkedBoxes(board[4][6][2],board[5][6][2], board[4][7][2],board[5][7][2]);
        
        ab9.setLinkedBoxes(board[0][4][4],board[1][4][4], board[0][5][4],board[1][5][4]);
        ab10.setLinkedBoxes(board[4][4][4],board[5][4][4], board[4][5][4],board[5][5][4]); 
        
        ab11.setLinkedBoxes(board[0][8][4],board[1][8][4], board[0][9][4],board[1][9][4]);
        ab12.setLinkedBoxes(board[4][8][4],board[5][8][4], board[4][9][4],board[5][9][4]);
        
        ChessPiece.ABList.add(ab1);
        ChessPiece.ABList.add(ab2);
        ChessPiece.ABList.add(ab3);
        ChessPiece.ABList.add(ab4);
        ChessPiece.ABList.add(ab5);
        ChessPiece.ABList.add(ab6);
        ChessPiece.ABList.add(ab7);
        ChessPiece.ABList.add(ab8);
        ChessPiece.ABList.add(ab9);
        ChessPiece.ABList.add(ab10);
        ChessPiece.ABList.add(ab11);
        ChessPiece.ABList.add(ab12);
   }
}
