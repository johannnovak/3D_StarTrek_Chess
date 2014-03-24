package map;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This class provides a window which will be displayed when a pawn reach the end of the board. Then it offers
 * the possibility to chose between all dead chess pieces to find the one which will replace the pawn.
 * 
 * Basically it will search for all dead chess piece from the same color as the pawn which reached the end of the board and
 * replace it in the same time by the chosen one.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
class ChooseWindow extends JFrame implements ActionListener{
   
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
    private int nbDead = 0;
    private ArrayList<ChessPiece> deadList;
    private ArrayList<JButton> buttons;
    private ChessPiece pieceToChange;
    
    public ChooseWindow(){
    }
	/**
	 * Create a new ChooseWindow object which will let you the choice to replace the pawn by one of your dead chess pieces.
	 * 
	 * @param piece The Pawn which has reached the end of the board.
	 */
    public ChooseWindow(ChessPiece piece){
        this.setLocationRelativeTo(null);
        this.deadList = new ArrayList<ChessPiece>();
        this.buttons = new ArrayList<JButton>();
        this.pieceToChange = piece;
        this.setAlwaysOnTop(true);
        /*Display all chess pieces that are dead and of the same color as the chess piece passed by parameter*/
        for(ChessPiece p : ChessPiece.list)
            if(!p.getAlive() && !p.getTypePiece().equals("pawn") && p.getColor()==piece.getColor())
            {
                this.buttons.add(new JButton(p.getImage()));
                this.buttons.get(nbDead).addActionListener(this);
                ++nbDead;
                this.deadList.add(p);
                this.setSize(100*nbDead, 100);        
            }
        
        this.panel.setLayout(new GridLayout(1, nbDead));
        
        for(int i = 0; i < nbDead; ++i)
               this.panel.add(this.buttons.get(i));

        this.setContentPane(this.panel);
        if (nbDead>0)
        	this.setVisible(true);
        
    }
	/**
	 * Replace the pawn by the chosen chess piece and end the turn of the player.
	 */
    @Override
    public void actionPerformed(ActionEvent ae) {
        for(int i = 0; i < buttons.size(); ++i)
            if(ae.getSource().equals(buttons.get(i)))
            {
                deadList.get(i).setAlive(true);
                deadList.get(i).moveTo(this.pieceToChange.getPosition());
                this.pieceToChange.nextTurn();
                Box.setClicked(false);
                this.dispose();
            }
                
    }
    
}

