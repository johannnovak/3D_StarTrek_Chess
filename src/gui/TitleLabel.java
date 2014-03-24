package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
/**
 * This class allows to display a Label with a specific style (Arial BOLD size 50).
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class TitleLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	Font font = new Font("Arial",Font.BOLD,50);
    
    public TitleLabel(String string){
         super(string);
         this.setHorizontalTextPosition(CENTER);
         this.setBackground(Color.white);
         
    }
    
    @Override
    protected void paintChildren(Graphics g){
        g.setFont(font);
            this.repaint();
    }
}
