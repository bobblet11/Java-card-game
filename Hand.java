import java.util.ArrayList;
/**
* The Hand class stores the 3 cards needed for each player to play. It does methods using these 3 cards to determine
* the winner, and can replace the cards with new cards.
* 
* @author Benjamin Jun-jie Glover 3035962764
* @version 1.0
* @since 11-16-2023
* 
* @param cards This is the ArrayList that holds the 3 Card objects each player has in their hand.
* @param available This array is used to determine if one of the 3 cards at a specific index has been replaced during the round.
* @param availableCount This integer counts how many cards have been replaced in a round.
* @param specialCount This integer counts how many special cards are in a hand.
* @param nonSpecialValue This integer stores the numerical value of the non special cards in a hand after modulus 10.
*/ 

public class Hand {
	ArrayList<Card> cards;
	ArrayList<Integer> available;
	
	int availableCount = 0;
	int specialCount = 0;
	int nonSpecialValue = 0;
	
	/**
	 * This is the constructor method for the Hand class.
	 * This will allocate memory for the cards ArrayList and the available ArrayList,
	 * and will fill these with new card objects and integers respectively.
	 * 
	 * @param deck This is a reference to the global deck which the cards in this class draw from.
	 */
	public Hand(Deck deck)
	{
		cards = new ArrayList<Card>();
		available = new ArrayList<Integer>();
		for (int i = 0; i<3; i++)
		{
			available.add(1);
			Card card = new Card(deck);
			cards.add(card);
		}
	}
	
	/**
	 * GetCardsArray is a getter method that returns the ArrayList storing the cards.
	 * @return ArrayList<Card> the ArrayList that stores the cards.
	 */
	public ArrayList<Card> GetCardsArray()
	{
		return cards;
	}
	
	/**
	 * The NewHand method replaces all card values of the cards ArrayList.
	 */
	public void NewHand()
	{
		specialCount = 0;
		for (int i = 0; i<3; i++)
		{
			cards.get(i).NewCard();
			if (cards.get(i).isSpecial)
			{
				specialCount+=1;
			}
			available.set(i, 1);
		}
		availableCount = 0;
		nonSpecialValue = 0;
	}
	
	/**
	 * The IsReplacable method determines if a specific card can be replaced
	 * @param card The specific card in the hand to be replaced.
	 */
	public boolean IsReplacable(int card)
	{
		return available.get(card) == 1 && availableCount < 2;
	}
	
	/**
	 * The ReplaceCard method selects a specific card and replaces it's value with a new card from the global deck.
	 * @param card The specific card in the hand to be replaced.
	 */
	public void ReplaceCard(int card)
	{
		available.set(card, 0);
		availableCount += 1;
		if (cards.get(card).isSpecial)
		{
			specialCount-=1;
		}
		cards.get(card).NewCard();		
		if (cards.get(card).isSpecial)
		{
			specialCount+=1;
		}
		cards.get(card).SetDisplay(true);
		cards.get(card).RenderImage();
	}
	
	/**
	 * The CalculateValue method calculates the numerical value of the non-special cards and returns the remainder after 
	 * dividing by 10.
	 * @return int the numerical value of the non-special cards and returns the remainder after dividing by 10.
	 */
	public int CalculateValue()
	{
		for (int i = 0; i<3; i++)
		{
			if (Integer.parseInt(cards.get(i).value) <= 10)
			{
				nonSpecialValue += Integer.parseInt(cards.get(i).value);
			}
		}
		return nonSpecialValue;
	}
	
	
	/**
	 * The IsWinner method determines whether the object that called this function has a better hand than another hand.
	 * @param otherHand the other player's hand who will be used to compare against.
	 * @return boolean true means the Hand object that called this function beat the other hand.
	 */
	public boolean IsWinner(Hand otherHand)
	{
		if (this.specialCount > otherHand.specialCount)
		{
			return true;
		}
		else if (this.specialCount == otherHand.specialCount)
		{
			return ((this.CalculateValue()%10) > (otherHand.CalculateValue()%10));
		}
		else
		{
			return false;
		}
	}
}
