package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import map.Box;
import map.ChessPiece;

/**
 * This class allows to move movable boards.
 * 
 * /!\Bugged version
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
    public class ArrowButton extends JButton implements ActionListener{
    
	private static final long serialVersionUID = 1L;
	private boolean activated;
    private static boolean clicked;
    private static ArrowButton temp = null;
    private ArrayList<ArrowButton> neighbors;
    private Color teamColor = null;
    private Color color = null;
    
    private ArrayList<Box> linkedBoxes = new ArrayList<Box>();
    /**
     * Create a new arrow button (basically a new place to move a movable board).
     * 
     * @param string The text to display in the button
     */
    public ArrowButton(String string){

        this.setText(string);
        this.activated = false;
        this.setBorderPainted(false);
        this.setBackground(Color.WHITE);
        this.neighbors = new ArrayList<ArrowButton>();
        ArrowButton.clicked = false;
        
        this.setForeground(Color.BLACK);            
        this.setEnabled(false); 
    }
    
    /**
     * Create a new ArrowButton whose text and color are passed by parameter, and the team controlling it has its color passed by
     * parameter
     * 
     * @param string The text to display in the button
     * @param color The color of the text to be displayed
     * @param teamColor The color of the team owning the movable board (if exists) on the arrow button
     */
    public ArrowButton(String string, Color color, Color teamColor){
        this.setText(string);
        this.activated = true;
        this.color = color;
        this.teamColor = teamColor;
        
        this.setBorderPainted(false);
        this.setBackground(Color.WHITE);
        this.neighbors = new ArrayList<ArrowButton>();
        ArrowButton.clicked = false;      
        
        this.setForeground(this.color);
    }
    /**
     * Action performed when clicking on an arrowbutton
     * 
     * Same behavior as Box but before moving we need to check that the movable board belong to the right team.
     * @see Box
     */
	@Override
    public void actionPerformed(ActionEvent ae) {
        int i = 0;
        
        // the arrow button has already been clicked, it means that we would like to unselect it.
        if(ArrowButton.clicked && (this == ArrowButton.temp || this.activated))
        {
            Color tempColor;
        	ArrowButton.clicked = false;
            for(ArrowButton b : ArrowButton.temp.neighbors)
            {
                if(!b.getActivated())
                {
                    b.setForeground(Color.BLACK);
                    b.setEnabled(false);
                }
            }
            if (temp.nbOnMovable()>=1)
            {
            	int i3=0;
            	while(temp.linkedBoxes.get(i3).getPiece()==null)
            		++i3;
            	tempColor = temp.linkedBoxes.get(i3).getPiece().getColor();
            }
            else
            {
            	int i2=0;
            	while(ChessPiece.list.get(i2).getColor() == temp.getTeamColor())
        		{
        			++i2;
        		}
        		tempColor = temp.getTeamColor();
            }
            for(ChessPiece p : ChessPiece.list)
                if(p.getColor() == tempColor)
                    p.getPosition().setEnabled(true);

        /* the arrowbutton has not been clicked but an other arrowbutton has been clicked, then we have the arrowbutton at the beginning and at the
    	 end of the move.*/
        }else if(ArrowButton.clicked && !this.activated){
            ArrowButton.clicked = false;
            Color tempColor;
            boolean isCheck = false;
            ArrowButton.temp.changeToMovable(this);
            if (temp.nbOnMovable()>=1)
            {
            	int i3=0;
            	while(temp.linkedBoxes.get(i3).getPiece()==null)
            		++i3;            	
            	tempColor = temp.linkedBoxes.get(i3).getPiece().getColor();
            }
            else
            {
            	int i2=0;
            	while(ChessPiece.list.get(i2).getColor() == temp.getTeamColor())
        		{
        			++i2;
        		}
        		tempColor = temp.getTeamColor();
            }
          //CHECK
          for(ChessPiece p : ChessPiece.getList())
          {
              if (p.getAlive())
              {
              	p.setNextPosition();
              	p.getNextPosition();
              }
          }
        //Check whether the player which has moved the chess piece can do it (i.e., if his/her king is checked, he/she cannot)
          if (tempColor==Color.WHITE)
          {
              if (ChessPiece.isWhiteKingChecked())
              {
              	/*NOT ALLOWED*/
             	  this.changeToMovable(ArrowButton.temp);
                  isCheck = true;
              }
          }else
          {
              if (ChessPiece.isBlackKingChecked())
              {
              	/*NOT ALLOWED*/
                  this.changeToMovable(ArrowButton.temp);
                  isCheck = true;
                  System.out.println("plop");
              }
                 
              
          }
        //If the move is not allowed, display an error message and give the ability to play to the player who made the mistake
          if (isCheck==true)
          {
      		JOptionPane.showMessageDialog(null, "You can't move the movable otherwise your king is checked !", "Check !", JOptionPane.WARNING_MESSAGE);
            for(ChessPiece p : ChessPiece.list)
                if(p.getColor() == tempColor)
                    p.getPosition().setEnabled(true);
        	ArrowButton.clicked = false;
            for(ArrowButton b : ArrowButton.temp.neighbors)
            {
                if(!b.getActivated())
                {
                    b.setForeground(Color.BLACK);
                    b.setEnabled(false);
                }
            }
            
            
          }
          else
          {
			this.activated = true;
			this.color = ArrowButton.temp.getColor();
			this.setForeground(this.color);
			this.teamColor = ArrowButton.temp.getTeamColor();
			
			
			ArrowButton.temp.setTeamColor(null);
			ArrowButton.temp.activated = false;
			ArrowButton.temp.setForeground(Color.BLACK);
			this.setEnabled(true);
			ChessPiece.list.get(0).nextTurnByColor(tempColor);
          }
          
          
          ChessPiece.setBlackKingChecked(false);
          ChessPiece.setWhiteKingChecked(false); 
        //If not any arrowbutton has been clicked, then we just select it to display (and allow) next possible destinations of the movable board.
        }else{
            if(this.nbOnMovable()>1)
            {
                JOptionPane.showMessageDialog(null, "You can't move it, there are too many pieces on the movable board", "Warning !", JOptionPane.WARNING_MESSAGE);
            }
            else{
                ArrowButton.clicked = true;
                ArrowButton.temp = this;
                for(ArrowButton b : ArrowButton.temp.neighbors){
                    if(b.getActivated() == false){
                        if(this.nbOnMovable() == 0)
                        {
                            b.setEnabled(true);                        
                            b.setForeground(Color.GREEN);                            
                        }else if(i%2 == 0)
                        {
                            b.setEnabled(true);                        
                            b.setForeground(Color.GREEN);
                        }
                    }
                    ++i;
                }
                for(ChessPiece p : ChessPiece.list)
                    if(p.getColor() == this.getTeamColor())
                        p.getPosition().setEnabled(false);

            }
        }
    }
	/**
	 * Set direct neighbors for the arrow button (possible destination for the movable board)
	 * 
	 * @param neighbors The neighbors of the arrow button
	 */
    void setNeighbors(ArrowButton... neighbors){
        for(ArrowButton b : neighbors){
            this.neighbors.add(b);
        }
    }
    /**
     * Set state of the arrowbutton (activated or not)
     * 
     * @param activated The boolean stating whether or not the arrowbutton is activated
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    /**
     * Return the state of the arrowbutton (activated or not)
     * @return the state of the arrowbutton (activated or not)
     */
    public boolean getActivated() {
        return this.activated;
    }
    /**
     * Return the color of the arrowbutton
     * @return the color of the arrowbutton
     */
    public Color getColor(){
        return this.color;
    }
    /**
     * Return boxes associated to the arrowbutton
     * @return boxes associated to the arrowbutton
     */
    public ArrayList<Box> getLinkedBox(){
        return this.linkedBoxes;
    }
    /**
     * Set Boxes associated to the arrowbutton
     * @param b1 A box associated to the arrowbutton
     * @param b2 A box associated to the arrowbutton
     * @param b3 A box associated to the arrowbutton
     * @param b4 A box associated to the arrowbutton
     */
    public void setLinkedBoxes(Box b1, Box b2, Box b3, Box b4) {
        this.linkedBoxes.add(b1);        
        this.linkedBoxes.add(b2);        
        this.linkedBoxes.add(b3);        
        this.linkedBoxes.add(b4); 
        
        if(!this.activated){
            for(int i = 0; i < 4; ++i)
                this.linkedBoxes.get(i).setVisible(false);
        }
    }
    /**
     * Set Boxes associated to the arrowbutton
     * @param linkedBox Boxes associated to the arrowbutton
     */
    public void setLinkedBoxes(ArrayList<Box> linkedBox) {
        this.linkedBoxes = linkedBox;
    }
    /**
     * Return the color of the team owning the movable board
     * @return the color of the team owning the movable board
     */
    public Color getTeamColor() {
        return this.teamColor;
    }
    /**
     * Set the color of the team owning the movable board
     * @param teamColor the color of the team owning the movable board
     */
    public void setTeamColor(Color teamColor){
        this.teamColor = teamColor;
    }
    /**
     * Move the movable board of the current arrowbutton and all chess pieces on it to the arrowbutton passed by parameter
     * @param ab The arrowbutton where to move the movable board
     */
    public void changeToMovable(ArrowButton ab){
        for(int i = 0; i < 4; ++i)
        {  
            if(this.linkedBoxes.get(i).getPiece() != null)
                this.linkedBoxes.get(i).getPiece().moveTo(ab.linkedBoxes.get(i));
            this.linkedBoxes.get(i).setVisible(false);
            this.linkedBoxes.get(i).setPiece(null);
            ab.linkedBoxes.get(i).setVisible(true);
        }
    }
    /**
     * Return the number of chess pieces on the movable board
     * @return the number of chess pieces on the movable board
     */
    public int nbOnMovable(){
        int count = 0;
        for(int i = 0; i < 4; ++i)
            if(this.linkedBoxes.get(i).getPiece() != null)
                ++count;
        
        return count;
    }
    /**
     * Return the first (or only) chess piece on the movable board
     * @return the first (or only) chess piece on the movable board
     */
    public ChessPiece getPieceOnMovable(){
        for(int i = 0; i < 4; ++i)
            if(this.linkedBoxes.get(i).getPiece() != null)
                return this.linkedBoxes.get(i).getPiece() ;
        
        return null;
    }
}