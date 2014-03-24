package map;

import java.awt.Color;
import java.lang.ref.WeakReference;

import javax.swing.ImageIcon;
/**
 * This class represents Bishop as a chess piece. Bishop inherits from the abstract class ChessPiece.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class Bishop extends ChessPiece{
	/**
	 * Create a new Bishop with the color and the place passed by parameter
	 * @param color The color in which the Bishop must be painted
	 * @param box The place where the Bishop must be placed
	 */
    public Bishop(Color color, Box box){
        super(color, box, (color == Color.WHITE) ? new ImageIcon("wbishop.gif") : new ImageIcon("bbishop.gif"));
    }
    /**
     * Define how to find next possible positions of the bishop
     */
	@Override
	public void setNextPosition(boolean isFocused) {
		// TODO Auto-generated method stub
		this.nextPosition.clear();
		if (this.getAlive())	
			for (Box b : this.getNextDiagonalPosition())
	    	{
	    		this.nextPosition.add(new WeakReference<Box>(b));
	    	}
	}
   
}
