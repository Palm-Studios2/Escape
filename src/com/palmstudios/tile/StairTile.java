/**
 * 	@file
 * 
 * 	An stair tile. All entities can pass through this (they are unblocked) 
 *	When passed through, it will take the player to the next level if they have a key
 * 
 *	@date 7-4-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.palmstudios.system.Art;
import com.palmstudios.system.Tile;

public class StairTile extends Tile
{
	private BufferedImage sprite;
	
	public StairTile()
	{
		super(Tile.TILE_STAIR, false, "Stair");
	}
	
	@Override
	public void draw(Graphics2D g2d, int x, int y)
	{
		g2d.drawImage(Art.tiles[2][0], x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
	}

}
