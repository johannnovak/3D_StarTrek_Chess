package map;

import java.awt.Color;
import java.lang.ref.WeakReference;

import javax.swing.ImageIcon;
/**
 * This class represents Pawn as a chess piece. Pawn inherits from the abstract class ChessPiece.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class Pawn extends ChessPiece {
	private boolean firstMove = true;
	/**
	 * Create a new Pawn with the color and the place passed by parameter
	 * @param color The color in which the Pawn must be painted
	 * @param box The place where the Pawn must be placed
	 */
	public Pawn(Color color, Box box) {
		super(color, box, (color == Color.WHITE) ? new ImageIcon("wpawn.gif") : new ImageIcon("bpawn.gif"));
	}
    /**
     * Define how to find next possible positions of the Pawn
     */
	@Override
	public void setNextPosition(boolean isFocused) {
		this.nextPosition.clear();
		this.nextPosition.add(new WeakReference<Box>(this.getPosition()));
		if (this.getPosition().getPosY() == ((this.color == Color.BLACK) ? 7 : 2))
			this.firstMove = true;
		int z=-2;
		/*Enable the move of two boxes if it is the first move of the pawn*/
		if (this.getAlive() && (this.getPosition().getPosY()!=((this.getColor()==Color.BLACK) ? 0 : 9) && this.getPosition().getPosY()!=((this.getColor()==Color.BLACK) ? 1 : 8)))
		{
			while (z<=2)
			{
				if (this.getPosition().getPosZ()+z<7 && this.getPosition().getPosZ()+z>0)
				{
					if (this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)>0 && this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)<10) 
						if (this.getPosition().getBoard()[this.getPosition().getPosX()][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z]!=null)
							if (this.getPosition().getBoard()[this.getPosition().getPosX()][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z].getPiece()==null)
							{
								this.nextPosition.add(new WeakReference<Box>(this.getPosition().getBoard()[this.getPosition().getPosX()][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z]));
								for (int k=-2; k<=2; k++)
								{
									if (this.getPosition().getPosZ()+z+k<7 && this.getPosition().getPosZ()+z+k>0)
									{
										if (firstMove && this.getPosition().getPosY()+((this.color == Color.BLACK) ? -2 : 2)>0 && this.getPosition().getPosY()+((this.color == Color.BLACK) ? -2 : 2)<10) 
											if (this.getPosition().getBoard()[this.getPosition().getPosX()][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -2 : 2)][this.getPosition().getPosZ()+z+k]!=null)
												if (this.getPosition().getBoard()[this.getPosition().getPosX()][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -2 : 2)][this.getPosition().getPosZ()+z+k].getPiece()==null)
													this.nextPosition.add(new WeakReference<Box>(this.getPosition().getBoard()[this.getPosition().getPosX()][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -2 : 2)][this.getPosition().getPosZ()+z+k]));
									}
									
								}
								
							}
					/*Allow to eat in diagonal*/
					if (this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)>0 && this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)<10 && this.getPosition().getPosX()+1<6)
						if (this.getPosition().getBoard()[this.getPosition().getPosX()+1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z]!=null)
							if (this.getPosition().getBoard()[this.getPosition().getPosX()+1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z].getPiece()!=null)
								if (this.getPosition().getBoard()[this.getPosition().getPosX()+1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z].getPiece().getColor() != this.getColor())
									if (!this.getPosition().getBoard()[this.getPosition().getPosX()+1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z].getPiece().getTypePiece().equals("Pawn"))
										this.nextPosition.add(new WeakReference<Box>(this.getPosition().getBoard()[this.getPosition().getPosX()+1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z]));
									else
									{
										if (this.getColor()==Color.BLACK)
										{
											ChessPiece.setWhiteKingChecked(true);
											
										}
											
										else
										{
											ChessPiece.setBlackKingChecked(true);
											
										}
											
									}
					
					if (this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)>0 && this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)<10 && this.getPosition().getPosX()-1>0)
						if (this.getPosition().getBoard()[this.getPosition().getPosX()-1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z]!=null)
							if (this.getPosition().getBoard()[this.getPosition().getPosX()-1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z].getPiece()!=null)
								if (this.getPosition().getBoard()[this.getPosition().getPosX()-1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z].getPiece().getColor() != this.getColor())
										if(!this.getPosition().getBoard()[this.getPosition().getPosX()-1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z].getPiece().getTypePiece().equals("Pawn"))
											this.nextPosition.add(new WeakReference<Box>(this.getPosition().getBoard()[this.getPosition().getPosX()-1][this.getPosition().getPosY()+((this.color == Color.BLACK) ? -1 : 1)][this.getPosition().getPosZ()+z]));
										else
										{
											if (this.getColor()==Color.BLACK)
											{
												ChessPiece.setWhiteKingChecked(true);
												
											}
												
											else
											{
												ChessPiece.setBlackKingChecked(true);
												
											}
												
												
										}
				}
				++z;
			}
		}
		/*Allow to change the pawn to an other dead piece if the pawn reached an end of a board*/
		else if (this.getAlive() && isFocused && (this.getPosition().getPosY()==((this.getColor()==Color.BLACK) ? 0 : 9) || this.getPosition().getPosY()==((this.getColor()==Color.BLACK) ? 1 : 8)))
			new ChooseWindow(this);
		this.firstMove = false;
	}
	/**
	 * Return "pawn"
	 * 
	 * @return "pawn"
	 */
	public String getTypePiece(){
		return "pawn";
	}

}
