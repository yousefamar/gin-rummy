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
	
	public GinRummyGame(int humanNum, int computerNum) {
		this.humanNum = humanNum;
		this.computerNum = computerNum;
		for (int i=0;i<(humanNum+computerNum);i++) {
			if(i<humanNum)
				players.add(new HumanPlayer(this));
			else
				players.add(new ComputerPlayer(this));
		}
		
		cardDeck.shuffle();
		cardDeck.deal();
		Player pla=players.get(0);
		pla.hand.clear();
		pla.hand.add(new Card(0));
		pla.hand.add(new Card(5));
		pla.hand.add(new Card(4));
		pla.hand.add(new Card(3));
		pla.hand.add(new Card(1));
		pla.hand.add(new Card(7));
		pla.hand.add(new Card(6));
		discardPile.push(cardDeck.drawTopCard());

		int i=0;
		while((currentPlayer=players.get(i)).handleTurn()) {
			i++;
			if(i>=players.size())
				i=0;
		}
	}
	
	public void setNextPlayerAsCurrent() {
		int nextPlayerIndex = currentPlayer.playerID+1;
		nextPlayerIndex = nextPlayerIndex>(getNumOfPlayers()-1)?0:nextPlayerIndex;
		currentPlayer = players.get(nextPlayerIndex);
	}
	
	public int getNumOfPlayers() {
		return humanNum + computerNum;
	}
}
