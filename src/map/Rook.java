package map;

import java.awt.Color;
import java.lang.ref.WeakReference;

import javax.swing.ImageIcon;
/**
 * This class represents Rook as a chess piece. Rook inherits from the abstract class ChessPiece.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class Rook extends ChessPiece{
	/**
	 * Create a new Rook with the color and the place passed by parameter
	 * @param color The color in which the Rook must be painted
	 * @param box The place where the Rook must be placed
	 */
    public Rook(Color color, Box box){
        super(color, box, (color == Color.WHITE) ? new ImageIcon("wrook.gif") : new ImageIcon("brook.gif"));
    }
    /**
     * Define how to find next possible positions of the Rook
     */
	@Override
	public void setNextPosition(boolean isFocused) {
		this.nextPosition.clear();
		if (this.getAlive())
		{
			for (Box b : this.getNextVerticalPosition())
	    	{
	    		this.nextPosition.add(new WeakReference<Box>(b));
	    	}
			for (Box b : this.getNextHorizontalPosition())
	    	{
	    		this.nextPosition.add(new WeakReference<Box>(b));
	    	}
		}
		
	}


}
