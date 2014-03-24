package gui;

import javax.swing.JLabel;

/**
 * This class allows to display a Label with a specific style (center).
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class MenuLab extends JLabel{
	private static final long serialVersionUID = 1L;

	public MenuLab(String string){
        this.setText(string);
        this.setHorizontalAlignment(CENTER);
    }
}
