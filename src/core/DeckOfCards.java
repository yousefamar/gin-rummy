package core;
import java.util.*;

public class DeckOfCards {
	
	private Random rand = new Random();
	private GinRummyGame theGame;
	public LinkedList<Card> deck = new LinkedList<Card>();
	
	/**
	 * The default suit order is Spades, Hearts, Clubs, Diamonds. 
	 * @param game
	 */
	public DeckOfCards(GinRummyGame game){
		this.theGame = game;
		for (int i=0;i<52;i++)
			deck.add(new Card(i));
	}
	
	public void cut() {
		int cutLoc = rand.nextInt(deck.size());
		LinkedList<Card> tempDeck = new LinkedList<Card>();
		for(int i=cutLoc;i<deck.size();i++)
			tempDeck.add(deck.get(i));
		for(int i=0;i<cutLoc;i++) {
			tempDeck.add(deck.get(i));
		}
		deck=tempDeck;
		 /* The old deck is overwritten.
		  * tempDeck is local and will be cleared on return. */
	}
	
	/**
	 * Shuffles the deck randomly. The randomness is proportional to the number of iterations.
	 * Two cards are swapped on every iteration with 1000*(number of decks) iterations in total.
	 */
	public void shuffle() {
		for (int i=0;i<1000;i++) {
			int card1 = rand.nextInt(deck.size()), card2 = rand.nextInt(deck.size()); 
			Card temp = deck.get(card1);//.clone();
			deck.set(card1, deck.get(card2));
			deck.set(card2, temp);
		}
	}
	
	public void deal() {
		int playerNum=0;
		for (int i=0;i<7*theGame.getNumOfPlayers();i++) {
			theGame.players.get(playerNum).addCardToHand(drawTopCard());
			playerNum++;
			playerNum=playerNum>=theGame.players.size()?0:playerNum;
		}
	}
	
	/**
	 * Removes top card in the deck and returns the Card object.
	 * @return
	 */
	public Card drawTopCard() {
		return deck.removeFirst();
	}
	
	public void printDeck() {
		int i=0;
		for (Card card:deck) {
			if(i%8==0)
				System.out.println();
			System.out.printf("%s\t",card.toString());
			i++;
		}
		System.out.println();
	}
}
