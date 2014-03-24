package map;

import java.awt.Color;
import java.lang.ref.WeakReference;

import javax.swing.ImageIcon;

/**
 * This class represents King as a chess piece. King inherits from the abstract class ChessPiece.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class King extends ChessPiece{
	
	/**
	 * Create a new King with the color and the place passed by parameter
	 * @param color The color in which the King must be painted
	 * @param box The place where the King must be placed
	 */
    public King(Color color, Box box){
        super(color, box, (color == Color.WHITE) ? new ImageIcon("wking.gif") : new ImageIcon("bking.gif"));
    }
    
    /**
     * Define how to find next possible positions of the king
     */
	@Override
	public void setNextPosition(boolean isFocused) {
		this.nextPosition.clear();
		this.nextPosition.add(new WeakReference<Box>(this.getPosition()));
		int z=-2;
		while (z<=2)
		{
			if (this.getPosition().getPosZ()+z<7 && this.getPosition().getPosZ()+z>=0)
			{
				/*Vertical + 1*/
				this.addOneNextPosition(0, 1, z);
				/*Vertical - 1*/
				this.addOneNextPosition(0, -1, z);
				/*Horizontal + 1*/
				this.addOneNextPosition(1, 0, z);
				/*Horizontal - 1*/
				this.addOneNextPosition(-1, 0, z);				
				/*Diagonal X+1 Y+1*/
				this.addOneNextPosition(1, 1, z);
				/*Diagonal X-1 Y-1*/
				this.addOneNextPosition(-1, -1, z);
				/*Diagonal X+1 Y-1*/
				this.addOneNextPosition(1, -1, z);
				/*Diagonal X-1 Y+1*/
				this.addOneNextPosition(-1, 1, z);
				
			}
			++z;
		}
    	
	}
	/**
	 * Return "king"
	 * 
	 * @return "king"
	 */
	public String getTypePiece(){
		return "king";
	}

}
