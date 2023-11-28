import javax.swing.*;	
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * The MyGui class implements all the GUI features of the game, as well as the logic behind the GUI features.
 * For instance buttons, menus etc.
 * 
* @author Benjamin Jun-jie Glover 3035962764
* @version 1.0
* @since 11-16-2023
* 
* @param dealerHand This is the reference variable pointing towards the Hand object for the dealer.
* @param playerHand This is the reference variable pointing towards the Hand object for the player.
* @param frame This reference variable points to the frame which holds all the widgets of the GUI and is the main frame.
* @param betInput This reference variable points to the JTextField which receives bets from the user.
* @param labelMoney This reference variable points to the JLabel used to show the user's money.
* @param labelInfo This reference variable points to the JLabel used to show the user's betting info.
* @param revealed This boolean determines whether the user has pressed the "Result" button and revealed all the cards.
* @param roundInProgress This boolean determines whether the user has pressed the "Start" button.
* @param money This Integer holds all the money available to the user
* @param bet This Integer holds all the money placed in a bet by the user
*/

public class MyGui {	

	//instance variables
	Hand dealerHand;
	Hand playerHand;
	JFrame frame;
	JTextField betInput;
	JLabel labelMoney;
	JLabel labelInfo;
	//game properties
	boolean revealed = false;
	boolean roundInProgress = false;
	//player properties
	int money = 100;
	int bet = 0;
	
	
	/**
	 * This method is used to reset the GridBagConstraints to their default values
	 * @param c This is the reference to the GridBagConstraints object that will be reset to default values
	 */
	private void ResetGridConstraints(GridBagConstraints c)
	{
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1; // default value
		c.gridheight = 1; // default value
		c.weightx = 0.0; // default value
		c.weighty = 0.0; // default value
		c.anchor = GridBagConstraints.CENTER; // default value
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 0, 0); // default value
		c.ipadx = 0; // default value
		c.ipady = 0; // default value
	}
	
	/**
	 * This is the main method creates a new MyGui object to run the game.
	 * @param args Unused
	 */
	public static void main(String args[])
	{
		MyGui gui = new MyGui();
	}
	
	/**
	 * This is the constructor method for the MyGui class.
	 * it initializes all the Swing components, Custom classes such as Hands and Decks, and adds the EventListeners to the buttons
	 * also organizes and renders the frame and with all its widgets
	 */
	public MyGui()
	{
		//deck
		Deck myDeck = new Deck();
		myDeck.Shuffle();
		
		//frame
		int frameWidth = 400, frameHeight = 700;
		frame = new JFrame();
		frame.setTitle("A Simple Card Game");
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu control = new JMenu("Control");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new Close());
		control.add(exit);
		menuBar.add(control);
		frame.setJMenuBar(menuBar);
		
		//main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5,1));
		mainPanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints c = new GridBagConstraints(); 
		ResetGridConstraints(c);
		frame.getContentPane().add(mainPanel);
		
		//sub-panels
		JPanel dealerPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		JPanel rpCardBtnPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		dealerPanel.setBackground(Color.green);
		playerPanel.setBackground(Color.green);
		rpCardBtnPanel.setBackground(Color.green);
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(dealerPanel);
		c.gridy = 1;
		mainPanel.add(playerPanel);
		c.gridy = 2;
		mainPanel.add(rpCardBtnPanel);
		c.gridy = 3;
		mainPanel.add(buttonPanel);
		c.gridy = 4;
		mainPanel.add(infoPanel);
		ResetGridConstraints(c);
		
		//cards
		dealerHand = new Hand(myDeck);
		playerHand = new Hand(myDeck);
		for (Card card : dealerHand.GetCardsArray())
		{
			dealerPanel.add(card.GetCardLabel());
		}
		for (Card card : playerHand.GetCardsArray())
		{
			playerPanel.add(card.GetCardLabel());
		}
		
		//replace buttons
		JButton replaceButton1 = new JButton("Replace Card 1");
		replaceButton1.addActionListener(new ReplaceCard(0)); 
		JButton replaceButton2 = new JButton("Replace Card 2");
		replaceButton2.addActionListener(new ReplaceCard(1)); 
		JButton replaceButton3 = new JButton("Replace Card 3");
		replaceButton3.addActionListener(new ReplaceCard(2)); 
		rpCardBtnPanel.add(replaceButton1);
		rpCardBtnPanel.add(replaceButton2);
		rpCardBtnPanel.add(replaceButton3);
		
		//control buttons
		JLabel labelBet = new JLabel("Bet: $");
		betInput = new JTextField(6); 
		JButton buttonStart = new JButton("Start");
		buttonStart.addActionListener(new Start());
		JButton buttonResult = new JButton("Result");
		buttonResult.addActionListener(new RevealCards());
		buttonPanel.add(labelBet);
		buttonPanel.add(betInput);
		buttonPanel.add(buttonStart);
		buttonPanel.add(buttonResult);
		
		//betting info
		labelInfo = new JLabel("Please place your bet!");
		labelMoney = new JLabel("The amount of money you have: $100");
		infoPanel.add(labelInfo);
		infoPanel.add(labelMoney);
		
		//final render
		frame.setVisible(true);
	}	
	
	/**
	 * The RevealCards inner class is used to implement the ActionListener for the "Results" Button
	 * 
	 * @author Benjamin Jun-jie Glover 3035962764
	 * @version 1.0
	 * @since 11-16-2023
	 */
	class RevealCards implements ActionListener
	{
		/**
		 * This is the overwritten function from the ActionListener interface
		 * This function is called when the button is pressed.
		 * When the round is in progress and this button is pressed, 
	     * all the cards will be revealed and the winner will be determined.
	     * @param event An event type that determines if the "Result" button has been pressed
		 */
		public void actionPerformed(ActionEvent event) {
			if (revealed == false && roundInProgress)
			{
				roundInProgress = false;
				revealed = true;
				for (Card card : dealerHand.GetCardsArray())
				{
					card.SetDisplay(true);
					card.RenderImage();
				}
				if (playerHand.IsWinner(dealerHand))
				{
					money += bet;
					JOptionPane.showMessageDialog(frame,"Congratulations! You win this round!");  
				}
				else
				{
					money -= bet;
					JOptionPane.showMessageDialog(frame,"Sorry! The dealer wins this round!");  
					if (money == 0)
					{
						JOptionPane.showMessageDialog(frame,"Game over!\nYou have no more money!\nPlease start a new game!");  	
						roundInProgress = true;
					}
				}
				labelInfo.setText("Please place your bet!");
				labelMoney.setText("The amount of money you have: $" + money);
			}
		}
	}
	
	
	/**
	 * The ReplaceCard inner class is used to implement the ActionListener for the "Replace" Buttons
	 * 
	 * @author Benjamin Jun-jie Glover 3035962764
	 * @version 1.0
	 * @since 11-16-2023
	 * 
	 * @param buttonOrder stores the position of the corresponding card attached to the button.
	 */
	class ReplaceCard implements ActionListener
	{
		int buttonOrder = -1;
		/**
		 * Constructor method for ReplaceCard class.
		 * Assigns the buttonOrder instance variable.
		 * @param order Determines the corresponding card attached to the button.
		 */
		public ReplaceCard(int order)
		{
			this.buttonOrder = order;
		}
		/**
		 * This is the overwritten function from the ActionListener interface.
		 * This function is called when the button is pressed.
		 * When the round is in progress and this button is pressed, 
		 * the corresponding card will be replaced with a new one.
		 * @param event An event type that determines if a "Replace" button has been pressed
		 */
		public void actionPerformed(ActionEvent event) {
			if (playerHand.IsReplacable(buttonOrder) && revealed == false && roundInProgress)
			{
				playerHand.ReplaceCard(buttonOrder);
			}
		}
	}
	
	/**
	 * The Close inner class is used to implement the ActionListener for the "Exit" MenuItem
	 * 
	 * @author Benjamin Jun-jie Glover 3035962764
	 * @version 1.0
	 * @since 11-16-2023
	 * 
	 */
	class Close implements ActionListener
	{
		/**
		 * This is the overwritten function from the ActionListener interface.
		 * This function is called when the button is pressed.
		 * Will close the frame when pressed.
		 * @param event An event type that determines if the "Exit" MenuItem has been pressed
		 */
		public void actionPerformed(ActionEvent event) 
		{
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
	
	/**
	 * The Start inner class is used to implement the ActionListener for the "Start" Button
	 * 
	 * @author Benjamin Jun-jie Glover 3035962764
	 * @version 1.0
	 * @since 11-16-2023
	 * 
	 */
	class Start implements ActionListener
	{
		/**
		 * This is the overwritten function from the ActionListener interface.
		 * This function is called when the button is pressed.
		 * Will read the betInput JTextField and attempt to parse the string into an Integer.
		 * Then will update the labels responsible for displaying information on bets and money.
		 * Then will give the dealer and player a new hand each
		 * @param event An event type that determines if the "Start" button has been pressed
		 */
		public void actionPerformed(ActionEvent event) {
			if (roundInProgress == false)
			{
				try
				{
					bet = Integer.parseInt(betInput.getText());
					if (bet <= money && bet >0)
					{
						roundInProgress = true;
						revealed = false;
						betInput.setText("");
						labelInfo.setText("Your current bet is: $" + bet);
						labelMoney.setText("The amount of money you have: $" + money);
						dealerHand.NewHand();
						playerHand.NewHand();
						for (Card card : playerHand.GetCardsArray())
						{
							card.SetDisplay(true);
							card.RenderImage();
						}
					}
				}
				catch (Exception e)
				{
					
				}
			}
		}
	}	
}
