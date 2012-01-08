package core;

import java.util.*;

public class ComputerPlayer extends Player {

	private Random rand = new Random();
	
	public ComputerPlayer(GinRummyGame game) {
		super(game);
	}

	@SuppressWarnings("unchecked")
	public int getWeakestCardIndex(boolean isDeckSelected) {
		handCopy = (LinkedList<Card>) hand.clone();

		/* Pull out groups of matching faces */
		//Faces take priority over runs though in reality it's more likely to win with runs.
		bubbleSortHandByFace(handCopy);
		for(int i=0;i<handCopy.size()-2;i++)
			if(handCopy.get(i).getValue()==handCopy.get(i+1).getValue()&&handCopy.get(i).getValue()==handCopy.get(i+2).getValue()) {
				if(i<handCopy.size()-3&&handCopy.get(i).getValue()==handCopy.get(i+3).getValue())
					handCopy.remove(i+3);
				handCopy.remove(i);
				handCopy.remove(i);
				handCopy.remove(i);
				i=-1;
			}

		/* Pull out runs */
		bubbleSortHandBySuit(handCopy); //NB: Hand is already sorted by value so values in suits will be sequential.
		for(int i=0;i<handCopy.size()-2;i++)
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

		int weakestIndexSoFar=isDeckSelected?7:rand.nextInt(7);
		if(handCopy.size()>0)
			weakestIndexSoFar = hand.indexOf(handCopy.get(rand.nextInt(handCopy.size())));
		
		/* Pull out non-unique cards */
		bubbleSortHandByFace(handCopy);
		for(int i=0;i<handCopy.size()-1;i++)
			if(handCopy.get(i).getValue()==handCopy.get(i+1).getValue()) {
				handCopy.remove(i);
				handCopy.remove(i);
				i=-1;
			}
		
		/* Pull out potential runs. */
		bubbleSortHandBySuit(handCopy); //NB: Hand is already sorted by value so values in suits will be sequential.
		for(int i=0;i<handCopy.size()-1;i++)
			if(handCopy.get(i).getSuit()==handCopy.get(i+1).getSuit()&&handCopy.get(i).getValue()==handCopy.get(i+1).getValue()-1) {
				handCopy.remove(i);
				handCopy.remove(i);
				i=-1;
			}
		
		if(handCopy.size()>0)
			return hand.indexOf(handCopy.get(rand.nextInt(handCopy.size())));
		return weakestIndexSoFar;
	}

	public int getPileSelection() {
		return shouldDrawCard(theGame.discardPile.peek())?0:1;
	}
	
	private boolean shouldDrawCard(Card card) {
		//TODO: Optimise and give the computer memory of past cards and cards other players want to avoid or steal them respectively.
		int cardValue = theGame.discardPile.peek().getValue(), cardSuit = theGame.discardPile.peek().getSuit();
		for (Card handCard : hand) {
			//Once again matches have priority over runs for consistency although theoretically it's more likely to win with a run.
			if(handCard.getValue()==cardValue)
				return true;
			if(handCard.getSuit()==cardSuit&&(handCard.getValue()==cardValue+1||handCard.getValue()==cardValue-1))
				return true;
		}
		return false;
		//return rand.nextBoolean();
	}
}
