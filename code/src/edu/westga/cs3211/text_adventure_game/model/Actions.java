package edu.westga.cs3211.text_adventure_game.model;

/**
 * Text Adventure Game - Action Enum
 * 
 * Lists all possible actions available in Text Adventure Game
 * 
 * @author Shawn Bretthauer MoriaEL
 * @version Fall 2024
 */
public enum Actions {
	NORTH("Move North", 0), EAST("Move East", 1), SOUTH("Move South", 2), WEST("Move West", 3), FIGHT("Fight", 4),
	INTERACT("Interact", 5), PICK_UP("Pick Up", 6);

	private String action;
	private int indexValue;

	Actions(String action, int index) {
		this.action = action;
		this.indexValue = index;
	}

	/**
	 * Returns a single string for the action available.
	 * 
	 * @return action the String value of the direction
	 */
	public String toString() {
		return this.action;
	}

	/**
	 * Returns the index value of the movement choice.
	 * 
	 * @return indexValue the value of the direction
	 */
	public int getIndexValue() {
		return this.indexValue;
	}
	
	/**
	 * Returns a dynamic action string for a specific item.
	 * 
	 * @param baseAction the base action (e.g., "Drop")
	 * @param itemName the name of the item
	 * @return the dynamic action string
	 */
	public static String getDynamicAction(String baseAction, String itemName) {
	    return baseAction + " " + itemName;
	}

	/**
	 * Returns the proper String value to the index.
	 * 
	 * @param index of the available room.
	 * 
	 * @return action String
	 */
	public static Actions getActionByIndex(int index) {
		for (Actions action : Actions.values()) {
			if (action.getIndexValue() == index) {
				return action;
			}
		}
		return null;
	}
}
