package core;

import java.io.Console;

public class HumanPlayer extends Player {

	public HumanPlayer(GinRummyGame game) {
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
//		int input;
//		boolean shouldDrawDiscard = false;
//		Console console = System.console();
//		do {
//			input = -1;
//			System.out.println();
//			System.out.println("Enter 1 to draw from the discard pile or 0 to draw from the deck: ");
//			try {
//				input = Integer.parseInt(console.readLine());
//			} catch (Exception e) {
//				input = -1;
//			}
//			if(input !=0 && input != 1)
//				System.out.println("Invalid selection.");
//			else
//				shouldDrawDiscard = input==1;
//		} while(input !=0 && input != 1);
//		do {
//			input = -1;
//			System.out.println();
//			System.out.println("Choose a card to discard (1-7): ");
//			try {
//				input = Integer.parseInt(console.readLine());
//			} catch (Exception e) {
//				input = -1;
//			}
//			if(input<1 || input>7)
//				System.out.println("Invalid selection.");
//		} while(input<1 || input>7);
//		
//		if(shouldDrawDiscard)
//			hand.add(theGame.discardPile.pop());
//		else
//			hand.add(theGame.cardDeck.drawTopCard());
//		theGame.discardPile.push(hand.remove(input));
		
		return hasWon();
	}
}
