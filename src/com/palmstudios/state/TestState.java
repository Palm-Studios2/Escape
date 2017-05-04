/**
 * 	@file
 *
 *	@date 
 *
 *	@author Jesse Buhagiar
 */
package com.palmstudios.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.palmstudios.object.Enemy;
import com.palmstudios.object.Player;
import com.palmstudios.system.GamePanel;
import com.palmstudios.system.GameState;
import com.palmstudios.system.Map;
import com.palmstudios.system.Tile;
import com.palmstudios.tile.AirTile;

/**
 * @author Jesse
 *
 */
public class TestState extends GameState
{
	public static Map map = new Map(); 
	private Player player = new Player("player");
	private int timer = 0;
	private int ouchies = 0;
	private int keys = 0;
	private int collisionTick = 0;
	
	Enemy[] enemies = new Enemy[] {
			new Enemy("Steve", 32, 384),
			new Enemy("Barry", 448, 384),
			new Enemy("George", 448, 64)
	};

	public TestState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}

	@Override
	public void init()
	{
		map = new Map("test", "map.txt");
	}

	@Override
	public void update()
	{
		if(ouchies > 10)
			player.sprite = player.loadSprite("enemy.png");
		if(timer == 40){
			enemies[0].update();
			enemies[1].update();
			enemies[2].update();
			timer = 0;
		}
		timer++;
		collisionCheck();
		if(map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32)/ Tile.TILE_SIZE)).getType() == Tile.TILE_KEY){
			map.setToAir((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32)/ Tile.TILE_SIZE));
			keys = 1;
		}
		if(map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 32)/ Tile.TILE_SIZE)).getType() == Tile.TILE_STAIR && keys == 1){
			gsm.changeState(GameStateManager.MENU_STATE);
			gsm.unloadState(GameStateManager.TEST_STATE);
		}
	}

	@Override
	public void draw(Graphics2D g2d)
	{
		map.draw(g2d);
		enemies[0].draw(g2d);
		enemies[1].draw(g2d);
		enemies[2].draw(g2d);
		player.draw(g2d);
		g2d.drawString("Ouchies:" + ouchies, 0, 16);
		g2d.drawString("Key:" + keys, 200, 16);
	}

	@Override
	public void keyPressed(int k)
	{
		if(k == KeyEvent.VK_W && player.getY() != 0
				&& map.getTileAt((player.getX() / Tile.TILE_SIZE), (player.getY()/ Tile.TILE_SIZE)).getType() != Tile.TILE_WALL)
			player.move(0, -1);
		if(k == KeyEvent.VK_A && player.getX() != 0
				&& map.getTileAt(((player.getX() - 32) / Tile.TILE_SIZE), ((player.getY() + 32)/ Tile.TILE_SIZE)).getType() != Tile.TILE_WALL)
			player.move(-1, 0);
		if(k == KeyEvent.VK_S && player.getY() != (GamePanel.HEIGHT - (Tile.TILE_SIZE + Tile.TILE_SIZE))
				&& map.getTileAt((player.getX() / Tile.TILE_SIZE), ((player.getY() + 64)/ Tile.TILE_SIZE)).getType() != Tile.TILE_WALL)
			player.move(0, 1);
		if(k == KeyEvent.VK_D && player.getX() != GamePanel.WIDTH - Tile.TILE_SIZE
				&& map.getTileAt(((player.getX() + 32) / Tile.TILE_SIZE), ((player.getY() + 32)/ Tile.TILE_SIZE)).getType() != Tile.TILE_WALL)
			player.move(1, 0);
		
		if(k == KeyEvent.VK_ESCAPE)
		{
			gsm.changeState(GameStateManager.MENU_STATE);
			gsm.unloadState(GameStateManager.TEST_STATE);
		}
		
	}

	@Override
	public void keyReleased(int k)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void collisionCheck(){
	//Collision tick is counts down before the collision is checked again so that the player will not infinitely take damage
		if(collisionTick == 0){
			for(int i = 0; i < enemies.length; i++){
				if(player.getX() == enemies[i].getX() && player.getY() == enemies[i].getY())
				{
					ouchies++;
					collisionTick = 120;
				}
			}
		}else if(collisionTick > 0)
			collisionTick--;
	}
}
