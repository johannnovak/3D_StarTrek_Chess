
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * This class allows to chose between two players which one will start the game.
 * 
 * /!\Not currently implemented
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class ButtonPlayer extends JButton implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a new ButtonPlayer whose text is "White Team   (Change)"
	 */
	public ButtonPlayer(){
        this.setText("White Team   (Change)");
    }
	/**
	 * If the ButtonPlayer is clicked, switch from "White Team   (Change)" to "Black Team   (Change)" and vice-versa
	 */
    @Override
    public void actionPerformed(ActionEvent ae) {   
        if(this.getText() == "White Team   (Change)"){
            this.setText("Black Team   (Change)");
        }else{
            this.setText("White Team   (Change)");
        }
    }
}