//TODO Commenter
package map;

import gui.ArrowButton;

import java.awt.Color;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.ImageIcon;
/**
 * This class provides an abstract representation of a chess piece. All chess pieces will inherit from this class to ensure
 * they will have the same behavior. A chess piece contains a box on which it is placed, a color, a state (dead or alive),
 * all of its possible next positions and an image.
 * The chess piece class has also some static variables: the list of all chess pieces in the game, the list of all arrow buttons
 * (buttons used to move movable boards), a temporary box for moving the selected chess piece and two booleans for whether the
 * king of each color is checked or not.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public abstract class ChessPiece {
    protected final Color color;
    protected WeakReference<Box> position;
    protected boolean alive;
    protected ArrayList<WeakReference<Box>> nextPosition;
    protected ImageIcon image;
    public static ArrayList<ChessPiece> list = new ArrayList<ChessPiece>();
    public static ArrayList<ArrowButton> ABList = new ArrayList<ArrowButton>();
    
    private static boolean blackKingChecked = false;
    private static boolean whiteKingChecked = false;
    private static ArrayList<Box> temp;
	/**
	 * Create a new ChessPiece object which has the color passed by parameter, the place in the game with the box passed
	 * by parameter and the image thanks to the image passed by parameter. It will also add itself to the static list of all
	 * chess pieces in the game.
	 * 
	 * @param color The Color of the chess piece (Black or White)
	 * @param box The Box where the chess piece is placed
	 * @param image The ImageIcon representing the chess piece (depends on its type)
	 */
	public ChessPiece(Color color, Box box, ImageIcon image){
		this.color = color;
		position = new WeakReference<Box>(box);
		this.nextPosition = new ArrayList<WeakReference<Box>>();
		list.add(this);
		this.alive = true;
		this.image = image;
    }
	/**
	 * Set the position of the chess piece in the game to the position passed by parameter
	 * 
	 * @param position The Box position where the chess piece will be set.
	 */
	public void setPosition(Box position) {
		this.position = new WeakReference<Box>(position);
	}
	/**
	 * Set the state of the black king (is it checked?) to the boolean passed by parameter.
	 * 
	 * @param blackKingChecked The boolean stating whether the black king is checked or not.
	 */
	public static void setBlackKingChecked(boolean blackKingChecked) {
		ChessPiece.blackKingChecked = blackKingChecked;
	}
	/**
	 * Set the state of the white king (is it checked?) to the boolean passed by parameter.
	 * 
	 * @param whiteKingChecked The boolean stating whether the white king is checked or not.
	 */
	public static void setWhiteKingChecked(boolean whiteKingChecked) {
		ChessPiece.whiteKingChecked = whiteKingChecked;
	}
	/**
	 * Return the state of the black king (is it checked?).
	 * 
	 * @return The boolean stating whether the black king is checked or not.
	 */
	public static boolean isBlackKingChecked() {
		return blackKingChecked;
	}
	/**
	 * Return the state of the white king (is it checked?).
	 * 
	 * @return The boolean stating whether the white king is checked or not.
	 */
	public static boolean isWhiteKingChecked() {
		return whiteKingChecked;
	}
	/**
	 * Return the list of all chess pieces contained in the game.
	 * 
	 * @return the list of all chess pieces contained in the game.
	 */
	public static ArrayList<ChessPiece> getList() {
		return list;
	}
	/**
	 * Check the type of the piece.
	 * 
	 * @return
	 * <ul>
	 * <li>"king" if a king
	 * <li>"Not a king" otherwise
	 * </ul>
	 */
    public String getTypePiece(){
    	return "Not a king";
    }
	/**
	 * Return the color of the chess piece.
	 * 
	 * @return The color of the chess piece.
	 */
    public Color getColor(){
        return this.color;
    }
	/**
	 * Return whether the chess piece is alive or not.
	 * 
	 * @return
	 * <ul>
	 * <li>true if the chess piece is alive
	 * <li>false otherwise
	 * </ul>
	 */
    public boolean getAlive(){
        return this.alive;
    }
	/**
	 * Set the state of the chess piece (alive or not).
	 * 
	 * @param bool The boolean stating whether the chess piece is alive or not.
	 */
    public void setAlive(boolean bool){
        this.alive = bool;
    }
    
	/**
	 * Perform action of eating the chess piece passed by parameter. It will set the piece dead, set its position to null
	 * and set the position of the current chess piece to the position of the piece.
	 * 
	 * @param piece The ChessPiece which will be eaten by the current chess piece.
	 */
    public void eat(ChessPiece piece){
    	piece.setAlive(false);
        piece.getPosition().setPiece(this);
        this.getPosition().setPiece(null);
    }
	/**
	 * Move the current chess piece to the place specified by the box in parameter. If there already is a chess piece on the
	 * box, it will be eaten by the current one.
	 * 
	 * @param box The Box where the current chess piece will be after the method.
	 */ 
    public void moveTo(Box box){
    	if (box!=null)
    	{
    		if (box.getPiece()!=null)
    			eat(box.getPiece());
    		else
    		{
    	        box.setPiece(this);
    	        this.getPosition().setPiece(null);
    		}
    		this.position = new WeakReference<Box>(box);
    	}
    	
	    	
    }
	/**
	 * Return the position of the chess piece in the game.
	 * 
	 * @return the position of the chess piece in the game (a Box)
	 */
    public Box getPosition() {
		return position.get();
    }
	/**
	 * Return all current chess piece's possible next positions.
	 * 
	 * @return all current chess piece's possible next positions.
	 */
    public ArrayList<Box> getNextPosition() {
    	ArrayList<Box> boxTem = new ArrayList<Box>();
    	for(WeakReference<Box> b : this.nextPosition){
            boxTem.add(b.get());
    	}
    	return boxTem;
	}
   /**
    * Retrieve all possible next positions considering a moving of X on X and of Y on Y.
    * 
    * @param X The moving on X
    * @param Y The moving on Y
    * @param list The list of all previous possible next positions with the same moving on X and Y.
    */
   protected void getNextPosition(int X, int Y, CopyOnWriteArrayList<Box> list){
	   CopyOnWriteArrayList<Box> tempAL = new CopyOnWriteArrayList<Box>();	
	   CopyOnWriteArrayList<Box> tempAL2 = new CopyOnWriteArrayList<Box>();	
		   int Z=-2;
		   if (list.isEmpty())
			   list.add(this.getPosition());
		   //For each of the next positions discovered in the previous call to this method, find the next possible ones.
		   for (Box b : list)
		   {
			   //Check if after moving we are still on the map and not out of bounds
			   if(b.getPosX()+X>=0 && b.getPosX()+X<6 && b.getPosY()+Y>=0 && b.getPosY()+Y<10)
				   {
				   	   //Search for next positions on the same level (on Z)
					   Z=0;
					   if (b.getPosZ()+Z<7 && b.getPosZ()+Z>=0)
					   		if (this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]!=null && this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isVisible())
							{
								if (!ChessPiece.temp.contains(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]))
								{
									//If there is no chess piece on the box, add it to possible next positions
									if (this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].getPiece()==null)
									{
										tempAL.add(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]);
									}
									//There is a chess piece on the box
									else
									{
										//Check if it's not from the same team
										if(!this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isTeam(this.getColor()))
												if(!this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isKing())
												{
													/*If it's not a king, add it to next possible positions (but we won't be able to go on with this box,
													 * since we met a "dead-end" (we can't override the chess piece !)) 
													 */
													tempAL2.add(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]);
												}
												else
												{
													//If it's a king, set it checked
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
				
								}
							}
					   /*Search for next possible positions on upper levels starting from +1 to +2 (if there is a chess piece on the box at the
					    * level +1 the level +2 is not possible.
					    */
					   
					   Z=1;
					   while(Z<=2)
					   {
							if (b.getPosZ()+Z<7 && b.getPosZ()+Z>=0)
								if (this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]!=null && this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isVisible())
								{
						   			
									if (!ChessPiece.temp.contains(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]))
									{
										//If there is no chess piece on the box, add it to possible next positions
										if (this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].getPiece()==null)
										{
											tempAL.add(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]);
										}
										//There is a chess piece on the box
										else
										{
											//Check if it's not from the same team
											if(!this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isTeam(this.getColor()))
													if(!this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isKing())
													{
														/*If it's not a king, add it to next possible positions (but we won't be able to go on with this box,
														 * since we met a "dead-end" (we can't override the chess piece !)) 
														 */
														tempAL2.add(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]);
													}
													else
													{
														//If it's a king, set it checked
														if (this.getColor()==Color.BLACK)
														{
															ChessPiece.setWhiteKingChecked(true);
															
														}
														else
														{
															ChessPiece.setBlackKingChecked(true);
															
														}
													}
											/*BREAK THE LOOP because there is a chess piece on the level*/
											Z=2;
										}
					
									}
								}
						++Z;			
					   }
					   /*Search for next possible positions on downer levels starting from -1 to -2 (if there is a chess piece on the box at the
					    * level -1 the level -2 is not possible.
					    */
					   Z=-1;
					   while(Z>=-2)
					   {
							if (b.getPosZ()+Z<7 && b.getPosZ()+Z>=0)
								if (this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]!=null && this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isVisible())
								{
									//If there is no chess piece on the box, add it to possible next positions
									if (!ChessPiece.temp.contains(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]))
									{
										if (this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].getPiece()==null)
										{
											tempAL.add(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]);
										}
										//There is a chess piece on the box
										else
										{
											//Check if it's not from the same team
											if(!this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isTeam(this.getColor()))
													if(!this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z].isKing())
													{
														/*If it's not a king, add it to next possible positions (but we won't be able to go on with this box,
														 * since we met a "dead-end" (we can't override the chess piece !)) 
														 */
														tempAL2.add(this.getPosition().getBoard()[b.getPosX()+X][b.getPosY()+Y][b.getPosZ()+Z]);
													}
													else
													{
														//If it's a king, set it checked
														if (this.getColor()==Color.BLACK)
														{
															ChessPiece.setWhiteKingChecked(true);
															
														}
														else
														{
															ChessPiece.setBlackKingChecked(true);
															
														}
													}
											/*BREAK THE LOOP because there is a chess piece on the level*/
											Z=-2;
										}
					
									}
								}
						--Z;			
					   }
				   }	
		   
			   	  
		   }
		   //Add to the list stored as attribute the next possible positions
		   if (!tempAL2.isEmpty())
			   ChessPiece.temp.addAll(tempAL2);
		   if (!tempAL.isEmpty())
		   {
			   ChessPiece.temp.addAll(tempAL);
			   //Recursively call with the list of next possible positions which are not "dead-ends".
			   getNextPosition(X, Y, tempAL);
			   //Add the result of this call
			   ChessPiece.temp.addAll(tempAL);
		   }
			   
		   
   }
   /**
    * Retrieve all possible next positions for chess pieces that move on diagonals.
    *  
    *  @return all possible next positions for chess pieces that move on diagonals.
    */
   protected ArrayList<Box> getNextDiagonalPosition(){
	   	CopyOnWriteArrayList<Box> tempAL = new CopyOnWriteArrayList<Box>();
		CopyOnWriteArrayList<Box> tempAL2 = new CopyOnWriteArrayList<Box>();
		ChessPiece.temp = new ArrayList<Box>();
		Box temp = this.getPosition();
		
	   	this.getNextPosition(-1, -1, tempAL);
	   	for(int k=0; k<7; k++)
	   	//Be sure there is no box just above or underneath
	   		ChessPiece.temp.remove(temp.getBoard()[temp.getPosX()][temp.getPosY()][k]);
	   	
	   	
	   	this.getNextPosition(1, 1, tempAL2);
	   	tempAL.addAll(tempAL2);
	   	for(int k=0; k<7; k++)
	   	//Be sure there is no box just above or underneath
	   		ChessPiece.temp.remove(this.getPosition().getBoard()[temp.getPosX()][temp.getPosY()][k]);
	   	
	   	tempAL2.clear();
	   	
	   	this.getNextPosition(-1, 1, tempAL2);
	   	tempAL.addAll(tempAL2);
	   	for(int k=0; k<7; k++)
	   	//Be sure there is no box just above or underneath
	   		ChessPiece.temp.remove(this.getPosition().getBoard()[temp.getPosX()][temp.getPosY()][k]);
	   	
	   	tempAL2.clear();
	   	
	   	this.getNextPosition(1, -1, tempAL2);
	   	tempAL.addAll(tempAL2);
	   	for(int k=0; k<7; k++)
	   	//Be sure there is no box just above or underneath
	   		ChessPiece.temp.remove(this.getPosition().getBoard()[temp.getPosX()][temp.getPosY()][k]);
	   	
	   	ChessPiece.temp.add(temp);
	   	return ChessPiece.temp;
	   
    }
   /**
    * Retrieve all possible next positions for chess pieces that move on vertically (only on Y).
    *  
    *  @return all possible next positions for chess pieces that move on vertically.
    */
    protected ArrayList<Box> getNextVerticalPosition(){
	   	CopyOnWriteArrayList<Box> tempAL = new CopyOnWriteArrayList<Box>();
		CopyOnWriteArrayList<Box> tempAL2 = new CopyOnWriteArrayList<Box>();
		ChessPiece.temp = new ArrayList<Box>();
		Box temp = this.getPosition();
			
	   	this.getNextPosition(0, -1, tempAL);
	   	for(int k=0; k<7; k++)
	   	//Be sure there is no box just above or underneath
	   		ChessPiece.temp.remove(temp.getBoard()[temp.getPosX()][temp.getPosY()][k]);
	   	
	   	
	   	this.getNextPosition(0, 1, tempAL2);
	   	tempAL.addAll(tempAL2);
	   	for(int k=0; k<7; k++)
	   	//Be sure there is no box just above or underneath
	   		ChessPiece.temp.remove(this.getPosition().getBoard()[temp.getPosX()][temp.getPosY()][k]);
	   	
	   	ChessPiece.temp.add(temp);
	   	return ChessPiece.temp;
    }
   /**
    * Retrieve all possible next positions for chess pieces that move on horizontally (only on X).
    *  
    *  @return all possible next positions for chess pieces that move on horizontally.
    */
    protected ArrayList<Box> getNextHorizontalPosition(){
	   	CopyOnWriteArrayList<Box> tempAL = new CopyOnWriteArrayList<Box>();
		CopyOnWriteArrayList<Box> tempAL2 = new CopyOnWriteArrayList<Box>();
		ChessPiece.temp = new ArrayList<Box>();
		Box temp = this.getPosition();
		
	   	this.getNextPosition(-1, 0, tempAL);
	   	for(int k=0; k<7; k++)
	   		//Be sure there is no box just above or underneath
	   		ChessPiece.temp.remove(temp.getBoard()[temp.getPosX()][temp.getPosY()][k]);
	   	
	   	
	   	this.getNextPosition(1, 0, tempAL2);
	   	tempAL.addAll(tempAL2);
	   	for(int k=0; k<7; k++)
	   	//Be sure there is no box just above or underneath
	   		ChessPiece.temp.remove(this.getPosition().getBoard()[temp.getPosX()][temp.getPosY()][k]);
	   	
	   	ChessPiece.temp.add(temp);
	   	return ChessPiece.temp;
    }
	/**
	 * Ask for the chess piece all of its possible next positions.
	 */
    public void setNextPosition(){
    	this.setNextPosition(false);
    }
    
    public abstract void setNextPosition(boolean isFocused);

	/**
	 * Return the image of the chess piece.
	 * 
	 * @return the image of the chess piece.
	 */
    public ImageIcon getImage() {
        return image;
    }
   /**
    * Retrieve all possible next positions considering a moving of X on X, of Y on Y and of Z on Z. It is the same as
    * getNextPositions(X, Y, list) but this one is not recursive, it only allows a moving to a neighbor box.
    * 
    * @param X The moving on X
    * @param Y The moving on Y
    * @param Z The moving on Z
    */
	public void addOneNextPosition(int X, int Y, int Z){
		if (this.getPosition().getPosY()+Y<10 && this.getPosition().getPosY()+Y>=0 && this.getPosition().getPosX()+X>=0 && this.getPosition().getPosX()+X<6)
			if (this.getPosition().getBoard()[this.getPosition().getPosX()+X][this.getPosition().getPosY()+Y][this.getPosition().getPosZ()+Z]!=null && this.getPosition().getBoard()[this.getPosition().getPosX()+X][this.getPosition().getPosY()+Y][this.getPosition().getPosZ()+Z].isVisible())
				if (this.getPosition().getBoard()[this.getPosition().getPosX()+X][this.getPosition().getPosY()+Y][this.getPosition().getPosZ()+Z].getPiece()!=null)
				{
					if (this.getPosition().getBoard()[this.getPosition().getPosX()+X][this.getPosition().getPosY()+Y][this.getPosition().getPosZ()+Z].getPiece().getColor() != this.getColor())
						if (!this.getPosition().getBoard()[this.getPosition().getPosX()+X][this.getPosition().getPosY()+Y][this.getPosition().getPosZ()+Z].getPiece().getTypePiece().equals("king"))
							//The move is allowed if there is not a king on the box
							this.nextPosition.add(new WeakReference<Box>(this.getPosition().getBoard()[this.getPosition().getPosX()+X][this.getPosition().getPosY()+Y][this.getPosition().getPosZ()+Z]));
						else 
						{
							/*If the king is on the box, set it checked (it is not a possible next positions but since it can be
							met during the computation of next positions, it is checked*/
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
				else
					//The move is allowed if there is no chess piece on the box
					this.nextPosition.add(new WeakReference<Box>(this.getPosition().getBoard()[this.getPosition().getPosX()+X][this.getPosition().getPosY()+Y][this.getPosition().getPosZ()+Z]));
		
	}
	/**
	 * End the turn of the player owning the current chess piece. It will disable all the chess piece of the same color as
	 * the current chess piece and enable all the other one.
	 */
	public void nextTurn(){
		for (Box b : this.getNextPosition())
		{
			b.setEnabled(false);
			b.setColor(null);
		}
        
        for(ArrowButton ab : ChessPiece.ABList)
        {
            if(ab.getActivated())
            {
            	if(ab.isEnabled())
                    ab.setEnabled(false);
                else 
                    ab.setEnabled(true);
            }
            else
            	ab.setEnabled(false);
            
        }
                
		for (ChessPiece b : ChessPiece.list)
		{
                    if (b.getAlive())
                    {
                        if(b.getColor()!=this.getColor())
                        {
                            b.getPosition().setEnabled(true);
                            b.getPosition().setColor(null);
                        }else
                        {
                            b.getPosition().setEnabled(false);
                        }
                    }
		}
	}
	/**
	 * End the turn of the player owning the color team. It will disable all the chess piece of the color color
	 * and enable all the other one.
	 * 
	 * @param color The color of the team currently playing.
	 */
	public void nextTurnByColor(Color color){
        for(ArrowButton ab : ChessPiece.ABList)
        {
            if(ab.getActivated())
            {
            	if(ab.isEnabled())
                    ab.setEnabled(false);
                else 
                    ab.setEnabled(true);
            }
            else
            	ab.setEnabled(false);
            
        }
                
		for (ChessPiece b : ChessPiece.list)
		{
                    if (b.getAlive())
                    {
                        if(b.getColor()!=color)
                        {
                            b.getPosition().setEnabled(true);
                            b.getPosition().setColor(null);
                        }else
                        {
                            b.getPosition().setEnabled(false);
                        }
                    }
		}
	}
	/**
	 * Call it when you need to restart a game. It will clear the list of chess pieces in the game, of arrow buttons.
	 */
	public static void clear(){
		if (ABList!=null)
			ChessPiece.ABList.clear();
		if (list!=null)
			ChessPiece.list.clear();
		if (temp!=null)
			ChessPiece.temp.clear();
	}
}