package core;

import java.util.Random;

public class ComputerPlayer extends Player {

	public ComputerPlayer(GinRummyGame game) {
		super(game);
	}

	@Override
	public boolean handleTurn() {
		//TODO: Teach the AI how to steal other player's cards from memory.
		//playMove(1,new Random().nextInt(7));
		return hasWon();
	}

	private int getWeakestCardShiftedIndex() {
		return 1;
	}

	private boolean shouldDrawCard(Card card) {
		return false;
	}

	public int getWeakestCard() {
		return 8;
	}

	public int getPileSelection() {
		return 1;
	}
}
