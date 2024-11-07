package edu.westga.cs3211.text_adventure_game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.westga.cs3211.text_adventure_game.viewmodel.ViewModel;

/**
 * Text Adventure Game - Game Manager
 * 
 * Handles all of the interactions between classes.
 * 
 * @author Shawn Bretthauer
 * @version Fall 2024
 */
public class GameManager {
	private final String gameLocationsFileLocation = "src/edu/westga/cs3211/text_adventure_game/assets/testLocations.txt";
	private final String gameHazardsFileLocation = "src/edu/westga/cs3211/text_adventure_game//assets/testHazards.txt";
	
	private final String startingLocation = "EntryRoom";
	
	private ViewModel viewModel;
	private FileReader gameFiles;
	private Player player;
	private Location playerLocation;
	private Hazard locationHazard;
	private List<String> movementOptions;
	
	private HashMap<String, Location> gameLocations;
	private HashMap<String, Hazard> gameHazards;

	/**
	 * Empty GameManager constructor
	 */
	public GameManager() {
		this.viewModel = new ViewModel();
		this.player = new Player();
		this.gameFiles = new FileReader(this.gameLocationsFileLocation, this.gameHazardsFileLocation);
		this.movementOptions = new ArrayList<String>();
		
		this.initializeGameManger();
	}
	
	private void initializeGameManger() {
		this.setGameLocationsHazards();
		this.getLocationDescription();
		
	}
	
	private void setGameLocationsHazards()  {
		this.gameLocations = this.gameFiles.getLocationMap();
		this.gameHazards = this.gameFiles.getHazards();
		
		this.playerLocation = this.gameLocations.get(this.startingLocation);
	}
	
	private String getLocationDescription() {
		if (this.playerLocation.getHazardCheck()) {
			this.player.reducePlayerHitPoint(this.gameHazards.get(this.playerLocation.getHazardName()).getHazardDamageValue());
			return this.gameHazards.get(this.playerLocation.getHazardName()).getHazardDescription();
		}
		return this.gameLocations.get(this.playerLocation).getRoomDescription();
	}
	
	// May not need
	private boolean getHazard() {
		return this.gameLocations.get(this.playerLocation).getHazardCheck();
	}
	
	/**
	 * Gets the description of the hazard the player encounters.
	 * 
	 * @return hazard description
	 */
	// May not need
	private String getHazardName() {
		if (this.getHazard()) {
			this.locationHazard = this.gameHazards.get(this.gameLocations.get(this.playerLocation).getHazardName());
		}
		return this.gameLocations.get(this.playerLocation).getHazardName();
	}
	
	// May not need
	private String getHazardDescription() {
		return this.gameHazards.get(this.locationHazard).getHazardDescription();
	}
	
	/**
	 * Moves the player from one location to the next based on their choice.
	 * 
	 * @param action the chosen player actions for movement
	 */
	public void movePlayer(Actions action) {
		String[] connectedRoom = this.playerLocation.getConnectedRooms();
		if (action.equals(Actions.NORTH)) {
			this.playerLocation = this.gameLocations.get(connectedRoom[action.getIndexValue()]);
		} else if (action.equals(Actions.EAST)) {
			this.playerLocation = this.gameLocations.get(connectedRoom[action.getIndexValue()]);
		} else if (action.equals(Actions.SOUTH)) {
			this.playerLocation = this.gameLocations.get(connectedRoom[action.getIndexValue()]);
		} else {
			this.playerLocation = this.gameLocations.get(connectedRoom[action.getIndexValue()]);
		}
	}
	
	private void getActionList() {
		String[] options = this.playerLocation.getConnectedRooms();
		
		for (int index = 0; index < options.length; index++) {
			if (!options[index].isBlank()) {
				this.movementOptions.add(this.toString());
			}
		}
	}
	
	/**
	 * Returns the list of movement options to the ViewModel
	 * 
	 * @return movementOptions
	 */
	public List<String> getMovementOptions() {
		this.getActionList();
		
		return this.movementOptions;
	}
}
