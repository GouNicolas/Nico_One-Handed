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
	protected Dimension LATERAL_PANEL_DIMENSION = new Dimension(240, 400);
	protected Dimension HAND_PANEL_DIMENSION = new Dimension(900, 400);
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
	protected JButton buttonSuit;
	protected JButton buttonJoker;

	protected JLabel cardDisplay1;
	protected JLabel cardDisplay2;



	private JButton buttonClose;



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
		this.setMinimumSize(new Dimension(1340, 700));
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
		handPanel.setLayout(new GridBagLayout());

		// configure the panel for the discard pile
		discardPanel.setBackground(COLOR_CREAM);
		discardPanel.setMinimumSize(LATERAL_PANEL_DIMENSION);
		discardPanel.setLayout(new GridBagLayout());

		// deckdisplay
		displayDeck();

		// discard display
		displayDiscard();

		// score display
		displayInfo();

		// add the buttons to the buttons panel
		addActionButtons();  

		// display the hand
		displayHand();

		// make the window visible
		this.setVisible(true);
	}
	protected void addActionButtons() {
		// add the buttons to the buttons panel
		// configure the rank button
		buttonRank = new JButton("Same Rank");
		buttonRank.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
				playRank();
			}
		});
		// configure the suit button
		buttonSuit = new JButton("Same Suit");
		buttonSuit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				playSuit();
			}
		});
		// configure a draw button
		buttonDraw = new JButton("Draw");
		buttonDraw.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playDraw();
			}
		});
		// configure the Joker button
		buttonJoker = new JButton("Joker");
		buttonJoker.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				playJoker();
			}
		});
		
		//add the buttons
		buttonsPanel.add(buttonRank);
		buttonsPanel.add(buttonSuit);
		buttonsPanel.add(buttonJoker);
		buttonsPanel.add(buttonDraw);

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
	protected void displayDeck() {
		// clear the deck panel
		deckPanel.removeAll();
		// display the remaining cards in the deck
		if (game.getDeck().length() > 0) {
			JLabel deckLabel = new JLabel("Remaining Cards: " + game.getDeck().length());
			deckLabel.setFont(FONT_TEXT_BIG);
			deckLabel.setSize(new Dimension(220,200));
			JLabel card_back = new JLabel(new ImageIcon(GameWindow.class.getResource("/resources/cardback.png")));
			card_back.setSize(200, 328);
			deckPanel.add(card_back);
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridy= 1;
			deckPanel.add(deckLabel,constraints);
		}
		// display "No card" if the deck is empty
		else {
			JLabel no_card = new JLabel("No card");
			no_card.setFont(FONT_TEXT_BIG);
			no_card.setSize(new Dimension(220,200));
			deckPanel.add(no_card);
		}
	}
	protected void displayInfo() {
		InfoPanel.removeAll();
		// display the score and the jokers left
		JLabel scoreLabel = new JLabel("Score: " + game.getScore());
		scoreLabel.setFont(FONT_TEXT_BIG);
		scoreLabel.setForeground(Color.WHITE);
		JLabel jokersLabel = new JLabel("Jokers left: " + game.getJokersLeft());
		jokersLabel.setFont(FONT_TEXT_BIG);
		jokersLabel.setForeground(Color.WHITE);
		// add a button 
		buttonClose = new JButton("Save and Close");
		buttonClose.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				game.saveGame();
				System.exit(0);
			}
		});
		buttonClose.setFont(FONT_TEXT_BIG);
		buttonClose.setSize(new Dimension(150,100));
		buttonClose.setForeground(Color.WHITE);
		InfoPanel.add(buttonClose);
		InfoPanel.add(scoreLabel);
		InfoPanel.add(jokersLabel);
	}
	protected void playRank() {
		winIsGameOver();
		// if it is not the case
		if(!(game.getHand().getCard(0).rankEquals(game.getHand().getCard(3)))){
			showWarning("The first and last card don't have the same Rank");
		}
		else{
			game.handleRankCase();
			game.fillHand();
			displayInfo();
			displayHand();
		}
	}
	protected void winIsGameOver(){
		if (game.isGameOver()){
			int score = game.getScore(); // Assume you have a getScore method
			Object[] options = {"New Game", "End the app"};
	
			int response = JOptionPane.showOptionDialog(null, "Congratulations! Your score is " + score, "Game Over", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	
			if (response == 0) {
				// Start a new game when the "New Game" button is clicked
				game = new Game();
				displayHand();
				displayDiscard();
				displayDeck();
				displayInfo();
			} else if (response == 1) {
				// Close the window when the "End the app" button is clicked
				System.exit(0);
			}
		}
	}
	/* protected void winIsGameOver(){
		if (game.isGameOver()){
			int score = game.getScore(); // Assume you have a getScore method
			JOptionPane.showMessageDialog(null, "Congratulations! Your score is " + score, "Game Over", JOptionPane.QUESTION_MESSAGE);
			
			JButton newGameButton = new JButton("New Game");
			newGameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Start a new game when the button is clicked
					game = new Game();
					displayHand();
					displayDiscard();
					displayDeck();
					displayInfo();
				}
			});
	
			JButton closeButton = new JButton("End the app");
			closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// Close the window when the button is clicked
					System.exit(0);
				}
			});
	
			// Add the buttons to the window
			this.add(newGameButton);
			this.add(closeButton);
		}
	} */
	protected void playDraw(){
		winIsGameOver();
		game.draw();
		displayHand();
	}
	protected void playJoker(){
		winIsGameOver();
		// if it is not possible
		if(!(game.getJokersLeft() > 0)){
			showWarning("You have no joker left");
		}
		else{
			game.handleJokerCase();
			game.fillHand();
			displayInfo();
			displayHand();
		}
	}
	protected void playSuit(){
		winIsGameOver();
		// if it is not the case
		if(!(game.getHand().getCard(0).suitEquals(game.getHand().getCard(3)))){
			showWarning("The first and last card don't have the same Suit");
		}
		else{
			game.handleSuitCase();
			game.fillHand();
			displayInfo();
			displayHand();
		}
	}
	protected void displayDiscard() {
		// remove all the components from the discard panel
		discardPanel.removeAll();
		// display "No card" if the discard pile is empty

		if (game.getDiscard().length() == 0) {
			JLabel no_card = new JLabel("No card");
			no_card.setFont(FONT_TEXT_BIG);
			no_card.setSize(new Dimension(240,200));
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
	protected void clearHand() {
		handPanel.removeAll();
	}
	protected void displayHand(){
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
		displayDeck();
		displayDiscard();
	}
	
}

