package core;

import java.io.Console;

public class HumanPlayer extends Player {

	public HumanPlayer(GinRummyGame game) {
		super(game);
	}

	@Override
	public boolean handleTurn() {
		//Everything done in GUI now.
		//This is where the console inputs used to come from.
		return hasWon();
	}
}
