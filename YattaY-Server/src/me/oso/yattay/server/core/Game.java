package me.oso.yattay.server.core;

import java.util.HashMap;
import java.util.Map;

import me.oso.yattay.player.Player;
import me.oso.yattay.world.Level;

/**
 * Created by Thomas on 4 oct. 2016
 */
public class Game extends Thread {

	private Level level;
	private Map<Integer, Player> red, blue;
	private int maxPlayersTeam;
	private boolean running;
	
	public Game(Level level, int maxPlayersTeam) {
		this.level = level;
		this.maxPlayersTeam = maxPlayersTeam;
		this.red = new HashMap<>();
		this.blue = new HashMap<>();
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
	
	public boolean join(int uid, String name) {
		if(red.size() == maxPlayersTeam && blue.size() == maxPlayersTeam) return false;
		
		if(red.size() >= blue.size()) {
			blue.put(uid, new Player(name));
			blue.get(uid).getPosition().x = level.getBlueX();
			blue.get(uid).getPosition().y = level.getBlueY();
		} else {
			red.put(uid, new Player(name));
			red.get(uid).getPosition().x = level.getRedX();
			red.get(uid).getPosition().y = level.getRedY();
		}
		return true;
	}

	public Map<Integer, Player> getRed() {
		return red;
	}

	public Map<Integer, Player> getBlue() {
		return blue;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}
