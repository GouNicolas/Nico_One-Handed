package view;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class TestWindow extends JFrame implements ActionListener {

	//JFrame class wants its subclasses to have a "unique ID",
	//and code interfaces force us to do so to avoid a warning
	//Just put whatever "long" int number here.
	private static final long serialVersionUID = 2L;
	
	//Entities in the window that I will need to access outside
	//constructor were modified to be attributes of the class, that way I
	//can access them in any method
	protected JButton buttonSpades;
	protected JButton buttonHearts;
	protected JButton buttonDiamonds;
	protected JButton buttonClubs;
	protected JLabel cardDisplay;
	
	/**
	 * Constructor
	 */
	public TestWindow() {
		
		// ...

		this.setSize(1920,1080);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Swing Test Window");
		this.setResizable(false);
		this.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.setLocationRelativeTo(null);

		// Set the background image of the window
		ImageIcon backgroundImage = new ImageIcon(TestWindow.class.getResource("/resources/window_bg.png"));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		backgroundLabel.setOpaque(false);
		this.getLayeredPane().add(backgroundLabel, Integer.valueOf(Integer.MIN_VALUE));
		

		
		//Code created by WindowBuilder Editor. Only mofification
		//was changing some local variables to attributes, so we can access
		//them in other methods
		buttonSpades = new JButton("Change to Spades");
		buttonSpades.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonSpades.setBounds(503, 22, 223, 85);
		getContentPane().add(buttonSpades);
		
		buttonHearts = new JButton("Change to Hearts");
		buttonHearts.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonHearts.setBounds(503, 125, 223, 85);
		getContentPane().add(buttonHearts);
		
		buttonDiamonds = new JButton("Change to Diamonds");
		buttonDiamonds.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonDiamonds.setBounds(503, 235, 223, 85);
		getContentPane().add(buttonDiamonds);
		
		buttonClubs = new JButton("Change to Clubs");
		buttonClubs.setFont(new Font("Tahoma", Font.BOLD, 16));
		buttonClubs.setBounds(503, 344, 223, 85);
		getContentPane().add(buttonClubs);
		
		cardDisplay = new JLabel("");
		cardDisplay.setIcon(new ImageIcon(TestWindow.class.getResource("/resources/KS.png")));
		cardDisplay.setBounds(138, 62, 200, 328);
		getContentPane().add(cardDisplay);
		getContentPane().add(cardDisplay);
		
		//Manual input : we create listeners on all 4 buttons
		//When clicked, they will trigger "actionPerformed" method on
		//the object set as parameter (here : "this")
		buttonSpades.addActionListener(this);
		buttonHearts.addActionListener(this);
		buttonDiamonds.addActionListener(this);
		buttonClubs.addActionListener(this);
		
		//Manual input : window must be set visible, or it won't display
		//This should be done at the end, otherwise some elements might 
		//not properly appear (images in particular)
		this.setVisible(true);
	}

	/**
	 * Buttons can send their "click" event to classes that have
	 * "ActionListener" interface. Why? Because they "know" those classes
	 * will be forced to have an "actionPerformed" method to call.
	 * 
	 * They will call it when they are clicked thanks to listeners set 
	 * above in constructor
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		//All buttons call the same finction, so we have to test
		//for the source of the event, and react accordingly.
		
		//Here, "reaction" is to change the path of the image we'll load afterwards
		String imagePath ="";
		if(e.getSource() == buttonSpades) {
			imagePath = "/resources/KS.png";
		}
		else if(e.getSource() == buttonHearts) {
			imagePath = "/resources/KH.png";
		}
		else if(e.getSource() == buttonDiamonds) {
			imagePath = "/resources/KD.png";
		}
		else
			imagePath = "/resources/KC.png";
		
		//This line will change the image by fetching in "resources" package
		this.cardDisplay.setIcon(new ImageIcon(TestWindow.class.getResource(imagePath)));
	}
	public void setCardDisplay(String imagePath) {
		this.cardDisplay.setIcon(new ImageIcon(TestWindow.class.getResource(imagePath)));
	}
}
