package map;

import java.awt.Color;
import java.lang.ref.WeakReference;

import javax.swing.ImageIcon;
/**
 * This class represents Queen as a chess piece. Queen inherits from the abstract class ChessPiece.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class Queen extends ChessPiece{
	/**
	 * Create a new Queen with the color and the place passed by parameter
	 * @param color The color in which the Queen must be painted
	 * @param box The place where the Queen must be placed
	 */
    public Queen(Color color, Box box){
        super(color, box, (color == Color.WHITE) ? new ImageIcon("wqueen.gif") : new ImageIcon("bqueen.gif"));
    }
    /**
     * Define how to find next possible positions of the Queen
     */
	@Override
	public void setNextPosition(boolean isFocused) {
		this.nextPosition.clear();
		System.out.println("X :"+this.getPosition().getPosX()+" Y :"+this.getPosition().getPosY()+" Z :"+this.getPosition().getPosZ());
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
			for (Box b : this.getNextDiagonalPosition())
	    	{
	    		this.nextPosition.add(new WeakReference<Box>(b));
	    	}
		}
		
	}

}
