package core;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public abstract class Player {

	public int playerID;
	public ArrayList<Card> hand = new ArrayList<Card>();
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
	
	public void drawTopCardFromDeck() {
		hand.add(theGame.cardDeck.drawTopCard());
		if(theGame.cardDeck.getSize()<1)
			theGame.onDeckEmpty();
	}
	
	public void drawTopCardFromDiscardPile() {
		hand.add(theGame.discardPile.pop());
		if(theGame.cardDeck.getSize()<1)
			theGame.onDeckEmpty();
	}
	
	public void addCardToDiscardPile(int index) {
		theGame.discardPile.push(hand.remove(index));
	}
	
	/**
	 * Checks if the player has a winning hand.
	 * @return
	 */
	public boolean hasWon() {
		return false;
	}
	
}
