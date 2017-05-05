/**
 * 	@file
 * 
 * 	Class that manages all of our game states. 
 *
 *	@date  26-3-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.state;

import java.util.ArrayList;

import com.palmstudios.system.GameState;

/**
 * @author Jesse
 *
 */
public class GameStateManager
{
	
	public static final int SPLASH_STATE 	= 0;
	public static final int MENU_STATE 		= 1;
	public static final int TEST_STATE 		= 2;
	public static final int LEVEL_TWO		= 3;
	public static final int DEFEAT_STATE	= 4;
	public static final int VICTORY_STATE	= 4;
	
	private ArrayList<GameState> 	statesList;
	private GameState				currentState;
	/**
	 * Class constructor.
	 */
	public GameStateManager()
	{
		statesList = new ArrayList<GameState>();
		currentState = null;
	}
	
	/**
	 * Change the game state that is currently 
	 * @param stateNum - The state number we want to change to.
	 */
	public void changeState(int stateNum)
	{
		currentState = statesList.get(stateNum);
	}
	
	/**
	 * Load a game state into the states list.
	 * @param state - GameState object we want to add (CANNOT be an actual GameState)
	 */
	public void loadState(GameState state)
	{
		if(state != null)
			statesList.add(state);
	}
	
	/**
	 * Unload a state in the statesList if it exists.
	 * @param stateNum - The state number we want to release.
	 */
	public void unloadState(int stateNum)
	{
		if(statesList.get(stateNum) != null)
			statesList.remove(stateNum);
	}
	
	/**
	 * Get the current state.
	 */
	public GameState getCurrentState()
	{
		if(currentState != null)
			return currentState;
		else
			System.err.println("Error: currentState == null! Perhaps is hasn't been initialised??");
		
		return null;
	}
	
	/**
	 * Get the number of states in the state list
	 */
	public int getNumberStates()
	{
		return statesList.size();
	}
}
