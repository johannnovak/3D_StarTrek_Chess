package map;

import java.awt.Color;
import java.lang.ref.WeakReference;

import javax.swing.ImageIcon;
/**
 * This class represents Knight as a chess piece. Knight inherits from the abstract class ChessPiece.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class Knight extends ChessPiece{
	/**
	 * Create a new Knight with the color and the place passed by parameter
	 * @param color The color in which the Knight must be painted
	 * @param box The place where the Knight must be placed
	 */
    public Knight(Color color, Box box){
        super(color, box, (color == Color.WHITE) ? new ImageIcon("wknight.gif") : new ImageIcon("bknight.gif"));
    }
    /**
     * Define how to find next possible positions of the Knight
     */
	@Override
	public void setNextPosition(boolean isFocused) {
		this.nextPosition.clear();
		if (this.getAlive())
		{
			this.nextPosition.add(new WeakReference<Box>(this.getPosition()));
		    int z = -2;
            while(z <= 2)
            {
                if (this.getPosition().getPosZ()+z<7 && this.getPosition().getPosZ()+z>=0)
                {
                                 
                    this.addOneNextPosition(1, 2, z);
                    this.addOneNextPosition(-1, 2, z);
                    this.addOneNextPosition(-1, -2, z);
                    this.addOneNextPosition(1, -2, z);
                    this.addOneNextPosition(-2, 1, z);
                    this.addOneNextPosition(-2, -1, z);
                    this.addOneNextPosition(2, 1, z);
                    this.addOneNextPosition(2, -1, z);         

                }
            ++z;
            }
		}        
                
        }

}
