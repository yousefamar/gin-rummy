package core;

public class HumanPlayer extends Player {

	// The only reason this class is empty is because all input is handled in the GUI now
	// as it's no longer console based. The class remains for structural purposes and any further AI
	// (such as move suggestions) can be added to it.
	
	public HumanPlayer(GinRummyGame game) {
		super(game);
	}
}
