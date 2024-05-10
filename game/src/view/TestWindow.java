package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Game;
import model.Rank;
import model.Suit;


public class TestWindow extends JFrame{


	//JFrame class wants its subclasses to have a "unique ID",
	//and code interfaces force us to do so to avoid a warning
	//Just put whatever "long" int number here.
	private static final long serialVersionUID = 2L;
	
	//Entities in the window that I will need to access outside
	//constructor were modified to be attributes of the class, that way I
	//can access them in any method
	protected JButton showback;
	protected JButton buttonDraw;
	protected JButton buttonDiamonds;
	protected JButton buttonClubs;
	protected JLabel cardDisplay1;
	protected Game game;
	protected int indexButtons = 0;
	protected Color COLOR_CREAM = new Color(173, 136, 198);
	protected Color COLOR_PURPLE = new Color(116, 105, 182);
	protected Color COLOR_LIGHT_PURPLE = new Color(173, 136, 198);
	protected JPanel handPanel;
	protected JPanel deckPanel;
	protected JPanel discardPanel;
	protected JPanel scorePanel;
	protected JPanel buttonsPanel;


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
		this.setTitle("Swing Test Window");
		this.setResizable(true);
		this.setMinimumSize(new Dimension(1200, 775));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		

		// add a new look and feel to the window
		UIManager.put("nimbusBase", COLOR_PURPLE);
		UIManager.put("nimbusBlueGrey", COLOR_LIGHT_PURPLE );
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
		

		// Set the background image of the window
		ImageIcon backgroundImage = new ImageIcon(TestWindow.class.getResource("/resources/window_bg.png"));
		JLabel backgroundLabel = new JLabel(backgroundImage);
		backgroundLabel.setBounds(0, 0, this.getWidth(), this.getHeight());

		// Make sure the label fully stretches
		backgroundLabel.setOpaque(true);
		backgroundLabel.setLayout(new BorderLayout());

		// Add the label to the JFrame
		this.setContentPane(backgroundLabel);
		
		
		// create a panel for the buttons
		buttonsPanel.setMinimumSize(new Dimension(1200,428));
		buttonsPanel.setLayout(new FlowLayout());
		getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

		// create a panel for the hand
		handPanel.setMinimumSize(new Dimension(1200, 348));
		handPanel.setLayout(new GridLayout(1, 4));
		getContentPane().add(handPanel);

		// create a panel for the score
		scorePanel.setMinimumSize(new Dimension(1200, 50));
		scorePanel.setLayout(new FlowLayout());
		scorePanel.setBackground(COLOR_CREAM);
		getContentPane().add(scorePanel, BorderLayout.NORTH);

		// create a panel for the deck
		discardPanel.setSize(new Dimension(220, 300));
		deckPanel.setBackground(COLOR_CREAM);
		deckPanel.setLayout(null);
		getContentPane().add(deckPanel, BorderLayout.WEST);

		// create a panel for the discard pile
		discardPanel.setBackground(COLOR_CREAM);
		discardPanel.setSize(new Dimension(220, 300));
		discardPanel.setLayout(null);
		getContentPane().add(discardPanel, BorderLayout.EAST);

		// create buttons and set their properties
		showback = new JButton("back of a card");
		showback.setFont(new Font("Tahoma", Font.BOLD, 16));
		showback.setBounds(503, 22, 223, 85);
		getContentPane().add(showback);
		

		cardDisplay1 = new JLabel("");
		ViewCard cardKS = new ViewCard(Rank.KING, Suit.SPADES);
		cardDisplay1.setIcon(new ImageIcon(TestWindow.class.getResource(cardKS.imagePath)));
		cardDisplay1.setSize(200, 328);

		handPanel.add(cardDisplay1);
		handPanel.validate();
		handPanel.repaint();

		// add the buttons to the panel
		showback.addActionListener(e -> cardBack(e));
		buttonsPanel.add(showback);

		// change font
		Font newFont = new Font("Carlito", Font.PLAIN, 20);
		for (Component component : buttonsPanel.getComponents()) {
			if (component instanceof JButton) {
				component.setFont(newFont);
			}
		}
		// show the score
		JLabel scoreLabel = new JLabel("Score: " + game.getScore());
		scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		scorePanel.add(scoreLabel);
		// deckdisplay
		deckDisplay();

		// discard display
		discardDisplay();

		// make the window visible
		this.setVisible(true);
	}
	public void cardBack(ActionEvent e) {
		System.out.println("Button clicked");
		cardDisplay1.setIcon(new ImageIcon(TestWindow.class.getResource("/resources/cardback.png")));
	}
	private void deckDisplay() {
		int deckSize = game.getDeck().length();
		int a = 0;
		if (deckSize > 0){
			for (int i = 0; i < deckSize; i++) {
				JLabel one_card_of_the_deck = new JLabel(new ImageIcon(TestWindow.class.getResource("/resources/cardback.png")));
				one_card_of_the_deck.setBounds(a, a, 200, 328);
				deckPanel.add(one_card_of_the_deck);
				a += 1;
			}
		}
	}

	private void discardDisplay() {
		if (game.getDiscard().length() == 0) {
			JLabel no_card = new JLabel("No card in the discard pile");
			no_card.setBounds(0, 0, 200, 328);
			discardPanel.add(no_card);
		}
		else {ViewCard card = new ViewCard(game.getDiscard().getCard(0).getRank(), game.getDiscard().getCard(0).getSuit());
			discardPanel.removeAll();
			discardPanel.add(new JLabel(new ImageIcon(TestWindow.class.getResource(card.imagePath))));
		}
	}
}
