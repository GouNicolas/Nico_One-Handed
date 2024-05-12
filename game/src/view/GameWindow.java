package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import model.Game;
import model.Hand;
import model.Rank;
import model.Suit;


public class GameWindow extends JFrame{
	protected static final long serialVersionUID = 2L;

	

	// game instance
	protected Game game = new Game();

	//constants for the cosmetics
	protected Color COLOR_CREAM = new Color(255, 230, 230);
	protected Color COLOR_PINK_PURPLE = new Color(225, 175, 209);
	protected Color COLOR_LIGHT_PURPLE = new Color(173, 136, 198);
	protected Color COLOR_PURPLE = new Color(116, 105, 182);
	protected Font FONT_TEXT = new Font("Carlito", Font.PLAIN, 20);
	private static final Font FONT_TEXT_BIG = new Font("Carlito", Font.BOLD, 24);
	
	// window elements
	protected JPanel handPanel;
	protected JPanel deckPanel;
	protected JPanel discardPanel;
	protected JPanel scorePanel;
	protected JPanel buttonsPanel;

	// game elements
	protected JButton showback;
	protected JButton buttonDraw;
	protected JButton buttonRank;
	protected JButton buttonSuits;


	protected JLabel cardDisplay1;

	/**
	 * Constructor
	 */
	public GameWindow(Game g) {
		// Initialize the game
		game = g;
		// Initialize the panels
		
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
		this.setMinimumSize(new Dimension(1600, 900));
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
		
		// configure the panel for the buttons
		buttonsPanel.setMinimumSize(new Dimension(1200,200));
		buttonsPanel.setLayout(new GridLayout());
		buttonsPanel.setBackground(COLOR_CREAM);


		// configure the panel for the score
		scorePanel.setMinimumSize(new Dimension(1200, 50));
		scorePanel.setLayout(new FlowLayout());
		scorePanel.setBackground(COLOR_PURPLE);

		// configure the panel for the deck
		deckPanel.setBackground(COLOR_CREAM);
		deckPanel.setLayout(new FlowLayout());
		deckPanel.setPreferredSize(new Dimension(220,400));

		// configure the panel for the hand
		ImageIcon backgroundImage = new ImageIcon(GameWindow.class.getResource("/resources/window_bg.png"));
		Image img = backgroundImage.getImage();

		// set the background image for the hand panel
		JPanel handPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) { // Anonymous class to override the paintComponent method
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		};
		handPanel.setBorder(BorderFactory.createLineBorder(COLOR_PINK_PURPLE, 4));
		handPanel.setPreferredSize(new Dimension(1200,500));

		// configure the panel for the discard pile
		discardPanel.setBackground(COLOR_CREAM);
		discardPanel.setPreferredSize(new Dimension(220, 400));
		discardPanel.setLayout(new FlowLayout());
		
		// Tests
		cardDisplay1 = new JLabel();
		ViewCard cardKS = new ViewCard(Rank.KING, Suit.SPADES);
		cardDisplay1.setIcon(new ImageIcon(GameWindow.class.getResource(cardKS.imagePath)));
		cardDisplay1.setSize(200, 328);

		JLabel cardDisplay2 = new JLabel("test");
		handPanel.add(cardDisplay2);
		handPanel.add(cardDisplay1);

		// end of tests

		
		// deckdisplay
		deckDisplay();

		// discard display
		discardDisplay();

		// display the hand
		//displayHand();

		// score display
		scoreDisplay();

		// add the buttons to the buttons panel
		addActionButtons();

		// Add the panel to the frame
		this.add(buttonsPanel, BorderLayout.SOUTH);
		this.add(handPanel, BorderLayout.CENTER);
		this.add(scorePanel, BorderLayout.NORTH);
		getContentPane().add(deckPanel, BorderLayout.WEST);
		getContentPane().add(discardPanel, BorderLayout.EAST);
		
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
		if (game.getDeck().length() > 0) {
			JLabel deckLabel = new JLabel("Remaining Cards: " + game.getDeck().length());
			deckLabel.setFont(FONT_TEXT);
			JLabel one_card_of_the_deck = new JLabel(new ImageIcon(GameWindow.class.getResource("/resources/cardback.png")));
			one_card_of_the_deck.setSize(200, 328);
			deckPanel.add(one_card_of_the_deck);
			deckPanel.add(deckLabel);
		}
		else {
			JLabel no_card = new JLabel("No card");
			no_card.setBounds(0, 0, 200, 328);
			deckPanel.add(no_card);
		}
	}
	protected void scoreDisplay() {
		JLabel scoreLabel = new JLabel("Score: " + game.getScore());
		scoreLabel.setFont(FONT_TEXT_BIG);
		scoreLabel.setForeground(Color.WHITE);
		scorePanel.add(scoreLabel);
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
			discardPanel.add(new JLabel(new ImageIcon(GameWindow.class.getResource(card.imagePath))));
		}
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
			System.out.println(card.imagePath);
		}
		// clear the hand panel
		handPanel.removeAll();
	
		// display the cards in the hand
		for (String cardImage : cardImages) {
			ImageIcon imageIcon = new ImageIcon(GameWindow.class.getResource(cardImage));
			Image image = imageIcon.getImage();
			Image scaledImage = image.getScaledInstance(200, 328, Image.SCALE_SMOOTH);
			ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
			JLabel cardLabel = new JLabel(scaledImageIcon);
			System.out.println(cardImage);
			handPanel.add(cardLabel);
		}
	}
}

