package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GinRummyGame {
	
	private int humanNum, computerNum;
	public DeckOfCards cardDeck = new DeckOfCards(this);
	public ArrayList<Player> players = new ArrayList<Player>();
	public Stack<Card> discardPile = new Stack<Card>();
	public Player currentPlayer;
	public boolean noHumans;
	
	public GinRummyGame(int humanNum, int computerNum) {
		this.humanNum = humanNum;
		this.computerNum = computerNum;
		this.noHumans = computerNum==0;
		for (int i=0;i<(humanNum+computerNum);i++) {
			if(i<humanNum)
				players.add(new HumanPlayer(this));
			else
				players.add(new ComputerPlayer(this));
		}
		
		cardDeck.shuffle();
		cardDeck.deal();
		discardPile.push(cardDeck.drawTopCard());

		int i=0;
		while((currentPlayer=players.get(i)).handleTurn()) {
			i++;
			if(i>=players.size())
				i=0;
		}
	}
	
	public void playMove(int clickedCard, int clickedStack) {
		discardPile.push(clickedStack==0?currentPlayer.hand.set(clickedCard, discardPile.pop()):(currentPlayer.hand.set(clickedCard, cardDeck.drawTopCard())));
	}
	
	public void setCurentPlayerAsNext() {
		//try {Thread.sleep(1000);} catch (InterruptedException e){}
	}
	
	@SuppressWarnings("unchecked")
	public void onDeckEmpty() {
		cardDeck.setDeck((LinkedList<Card>) discardPile.clone());
		discardPile.clear();
		cardDeck.shuffle();
	}

	public int getNumOfPlayers() {
		return humanNum + computerNum;
	}
	
	public int getNumOfComputers() {
		return computerNum;
	}

	/**
	 * Returns maximum hand size; -1 when all cards should be split evenly.
	 * @return
	 */
	public int getMaxHandSize() {
		return 7;
	}
}
