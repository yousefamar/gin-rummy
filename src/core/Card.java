package core;

import java.awt.Color;
import java.awt.Graphics;

public class Card {
	
	private int index;
	
	/**
	 * Indices range from 0 to 51.
	 * @param index
	 */
	public Card(int index) {
		while(index>51)
			index-=52;
		this.index = index;
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
			suit = "♠";
			break;
		case 1:
			suit = "♥";
			break;
		case 2:
			suit = "♣";
			break;
		case 3:
			suit = "♦";
			break;
		default:
			suit = "Jk";
			break;
		}
		
		return strVal+suit;
	}

}
