package core;

public class Card {
	
	private int index;
	// If I had the freedom to, I'd add more fields to this class that affect
	// the way a particular card is rendered in the game instead of doing it in the GUI classes.
	
	/**
	 * Indices range from 0 to 51.
	 * @param index
	 */
	public Card(int index) {
		while(index>51)
			index-=52;
		this.index = index;
	}
	
	public int getValue() {
		int numVal = index;
		while(numVal>12)
			numVal-=13;
		return numVal;
	}
	
	public int getSuit() {
		int numVal = index, suitVal = 0;
		while(numVal>12) {
			numVal-=13;
			suitVal++;
		}
		return suitVal;
	}
	
	public boolean isRed() {
		return ((index>12&&index<26)||(index>38&&index<52));
	}
	
	public String toString() {
		String strVal, suit;
		int numVal = index, suitVal = 0;
		
		while(numVal>12) {
			numVal-=13;
			suitVal++;
		}
		
		switch (numVal) {
		case 0:
			strVal = "A";
			break;
		case 10:
			strVal = "J";
			break;
		case 11:
			strVal = "Q";
			break;
		case 12:
			strVal = "K";
			break;
		default:
			strVal = Integer.toString(numVal+1);
			break;
		}
		
		switch (suitVal) {
		case 0:
			suit = "\u2660";
			break;
		case 1:
			suit = "\u2665";
			break;
		case 2:
			suit = "\u2663";
			break;
		case 3:
			suit = "\u2666";
			break;
		default:
			suit = "Jk";
			break;
		}
		
		return strVal+suit;
	}

}
