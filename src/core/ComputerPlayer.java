package core;

import java.util.LinkedList;
import java.util.Random;

public class ComputerPlayer extends Player {

	public ComputerPlayer(GinRummyGame game) {
		super(game);
	}

	//TODO: Expand AI to include pattern recognition, probability calculation and stealing cards.
	@SuppressWarnings("unchecked")
	public int getWeakestCardIndex() {
		handCopy = (LinkedList<Card>) hand.clone();
		bubbleSortHandByFace(handCopy);
		//Pull out groups of matching faces.
		//Faces take priority over runs though in reality it's more likely to win with runs.
		for(int i=0;i<handCopy.size()-2;i++){
			if(handCopy.get(i).getValue()==handCopy.get(i+1).getValue()&&handCopy.get(i).getValue()==handCopy.get(i+2).getValue()) {
				if(i<handCopy.size()-3&&handCopy.get(i).getValue()==handCopy.get(i+3).getValue())
					handCopy.remove(i+3);
				handCopy.remove(i);
				handCopy.remove(i);
				handCopy.remove(i);
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
					&&handCopy.get(i).getValue()==handCopy.get(i+3).getSuit()-3)
					fourthCardFlag=true;
				handCopy.remove(i);
				handCopy.remove(i);
				handCopy.remove(i);
				if(fourthCardFlag)
					handCopy.remove(i);
				i=-1;
			}
		}
		if(handCopy.size()>0)
			return hand.indexOf(handCopy.get(0));
		return 7;
	}

	public int getPileSelection() {
		return shouldDrawCard(theGame.discardPile.peek())?0:1;
	}
	
	private boolean shouldDrawCard(Card card) {
		return new Random().nextBoolean();
	}
}
