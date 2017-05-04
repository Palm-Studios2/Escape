/**
 * 	@file
 * 
 * 	An key tile. All entities can pass through this (they are unblocked) 
 *	When passed through, it will update the players key bool
 *	And then become a floor tile 
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

public class KeyTile extends Tile
{
	private BufferedImage sprite;
	
	public KeyTile()
	{
		super(Tile.TILE_KEY, false, "Key");
	}
	
	@Override
	public void draw(Graphics2D g2d, int x, int y)
	{
		g2d.drawImage(Art.tiles[2][3], x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
	}

}