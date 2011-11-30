package core;
import java.util.*;


public abstract class Player {

	public int playerID;
	public LinkedList<Card> hand = new LinkedList<Card>();
	protected boolean winningCards[] = {false,false,false,false,false,false,false};
	protected GinRummyGame theGame;
	
	public Player(GinRummyGame game) {
		this.theGame = game;
		playerID = game.players.size();
	}
	
	/**
	 * Runs on a players turn. Should return true to flag a win.
	 * @return
	 */
	public abstract boolean handleTurn();

	public void addCardToHand(Card card) {
		hand.add(card);
	}

	@SuppressWarnings("unchecked")
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
	 * Checks if the player has a winning hand.
	 * @return
	 */
	public boolean hasWon() {
		bubbleSortHandByFace();
		
		for(int i=0;i<hand.size();i++){
			for(int j=i;j<hand.size();j++)
			if(hand.get(i)==hand.get(j))
				;
		}
		//find at least 3 cards with the same face
		//copy rest to temp
		//sort temp by suit
		//check if suits are the same
		//sort temp by face
		//check increments
		int valFreq[] = new int[52], suits[] = new int[52];
		for(Card card : hand) {
			int val = card.getValue();
			valFreq[val]++;
			suits[val] = card.getSuit()+1;
		}
		for(int i=0;i<valFreq.length;i++)
			if(valFreq[i]>2)
				for(int j=0;j<hand.size();j++)
					if(hand.get(j).getValue()==i)
						winningCards[j]=true;
		for(int i=0;i<suits.length-1;i++)
			if(suits[i]!=0&&suits[i]==suits[i+1])
				for(int j=0;j<hand.size();j++)
					if(hand.get(j).getValue()==i||hand.get(j).getValue()==i+1)
						winningCards[j]=true;
//		int maxFreq = 0;
//		for (int freq : valFreq)
//			if(freq>maxFreq)
//				maxFreq=freq;
		
		for(boolean winning : winningCards)
			if(!winning)
				return false;
		return true;
	}

	private void bubbleSortHandByFace() {
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
}