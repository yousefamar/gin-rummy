package core;
import java.util.*;


public abstract class Player {

	public int playerID;
	protected GinRummyGame theGame;
	public LinkedList<Card> hand = new LinkedList<Card>();
	protected LinkedList<Card> handCopy = new LinkedList<Card>();
	
	public Player(GinRummyGame game) {
		this.theGame = game;
		playerID = game.players.size();
	}
	
	//public abstract boolean handleTurn();

	public void addCardToHand(Card card) {
		hand.add(card);
	}

	public void playMove(int clickedCard, int clickedPile) {
		if(clickedCard>6)
			theGame.discardPile.push(theGame.cardDeck.drawTopCard());
		else
			theGame.discardPile.push(clickedPile==0?theGame.currentPlayer.hand.set(clickedCard, theGame.discardPile.pop()):(theGame.currentPlayer.hand.set(clickedCard, theGame.cardDeck.drawTopCard())));
		if(theGame.cardDeck.deck.isEmpty()) {
			for (Card card : theGame.discardPile)
				theGame.cardDeck.deck.add(card);
			theGame.discardPile.clear();
			theGame.cardDeck.shuffle();
			theGame.discardPile.push(theGame.cardDeck.drawTopCard());
		}
	}

	public void addCardToDiscardPile(int index) {
		theGame.discardPile.push(hand.remove(index));
	}
	
	/**
	 * @return Returns true if a player has a winning hand.
	 */
	@SuppressWarnings("unchecked")
	public boolean hasWon() {
		handCopy = (LinkedList<Card>) hand.clone();
		bubbleSortHandByFace(handCopy);
		LinkedList<Card> winning = new LinkedList<Card>();
		//Pull out groups of matching faces.
		//Faces take priority over runs though in reality it's more likely to win with runs.
		for(int i=0;i<handCopy.size()-2;i++){
			if(handCopy.get(i).getValue()==handCopy.get(i+1).getValue()&&handCopy.get(i).getValue()==handCopy.get(i+2).getValue()) {
				if(i<handCopy.size()-3&&handCopy.get(i).getValue()==handCopy.get(i+3).getValue())
					winning.add(handCopy.remove(i+3));
				winning.add(handCopy.remove(i));
				winning.add(handCopy.remove(i));
				winning.add(handCopy.remove(i));
				i=-1;
			}
		}
		bubbleSortHandBySuit(handCopy); //NB: Hand is already sorted by value so values in suits will be sequential.
		//Pull out runs.
		for(int i=0;i<handCopy.size()-2;i++){
			if(handCopy.get(i).getSuit()==handCopy.get(i+1).getSuit()&&handCopy.get(i).getSuit()==handCopy.get(i+2).getSuit()
				&&handCopy.get(i).getValue()==handCopy.get(i+1).getValue()-1&&handCopy.get(i).getValue()==handCopy.get(i+2).getValue()-2) {
				boolean fourthCardFlag = false;
				if(i<handCopy.size()-3&&handCopy.get(i).getSuit()==handCopy.get(i+3).getSuit()
					&&handCopy.get(i).getValue()==handCopy.get(i+3).getValue()-3)
					fourthCardFlag=true;
				winning.add(handCopy.remove(i));
				winning.add(handCopy.remove(i));
				winning.add(handCopy.remove(i));
				if(fourthCardFlag)
					winning.add(handCopy.remove(i));
				i=-1;
			}
		}
		if(winning.size()>6)
			return true;
		return false;
	}

	protected void bubbleSortHandByFace(LinkedList<Card> hand) {
		int swapCount;
		do{
			swapCount = 0;
			for (int i=0;i<hand.size()-1;i++)
				if(hand.get(i).getValue()>hand.get(i+1).getValue()) {
					hand.add(i, hand.remove(i+1));
					swapCount++;
				}
		}while(swapCount>0);
	}
	
	protected void bubbleSortHandBySuit(LinkedList<Card> hand) {
		int swapCount;
		do{
			swapCount = 0;
			for (int i=0;i<hand.size()-1;i++)
				if(hand.get(i).getSuit()>hand.get(i+1).getSuit()) {
					hand.add(i, hand.remove(i+1));
					swapCount++;
				}
		}while(swapCount>0);
	}
}