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
	
	public Game(Level level, int maxPlayersTeam) {
		this.level = level;
		this.red = new Player[maxPlayersTeam];
		this.blue = new Player[maxPlayersTeam];
	}
	
	@Override
	public void run() {
		
		running = true;
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / Server.TICKRATE;
		long timer = System.currentTimeMillis();
		int updates = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				update();
				updates++;
				delta--;
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if(updates < Server.TICKRATE-5)
					Server.getLog().warning("Tickrate is lower than expected, maybe the server is overloaded");
				updates = 0;
			}
		}
	}
	
	
	public void update() {
		
	}
	
	public boolean join(String name) {
		return false;
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
