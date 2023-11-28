import javax.swing.*;
/**
* The Card holds all the information related to a card and is also responsible for rendering the card in the frame.
* 
* @author Benjamin Jun-jie Glover 3035962764
* @version 1.0
* @since 11-16-2023
* 
* @param backFaceImagePath This is the default system path for the image of the back side of the card.
* @param imagePath This is the system path that will store the photo of the current card when the card is revealed.
* @param value This is the numerical value of the card.
* @param suite This is the suite value of the card.
* 
* @param imageIcon This is the reference variable that holds the ImageIcon responsible for rendering card images.
* @param cardLabel This is the reference variable that holds the JLabel responsible for adding cards to the frame.
* @param isFacing This is the boolean that determines if a card will be rendered with its back-side or its front-side.
* @param isSpecial This is the boolean that determines if a card is a Queen, King, or Jack.
* @param deck This is the reference variable that points to the deck which all cards draw from.
*/

public class Card{
	
	String backFaceImagePath = "src/Images/card_back.gif";
	String imagePath = "";
	String value = "";
	String suite = "";
	
	ImageIcon imageIcon = null;
	JLabel cardLabel = null;
	
	boolean isFacing = false;
	boolean isSpecial = false;
	
	Deck deck;
	
	
	/**
	 * This is the constructor method for the Card class.
	 * It assigns the global deck reference to the deck instance variable.
	 * Also sets the card to its default, empty state.
	 * @param deck This is the global deck reference.
	 */
	public Card(Deck deck)
	{
		this.deck = deck;
		cardLabel = new JLabel();
		ResetCard();
		RenderImage();
	}
	
	/**
	 * The NewCard method will take a card from the deck and assign it's information to the instance variables of this class.
	 */
	public void NewCard()
	{
		ResetCard();
		String card = deck.TakeCard();
		this.suite = card.substring(0,1);
		this.value = card.substring(1);
		if (Integer.parseInt(value) > 10)
		{
			isSpecial = true;
		}
		imagePath = "src/Images/card_" + card + ".gif";
		RenderImage();
	}
	
	/**
	 * The GetCardLabel method is a getter function that returns the JLabel instance variable of this class.
	 * @returns JLabel the JLabel that renders this card.
	 */
	public JLabel GetCardLabel()
	{
		return this.cardLabel;
	}
	
	
	/**
	 * The ResetCard method resets the card's instance variables to their default values.
	 */
	public void ResetCard()
	{
		imagePath = "";
		value = "";
		suite = "";
		isFacing = false;
		isSpecial = false;
		imageIcon = null;
	}
	
	
	/**
	 * The SetDisplay method determines if the card will render it's back-side or front-side.
	 * @param isFacing This is the boolean that chooses if back-side or front-side is rendered.
	 */
	public void SetDisplay(boolean isFacing)
	{
		this.isFacing = isFacing;
	}
	
	/**
	 * The RenderImage will choose which image to render based on the value of the isFacing instance variable.
	 * Will reset the imageIcon and set it as the label's Icon.
	 */
	public void RenderImage()
	{
		if (isFacing)
		{
			imageIcon = new ImageIcon(imagePath);
		}
		else
		{
			imageIcon = new ImageIcon(backFaceImagePath);
		}
		cardLabel.setIcon(imageIcon);
	}
	
	

	
}
