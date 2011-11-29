package core;

public class ComputerPlayer extends Player {

	public ComputerPlayer(GinRummyGame game) {
		super(game);
	}

	@Override
	public boolean handleTurn() {
//		System.out.println("Your hand is:");
//		for (Card card : this.hand)
//			System.out.printf("%s ", card.toString());
//		System.out.printf("\n\n");
//		theGame.printTopDiscardCard();
//		
//		int weakestCardShiftedIndex = 1;
//		boolean shouldDrawDiscard = shouldDrawCard(theGame.discardPile.peek());
//		try {
//			System.out.println();
//			System.out.println("Enter 1 to draw from the discard pile or 0 to draw from the deck: ");
//			if(theGame.noHumans)
//				Thread.sleep(1000);
//			System.out.println(shouldDrawDiscard?1:0);
//			if(theGame.noHumans)
//				Thread.sleep(1000);
//			System.out.println();
//			System.out.println("Choose a card to discard (1-7): ");
//			if(theGame.noHumans)
//				Thread.sleep(1000);
//			System.out.println(weakestCardShiftedIndex = getWeakestCardShiftedIndex());
//			if(theGame.noHumans)
//				Thread.sleep(1000);
//		} catch (InterruptedException e) {}
//
//		if(shouldDrawDiscard)
//			hand.add(theGame.discardPile.pop());
//		else
//			hand.add(theGame.cardDeck.drawTopCard());
//		theGame.discardPile.push(hand.remove(weakestCardShiftedIndex));
		
		return hasWon();
	}

	private int getWeakestCardShiftedIndex() {
		return 1;
	}

	private boolean shouldDrawCard(Card card) {
		return false;
	}
}
