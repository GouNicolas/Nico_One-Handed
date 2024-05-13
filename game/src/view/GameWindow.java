package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// other
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
// javax.swing
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Card;
// import model classes
import model.Game;
import model.Rank;
import model.Suit;

/**
 * Class to create the game window
 * 
 */
public class GameWindow extends JFrame{
	protected static final long serialVersionUID = 2L;

	

	// game instance
	protected Game game = new Game();

	//constants for the cosmetics
	protected Color COLOR_CREAM = new Color(250, 225, 225);
	protected Color COLOR_PINK_PURPLE = new Color(225, 175, 209);
	protected Color COLOR_LIGHT_PURPLE = new Color(173, 136, 198);
	protected Color COLOR_PURPLE = new Color(116, 105, 182);
	protected Font FONT_TEXT = new Font("Carlito", Font.PLAIN, 20);
	protected Font FONT_TEXT_BIG = new Font("Carlito", Font.BOLD, 24);
	protected Dimension LATERAL_PANEL_DIMENSION = new Dimension(220, 600);
	protected Dimension HAND_PANEL_DIMENSION = new Dimension(1160, 600);
	protected Dimension BUTTONS_PANEL_DIMENSION = new Dimension(1600, 200);
	protected Dimension INFO_PANEL_DIMENSION = new Dimension(1600, 80);

	// window elements
	protected JPanel handPanel;
	protected JPanel deckPanel;
	protected JPanel discardPanel;
	protected JPanel InfoPanel;
	protected JPanel buttonsPanel;
	protected ImageIcon backgroundImage;

	// game elements
	protected JButton showback;
	protected JButton buttonDraw;
	protected JButton buttonRank;
	protected JButton buttonSuits;
	protected JButton buttonJoker;

	protected JLabel cardDisplay1;
	protected JLabel cardDisplay2;



	/**
	 * Constructor
	 */
	public GameWindow(Game g) {
		// Initialize the game
		game = g;
		// Initialize the panels

		// set the background image for the hand panel
		backgroundImage = new ImageIcon(GameWindow.class.getResource("/resources/window_bg.png"));
		Image img = backgroundImage.getImage();
		handPanel = new JPanel() { // Anonymous class to override the paintComponent method
			@Override
			protected void paintComponent(Graphics g) { 
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		
		deckPanel = new JPanel();
		discardPanel = new JPanel();
		InfoPanel = new JPanel();
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
		this.setMinimumSize(new Dimension(1600, 900));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new GridBagLayout());
		this.setLocationRelativeTo(null);
		

		// Add the panel to the window
		// we use a GridBagLayout to mimic the BorderLayout because we had issues with the BorderLayout not displaying the panels correctly
		// Create the layout and constraints
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints constraints = new GridBagConstraints();
		this.setLayout(layout);
		// Set the insets to 0
		constraints.insets = new Insets(0, 0, 0, 0);
		// Mimic BorderLayout.NORTH for InfoPanel
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 1.0;
		constraints.weighty = 0;
		this.add(InfoPanel, constraints);

		// Mimic BorderLayout.WEST for deckPanel
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.weightx = 0;
		constraints.weighty = 1.0;
		this.add(deckPanel, constraints);

		// Mimic BorderLayout.CENTER for handPanel
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		this.add(handPanel, constraints);

		// Mimic BorderLayout.EAST for discardPanel
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.weighty = 1.0;
		constraints.weightx = 0;
		this.add(discardPanel, constraints);

		// Mimic BorderLayout.SOUTH for buttonsPanel
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 0;
		this.add(buttonsPanel, constraints);

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
		
		// configure the panel for the buttons
		buttonsPanel.setMinimumSize(BUTTONS_PANEL_DIMENSION);
		buttonsPanel.setLayout(new GridLayout());
		buttonsPanel.setBackground(COLOR_CREAM);

		// configure the panel for the score and the jokers left
		InfoPanel.setMinimumSize(INFO_PANEL_DIMENSION);
		FlowLayout infoLayout = new FlowLayout();
		infoLayout.setHgap(100);
		infoLayout.setVgap(20);
		InfoPanel.setLayout(infoLayout);
		InfoPanel.setBackground(COLOR_PURPLE);
		InfoPanel.setAlignmentY(CENTER_ALIGNMENT);
		

		// configure the panel for the deck
		deckPanel.setBackground(COLOR_CREAM);
		deckPanel.setLayout(new GridBagLayout());
		deckPanel.setMinimumSize(LATERAL_PANEL_DIMENSION);

		// configure the panel for the hand
		handPanel.setMinimumSize(new Dimension(1000,400));
		handPanel.setBorder(BorderFactory.createLineBorder(COLOR_PINK_PURPLE, 4));
		handPanel.setLayout(new FlowLayout());

		// configure the panel for the discard pile
		discardPanel.setBackground(COLOR_CREAM);
		discardPanel.setMinimumSize(LATERAL_PANEL_DIMENSION);
		discardPanel.setLayout(new FlowLayout());

		// deckdisplay
		deckDisplay();

		// discard display
		discardDisplay();

		// score display
		infoDisplay();

		// add the buttons to the buttons panel
		addActionButtons();

		// debug
		debug();

		// display the hand
		displayHand();

		// make the window visible
		this.setVisible(true);
	}
	protected void addActionButtons() {
		// add the buttons to the buttons panel
		// configure a draw button
		buttonDraw = new JButton("Draw");
		buttonDraw.setFont(FONT_TEXT);
		buttonDraw.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				game.draw();
				displayHand();
			}
		});
		buttonsPanel.add(buttonDraw);
		
		// configure a debug button
		showback = new JButton("back of a card");
		showback.setFont(FONT_TEXT);
		showback.setSize(new Dimension(300,100));
		getContentPane().add(showback);
		showback.addActionListener(e -> cardBack(e));
		buttonsPanel.add(showback);

		// change font for the buttons in buttonsPanel
		for (Component component : buttonsPanel.getComponents()) {
			if (component instanceof JButton) {
				component.setFont(FONT_TEXT_BIG);
				component.setBackground(COLOR_PURPLE);
				component.setForeground(Color.WHITE);
				component.setPreferredSize(new Dimension(180, 60));
			}
		}
	}
	// Function to display a warning message
    protected void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
	public void cardBack(ActionEvent e) {
		System.out.println("Button clicked");
		cardDisplay1.setIcon(new ImageIcon(GameWindow.class.getResource("/resources/cardback.png")));
	}
	protected void deckDisplay() {
		// clear the deck panel
		deckPanel.removeAll();
		// display the remaining cards in the deck
		if (game.getDeck().length() > 0) {
			JLabel deckLabel = new JLabel("Remaining Cards: " + game.getDeck().length());
			deckLabel.setFont(FONT_TEXT);
			JLabel card_back = new JLabel(new ImageIcon(GameWindow.class.getResource("/resources/cardback.png")));
			card_back.setSize(200, 328);
			GridBagConstraints constraints = new GridBagConstraints();

			// Add card_back at (0, 0)
			deckPanel.add(card_back, constraints);

			// Add deckLabel at (0, 1)
			constraints.gridy = 1;
			deckPanel.add(deckLabel, constraints);
		}
		// display "No card" if the deck is empty
		else {
			JLabel no_card = new JLabel("No card");
			no_card.setBounds(0, 0, 200, 328);
			deckPanel.add(no_card);
		}
	}
	protected void infoDisplay() {
		// display the score and the jokers left
		JLabel scoreLabel = new JLabel("Score: " + game.getScore());
		scoreLabel.setFont(FONT_TEXT_BIG);
		scoreLabel.setForeground(Color.WHITE);
		JLabel jokersLabel = new JLabel("Jokers left: " + game.getJokersLeft());
		jokersLabel.setFont(FONT_TEXT_BIG);
		jokersLabel.setForeground(Color.WHITE);
		InfoPanel.add(scoreLabel);
		InfoPanel.add(jokersLabel);
	}
	protected void playRank() {
		
	}
	protected void discardDisplay() {
		// remove all the components from the discard panel
		discardPanel.removeAll();
		// display "No card" if the discard pile is empty
		if (game.getDiscard().length() == 0) {
			JLabel no_card = new JLabel("No card");
			no_card.setFont(FONT_TEXT);
			no_card.setSize(getPreferredSize());
			discardPanel.add(no_card);
		}
		// display the top card of the discard pile
		else {
			Card last_card = game.getDiscard().getCard(game.getDiscard().length()-1);
			ViewCard card = new ViewCard(last_card.getRank(), last_card.getSuit());
			discardPanel.removeAll();
			discardPanel.add(new JLabel(new ImageIcon(GameWindow.class.getResource(card.imagePath))));
		}
	}
	/*
	 * Function used to track some really weird NPException that not even the debugger could help with
	 */
	public void debug() {
		System.out.println(handPanel);
		System.out.println("Debugging");
		if (handPanel == null) {
			System.out.println("handPanel is null");
		} else {
			System.out.println("handPanel is not null");
		}
		if (game.getHand() == null) {
			System.out.println("game.getHand() is null");
		} else {
			System.out.println("game.getHand() is not null");
		}
		if (deckPanel == null) {
			System.out.println("deckPanel is null");
		} else {
			System.out.println("deckPanel is not null");
		}
		if (discardPanel == null) {
			System.out.println("discardPanel is null");
		} else {
			System.out.println("discardPanel is not null");
		}
		if (InfoPanel == null) {
			System.out.println("InfoPanel is null");
		} else {
			System.out.println("InfoPanel is not null");
		}
		if (buttonsPanel == null) {
			System.out.println("buttonsPanel is null");
		} else {
			System.out.println("buttonsPanel is not null");
		}
		if (buttonDraw == null) {
			System.out.println("buttonDraw is null");
		} else {
			System.out.println("buttonDraw is not null");
		}
		if (showback == null) {
			System.out.println("showback is null");
		} else {
			System.out.println("showback is not null");
		}
		if (buttonRank == null) {
			System.out.println("buttonRank is null");
		} else {
			System.out.println("buttonRank is not null");
		}
		if (buttonSuits == null) {
			System.out.println("buttonSuits is null");
		} else {
			System.out.println("buttonSuits is not null");
		}
		if (cardDisplay1 == null) {
			System.out.println("cardDisplay1 is null");
		} else {
			System.out.println("cardDisplay1 is not null");
		}

	}
	protected void clearHand() {
		handPanel.removeAll();
	}
	protected void displayHand(){
		System.out.println("Displaying hand");
		debug();
		// list the cards in the hand
		ArrayList<String> cardImages = new ArrayList<String>();
		int number_of_cards_to_display;
		if (game.getHand().getHandSize() < 4) {
			number_of_cards_to_display = game.getHand().getHandSize();
		}
		else {
			number_of_cards_to_display = 4;
		}
		for (int i = 0; i < number_of_cards_to_display; i++) {
			ViewCard card = new ViewCard(game.getHand().getCard(i).getRank(), game.getHand().getCard(i).getSuit());
			cardImages.add(card.imagePath);
		}
		// clear the hand panel
		clearHand();
	
		// display the cards in the hand
		for (String cardImage : cardImages) {
			ImageIcon imageIcon = new ImageIcon(GameWindow.class.getResource(cardImage));
			Image image = imageIcon.getImage();
			Image scaledImage = image.getScaledInstance(200, 328, Image.SCALE_SMOOTH);
			ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
			JLabel cardLabel = new JLabel(scaledImageIcon);
			handPanel.add(cardLabel);
		}
		handPanel.revalidate();
		handPanel.repaint();
		deckDisplay();
		discardDisplay();
	}
	
}

