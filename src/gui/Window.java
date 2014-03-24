package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import map.ChessPiece;
/**
 * This class allows to display the main window.
 * 
 * @author NOVAK Johann
 * 		johann.novak@utbm.fr
 * @author SCHULZ Quentin
 * 		quentin.schulz@utbm.fr
 * 
 * @version v0.1
 */
public class Window extends JFrame implements ActionListener{
    
	private static final long serialVersionUID = 1L;
	JPanel panel;
    JPanel panTop;
    JPanel panBottom;
    JPanel panCenter;
    JPanel panGame;
    
    JButton b1;
    JButton b2;
    ButtonPlayer b3;
    
    MenuLab menuLabel;
    TitleLabel title;
    GameWindow gwin;
    
    /**
     * Create a new window
     */
    public Window(){
        this.setTitle("Star Trek 3D Chess Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,600);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        panel = new JPanel();
        panTop = new JPanel();
        panBottom = new JPanel();
        panCenter = new JPanel();
        panGame = new JPanel();
        
        b1 = new JButton("START A NEW GAME");
        b2 = new JButton("QUIT");
        b3 = new ButtonPlayer();
        
        menuLabel = new MenuLab("MENU");
        title = new TitleLabel("STAR TREK 3D CHESS SIMULATION");
        //Set the layout
        MainPage();
        this.setVisible(true);
    }
    /**
     * Called when the button Play is clicked
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        go();
        b3.setEnabled(false);
        b1.setEnabled(false);       
        
    }
    /**
     * Create a new GameWindow and ensure it will stay on focus
     */
    public void go(){
        
        gwin = new GameWindow();
        gwin.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {

		        b3.setEnabled(false);
		        b1.setEnabled(false);
			}
			
			@Override
			public void windowIconified(WindowEvent e) {

				gwin.toFront();
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {

				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {

				gwin.toFront();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {

				b3.setEnabled(true);
		        b1.setEnabled(true);
		        ChessPiece.clear();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {

			}
			
			@Override
			public void windowActivated(WindowEvent e) {

				gwin.toFront();
			}
		});
    }
    
    
    /**
     * Set the layout of the window
     */
    public void MainPage(){
        this.panel.setLayout(new BorderLayout());
        this.panBottom.setLayout(new GridLayout(2,3)); 
        this.panCenter.setLayout(new BorderLayout());
        
        this.setContentPane(this.panel);
        
        this.title.setHorizontalAlignment(ImageObserver.WIDTH/2);        
        
        this.panBottom.setBackground(Color.BLACK);
        this.panBottom.add(new JLabel());
        this.panBottom.add(menuLabel);          
        this.panBottom.add(new JLabel());
        this.panBottom.add(b1);
        this.panBottom.add(b3);
        this.panBottom.add(b2);  
        
        this.panCenter.add(new JLabel(new ImageIcon("LP24good.png")));

        b1.addActionListener(this);
        b2.addActionListener(new QuitListener());
        b3.addActionListener(b3);
         
        this.panel.add(title, BorderLayout.PAGE_START);
        this.panel.add(panBottom,BorderLayout.PAGE_END);
        this.panel.add(this.panCenter,BorderLayout.CENTER);

        
    }
}
