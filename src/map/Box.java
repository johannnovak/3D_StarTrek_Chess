package map;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * This class provides a mean to place chess pieces on the map. Each Box is associated to its position in the map
 *  (X, Y and Z), a chess piece (if it contains one), a color (only for design purpose).
 *  
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class Box extends JButton implements ActionListener{
	private static final long serialVersionUID = 8044595698385676163L;
	private ChessPiece piece;
	private int posX;
	private int posY;
	private int posZ;
	private WeakReference<Box[][][]> board;
	private Color color;
        
	private static Box temp = null;
	private static boolean clicked = false;
	/**
	 * Create a new box in the board passed by parameter and whose position in its is defined by X, Y and Z.
	 * 
	 * @param board The whole map of the game where the Box has to be put in.
	 * @param posX The position on X where the Box has to be placed.
	 * @param posY The position on Y where the Box has to be placed.
	 * @param posZ The position on Z where the Box has to be placed.
	 */
	public Box(Box[][][] board, int posX, int posY, int posZ) {
		this.board = new WeakReference<Box[][][]>(board);
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
       	}
	/**
	 * Set the chess piece contained by the box and the image displayed by the box (considering the chess piece in it).
	 * 
	 * @param piece The ChessPiece to be put in the box object.
	 */
	public void setPiece(ChessPiece piece) {
		this.piece = piece;
        if (this.piece == null)
        	this.setIcon(null);
        else
        	this.setIcon(piece.getImage());
        
	}
	/**
	 * Set whether the box has been clicked or not.
	 * 
	 * @param clicked The boolean stating whether the box has been clicked or not.
	 */
	public static void setClicked(boolean clicked) {

		Box.clicked = clicked;
	}
	/**
	 * Return the chess piece which is on the box.
	 * 
	 * @return
	 * <ul>
	 * <li>null if there is not.
	 * <li>the chess piece on the box if there is one.
	 * </ul>
	 */
	public ChessPiece getPiece() {
		return piece;
	}
	/**
	 * Return whether the box does not contain a piece or contains one.
	 * 
	 * @return The boolean stating if there is no piece on the box
	 */
	public boolean isEmpty(){
		return this.piece!=null;
	}
	/**
	 * Set the color of the box (design purpose).
	 * 
	 * @param color The color in which the box will be painted
	 */
	public void setColor(Color color){
		/*Return to default value*/
		if (color==null)
			this.setBackground(this.color);
		else
			this.setBackground(color);
	}
	/**
	 * Set the color of the box (the default one).
	 * 
	 * @param color The color in which the box will be painted
	 */
	public void setDefinitiveColor(Color color){
		this.color = color;
		this.setColor(color);
	}
	/**
	 * Set the position of the box on Z in the board.
	 * 
	 * @param posZ the position of the box on Z in the board.
	 */
	public void setPosZ(int posZ) {
		this.posZ = posZ;
	}
	/**
	 * Set the position of the box on Y in the board.
	 * 
	 * @param posY the position of the box on Y in the board.
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	/**
	 * Set the position of the box on X in the board.
	 * 
	 * @param posX the position of the box on X in the board.
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}
	/**
	 * Get the position of the box on Z in the board.
	 * 
	 * @return the position of the box on Z in the board.
	 */
	public int getPosZ() {
		return posZ;
	}
	/**
	 * Get the position of the box on Y in the board.
	 * 
	 * @return the position of the box on Y in the board.
	 */	
	public int getPosY() {
		return posY;
	}
	/**
	 * Get the position of the box on X in the board.
	 * 
	 * @return the position of the box on X in the board.
	 */
	public int getPosX() {
		return posX;
	}
	/**
	 * Return the board of the game
	 * 
	 * @return the board of the game
	 */
	public Box[][][] getBoard(){
		return this.board.get();
	}
	
	/**
	 * Action performed when clicking on the box.
	 * <ul>
	 * <li>If the box has already been clicked, it means that we would like to unselect it.
	 * <li>If the box has not been clicked but an other box has been clicked, then we have the box at the beginning and at the
	 * end of the move.
	 * <li>If not any box has been clicked, then we just select it to display (and allow) next possible destinations of the chess piece.
	 * </ul>
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		 // the box has already been clicked, it means that we would like to unselect it.
        if(Box.clicked && (this == Box.temp)){
      		clicked = false;
	      	this.getPiece().setNextPosition();
	      	// Disable all next positions
			for (Box b : this.getPiece().getNextPosition())
			{
	            	b.setEnabled(false);
	                b.setColor(null);
	                          
			}
			// Re-enable all chess piece from the playing team
	        for(ChessPiece p : ChessPiece.list)
	            if(this.getPiece().getColor() == p.getColor() && p.getAlive())
	                p.getPosition().setEnabled(true);
	        
        /* the box has not been clicked but an other box has been clicked, then we have the box at the beginning and at the
			 end of the move.*/
        }else if(Box.clicked){
            boolean isCheck = false;
        	Box.clicked = false;
            ChessPiece bemp = this.getPiece();
            //Moving the chess piece and allow the next player to play.
            Box.temp.getPiece().moveTo(this);
            this.getPiece().nextTurn();
                       
            //Refresh all next possible positions
            for(ChessPiece p : ChessPiece.getList())
            {
                if (p.getAlive())
                {
                	p.setNextPosition();
                	p.getNextPosition();
                }
            }
            //Check whether the player which has moved the chess piece can do it (i.e., if his/her king is checked, he/she cannot)
            if (this.getPiece().getColor()==Color.WHITE)
            {
                if (ChessPiece.isWhiteKingChecked())
                {
                	/*NOT ALLOWED*/
                	//If the move is not allowed, return to the previous position of the chess piece
                    this.getPiece().moveTo(Box.temp);
                    if(bemp != null)
                    {
                    	this.setPiece(bemp);
                    	bemp.setAlive(true);
                    }
                    isCheck = true;
                }
            }else
            {
                if (ChessPiece.isBlackKingChecked())
                {
                	/*NOT ALLOWED*/
                	//If the move is not allowed, return to the previous position of the chess piece
                    this.getPiece().moveTo(Box.temp);
                    if(bemp != null)
                    {
                    	this.setPiece(bemp);
                    	bemp.setAlive(true);
                    }
                    isCheck = true;
                }
                   
            }
            //If the move is not allowed, display an error message and give the ability to play to the player who made the mistake
            if (isCheck==true)
            {
        		
        		int i=0;
        		while(ChessPiece.list.get(i).getColor() == temp.getPiece().getColor())
        		{
        			++i;
        		}
        		ChessPiece.list.get(i).nextTurn();
        		JOptionPane.showMessageDialog(null, "You can't move to this case otherwise your king is checked !", "Check !", JOptionPane.WARNING_MESSAGE);
            }
            ChessPiece.setBlackKingChecked(false);
    		ChessPiece.setWhiteKingChecked(false);
            
    		//If not any box has been clicked, then we just select it to display (and allow) next possible destinations of the chess piece.
            }else{
                Box.clicked = true;
                Box.temp = this;
                Box.temp.getPiece().setNextPosition(true);
                ArrayList<Box> boxTemp = Box.temp.getPiece().getNextPosition();
                for(ChessPiece p : ChessPiece.list)
                    if(p != this.getPiece() && p.getColor() == this.getPiece().getColor())
                        p.getPosition().setEnabled(false);
                for (Box b: boxTemp)
                {
                    b.setEnabled(true);
                    b.setColor(Color.green);
                }                            
            }
	}
        
	/**
	 * Return the boolean stating whether the chess piece on the box is of the same color as the color passed by parameter
	 * 
	 * @param color The color to test with the chess piece's color
	 * @return
	 * <ul>
	 * <li>true if the color of the chess piece is the same as color in parameter
	 * <li>false otherwise
	 * </ul>
	 */
	public boolean isTeam(Color color){
		if (this.getPiece()!=null)
			return this.getPiece().getColor()==color;
		return false;
	}
	/**
	 * Return the boolean stating whether the chess piece on the box (if exists) is a king
	 * 
	 * @return
	 * <ul>
	 * <li>true if the chess piece on the box is a king
	 * <li>false otherwise
	 * </ul>
	 */
	public boolean isKing(){
		if (this.getPiece()!=null)
			return this.getPiece().getTypePiece().equals("king");
		return false;
	}
}
