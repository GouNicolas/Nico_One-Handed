package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Game;


public class TestWindow extends JFrame{


	//JFrame class wants its subclasses to have a "unique ID",
	//and code interfaces force us to do so to avoid a warning
	//Just put whatever "long" int number here.
	protected static final long serialVersionUID = 2L;
	
	//Entities in the window that I will need to access outside
	//constructor were modified to be attributes of the class, that way I
	//can access them in any method
	
	protected Game game;

	//constants for the cosmetics
	protected Color COLOR_CREAM = new Color(173, 136, 198);
	protected Color COLOR_PURPLE = new Color(116, 105, 182);
	protected Color COLOR_LIGHT_PURPLE = new Color(173, 136, 198);
	protected Font FONT_TEXT = new Font("Carlito", Font.PLAIN, 20);
	
	
	// window elements
	protected JPanel handPanel;
	protected JPanel deckPanel;
	protected JPanel discardPanel;
	protected JPanel scorePanel;
	protected JPanel buttonsPanel;
	protected JButton showback;
	protected JButton buttonDraw;
	protected JButton buttonDiamonds;
	protected JButton buttonClubs;
	protected JLabel cardDisplay1;

	protected AbstractButton overlayoutPanel;

	protected JButton jButton1;

	/**
	 * Constructor
	 */
	public TestWindow() {
		// Create a new game
		game = new Game();

		// Initialize the panels
		handPanel = new JPanel();
		deckPanel = new JPanel();
		discardPanel = new JPanel();
		scorePanel = new JPanel();
		buttonsPanel = new JPanel();

		// Set the window properties
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("One-Handed Solitaire");
		try {
			this.setIconImage(ImageIO.read(getClass().getResource("/resources/icon.png")));
		} catch (IOException e) {
			System.out.println("Window Icon not found");
			e.printStackTrace();
		}
		this.setResizable(true);
		this.setMinimumSize(new Dimension(1200, 775));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		

		// add a new look and feel to the window
		UIManager.put("nimbusBase", COLOR_PURPLE);
		UIManager.put("nimbusBlueGrey", COLOR_LIGHT_PURPLE);
		UIManager.put("control", COLOR_CREAM);
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
/*
		// Set the background image of the window
		ImageIcon backgroundImage = new ImageIcon(TestWindow.class.getResource("/resources/window_bg.png"));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
		backgroundLabel.setOpaque(true);
		backgroundLabel.setLayout(new BorderLayout());
		this.setContentPane(backgroundLabel); */
		
		
		// configure the panel for the buttons
		buttonsPanel.setMinimumSize(new Dimension(1200,200));
		buttonsPanel.setLayout(new FlowLayout());
		this.add(buttonsPanel, BorderLayout.SOUTH);

		// configure the panel for the hand
		//handPanel.setMinimumSize(new Dimension(4/6*getWidth(), 4/6*getHeight()));
		/* FlowLayout layout = new FlowLayout();
		layout.setHgap(10);
		layout.setVgap(20);
		handPanel.setLayout(layout);
		handPanel.setVisible(true);
		this.add(handPanel, BorderLayout.CENTER); */

		handPanel.setVisible(true);
		System.out.println("handPanel visibility: " + handPanel.isVisible());
		// Add elements to the panel
		handPanel.add(new JLabel("Card 1"));
		System.out.println("Added Card 1");
		handPanel.add(new JLabel("Card 2"));
		System.out.println("Added Card 2");

		// Add the panel to the frame
		this.add(handPanel, BorderLayout.CENTER);
		System.out.println("Added handPanel to frame");

		// configure the panel for the score
		scorePanel.setMinimumSize(new Dimension(1200, 50));
		scorePanel.setLayout(new FlowLayout());
		scorePanel.setBackground(COLOR_CREAM);
		this.add(scorePanel, BorderLayout.NORTH);

		// create a panel for the deck
		deckPanel.setBackground(COLOR_CREAM);
		deckPanel.setLayout(new FlowLayout());
		deckPanel.setPreferredSize(new Dimension(250,400));
		getContentPane().add(deckPanel, BorderLayout.WEST);

		// create a panel for the discard pile
		discardPanel.setBackground(COLOR_CREAM);
		discardPanel.setPreferredSize(new Dimension(220, 328));
		discardPanel.setLayout(new FlowLayout());
		getContentPane().add(discardPanel, BorderLayout.EAST);

		// create buttons and set their properties
		showback = new JButton("back of a card");
		showback.setFont(FONT_TEXT);
		showback.setSize(new Dimension(300,100));
		getContentPane().add(showback);
		
		// create a card
	/* 	cardDisplay1 = new JLabel();
		ViewCard cardKS = new ViewCard(Rank.KING, Suit.SPADES);
		cardDisplay1.setIcon(new ImageIcon(TestWindow.class.getResource(cardKS.imagePath)));
		cardDisplay1.setSize(200, 328);

		JLabel cardDisplay2 = new JLabel("test");
		handPanel.add(cardDisplay2);
		handPanel.add(cardDisplay1);

		handPanel.validate();
		handPanel.repaint(); */


		// add the buttons to the panel
		showback.addActionListener(e -> cardBack(e));
		buttonsPanel.add(showback);

		// change font for the buttons in buttonsPanel
		for (Component component : buttonsPanel.getComponents()) {
			if (component instanceof JButton) {
				component.setFont(FONT_TEXT);
			}
		}
		// show the score
		JLabel scoreLabel = new JLabel("Score: " + game.getScore());
		scoreLabel.setFont(FONT_TEXT);
		scorePanel.add(scoreLabel);
		
		
		// deckdisplay
		deckDisplay();

		// discard display
		discardDisplay();

		// display the hand
		//displayHand();

		// make the window visible
		this.setVisible(true);
	}
	public void cardBack(ActionEvent e) {
		System.out.println("Button clicked");
		cardDisplay1.setIcon(new ImageIcon(TestWindow.class.getResource("/resources/cardback.png")));
	}
	protected void deckDisplay() {
		if (game.getDeck().length() > 0) {
			JLabel deckLabel = new JLabel("Remaining Cards: " + game.getDeck().length());
			deckLabel.setFont(FONT_TEXT);
			JLabel one_card_of_the_deck = new JLabel(new ImageIcon(TestWindow.class.getResource("/resources/cardback.png")));
			one_card_of_the_deck.setSize(200, 328);
			deckPanel.add(one_card_of_the_deck);
			deckPanel.add(deckLabel);
		}
		else {
			JLabel no_card = new JLabel("No card in the deck");
			no_card.setBounds(0, 0, 200, 328);
			deckPanel.add(no_card);
		}
	}

	protected void discardDisplay() {
		if (game.getDiscard().length() == 0) {
			JLabel no_card = new JLabel("No card");
			no_card.setFont(FONT_TEXT);
			no_card.setSize(getPreferredSize());
			discardPanel.add(no_card);
		}
		else {ViewCard card = new ViewCard(game.getDiscard().getCard(0).getRank(), game.getDiscard().getCard(0).getSuit());
			discardPanel.removeAll();
			discardPanel.add(new JLabel(new ImageIcon(TestWindow.class.getResource(card.imagePath))));
		}
	}
	protected void displayHand(){
		// list the cards in the hand
		ArrayList<String> cardImages = new ArrayList<String>();
		for (int i = 0; i < game.getHand().getHandSize(); i++) {
			ViewCard card = new ViewCard(game.getHand().getCard(i).getRank(), game.getHand().getCard(i).getSuit());
			cardImages.add(card.imagePath);
		}
		for (String cardImage : cardImages) {
			try {
				BufferedImage card = ImageIO.read(getClass().getResource(cardImage));
				JLabel cardLabel = new JLabel(new ImageIcon(card));
				handPanel.add(cardLabel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		handPanel.validate();
		handPanel.repaint();
	}
}
