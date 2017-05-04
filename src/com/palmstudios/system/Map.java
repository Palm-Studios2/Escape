/**
 * 	@file
 * 
 * 	Map class. This is instantiated when we load a new map in the main Game state.
 * 
 *	@date 3-4-2017
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.system;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.palmstudios.tile.AirTile;
import com.palmstudios.tile.KeyTile;
import com.palmstudios.tile.NullTile;
import com.palmstudios.tile.StairTile;
import com.palmstudios.tile.WallTile;

public class Map
{
	/**
	 * How many tiles we can fit into the width and height of the map
	 */
	public static final int MAP_WIDTH 	= GamePanel.WIDTH / Tile.TILE_SIZE;
	public static final int MAP_HEIGHT 	= GamePanel.HEIGHT / Tile.TILE_SIZE;
	
	private String mapName;		/**< Name of the map we have loaded */
	private Tile[][] map; 		/**< Tile map */
	
	/**
	 * Default constructor
	 */
	public Map()
	{
		map = new Tile[MAP_WIDTH][MAP_HEIGHT];
		load("map.txt"); // Default to loading map.txt
	}
	
	/**
	 * Class Constructor
	 * @param mapName - Level name (i.e "Crypt of Despair")
	 */
	public Map(String mapName, String path)
	{
		this.mapName = mapName;
		map = new Tile[MAP_WIDTH][MAP_HEIGHT];
		load(path);
	}
	
	/**
	 * Load a map from disk and convert the integer values into tiles.
	 * 
	 * @param path - File path of the map to load.
	 * @return Returns true if map was successfully loaded from disk, false otherwise.
	 */
	private boolean load(String path)
	{
		try
		{
			BufferedReader 	br = new BufferedReader(new FileReader(path));
			String 			currentLine;
			String[] 		row;
			
			Tile t = null; // Local tile. Initialised properly when we walk the map
			int x = 0; // x pos counter
			int y = 0; // y pos counter
			
			while((currentLine = br.readLine()) != null) // Read each l
			{
				if(currentLine.isEmpty())
					continue;

				row = currentLine.trim().split(" "); // Each value is split with a space (' ')
				
				x = 0; // Reset x location so we don't overrun the array!
				
				for(String value : row)
				{
					int id = Integer.parseInt(value); // Get tile type
					
					switch(id) // Create a new tile depending on what type we hit and initialise it as such.
					{
					case Tile.TILE_AIR:
						t = new AirTile();
						break;
					case Tile.TILE_WALL:
						t = new WallTile(WallTile.WALL);
						break;
					case Tile.TILE_CORNER:
						t = new WallTile(WallTile.CORNER);
						break;
					case Tile.TILE_KEY:
						t = new KeyTile();
						break;
					case Tile.TILE_STAIR:
						t = new StairTile();
						break;
					default:
						t = new NullTile();
						System.err.println("INVALID TILE NUMBER!!!");
						break;
					}
					
					map[x][y] = t; // Store this tile in the map
					x++;
				}
				
				y++;
			}
			
			br.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	/**
	 * Draw this map.
	 * @param g2d - Graphics object from Framebuffer
	 */
	public void draw(Graphics2D g2d)
	{
		for(int y = 0; y < MAP_HEIGHT; y++)
		{
			for(int x = 0; x < MAP_WIDTH; x++)
			{
				map[x][y].draw(g2d, x, y);
			}
		}
	}
	
	/**
	 * 
	 * @param tileX - The x position of the tile (in tile co-ords)
	 * @param tileY - The y position of the tile (int tile co-ords)
	 * @return Map index at tileX and tileY
	 */
	public Tile getTileAt(int tileX, int tileY)
	{
		return map[tileX][tileY];
	}
	
	//Allows us to change a tile to an air tile
	//Eg for key tiles
	public void setToAir(int tileX, int tileY){
		map[tileX][tileY] = new AirTile();
	}
	
}
