import java.util.ArrayList;
import java.util.Collections;
/**
* The Deck class contains all the cards in a deck and acts as the source of cards for both the player and the dealer.
* 
* @author Benjamin Jun-jie Glover 3035962764
* @version 1.0
* @since 11-16-2023
* 
* @param deckCards This is the reference variable pointing towards an ArrayList containing all 52 cards.
*/
public class Deck {
	private ArrayList<String> deckCards;
	
	/**
	 * This is the constructor method for the Deck class.
	 * It allocates memory for the ArrayList.
	 * Then adds 52 cards into the deck.
	 */
	public Deck()
	{
		deckCards = new ArrayList<String>();
		CreateDeck();
	}
	
	/**
	 * The CreateDeck method appends a new deck into the ArrayList.
	 */
	private void CreateDeck()
	{
		String suites[] = {"1","2","3","4"};
		for (String suite : suites)
		{
			for (int i = 1; i <= 13; i++)
			{
				deckCards.add(suite + i);
			}
		}
	}
	
	/**
	 * The Shuffle method randomly arranges the cards in the ArrayList.
	 */
	public void Shuffle()
	{
		Collections.shuffle(deckCards);
	}
	
	/**
	 * The TakeCard method checks if the deckCards ArrayList has cards, then removes one card and returns it into the Hand of whoever called it.
	 * @return card This is the card that is removed from the deck.
	 */
	public String TakeCard()
	{
		String card = "-1";
		if (deckCards.size()>0)
		{
			card = deckCards.get(0);
			deckCards.remove(0);
		}
		else
		{
			CreateDeck();
			Shuffle();
			card = deckCards.get(0);
			deckCards.remove(0);
		}
		return card;
	}
	
	
}
