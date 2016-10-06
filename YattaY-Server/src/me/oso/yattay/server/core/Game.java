package me.oso.yattay.server.core;

import me.oso.yattay.player.Player;
import me.oso.yattay.world.Level;

/**
 * Created by Thomas on 4 oct. 2016
 */
public class Game extends Thread {

	private Level level;
	private Player[] red, blue;
	private boolean running;
	private static final int MAX_PLAYERS_PER_TEAM = 5;
	
	
	public Game(Level level) {
		this.level = level;
		this.red = new Player[MAX_PLAYERS_PER_TEAM];
		this.blue = new Player[MAX_PLAYERS_PER_TEAM];
	}
	
	@Override
	public void run() {
		while (running) {
			
		}
	}
	
	public void update() {
		 
	}

	public Player[] getRed() {
		return red;
	}

	public Player[] getBlue() {
		return blue;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
