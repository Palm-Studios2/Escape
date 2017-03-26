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
}