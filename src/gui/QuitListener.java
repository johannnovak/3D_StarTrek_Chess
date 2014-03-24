package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class defines the behavior of a click on a QuitButton (exit the game).
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */

public class QuitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent ae) {
            System.exit(0);
    }
    
}
