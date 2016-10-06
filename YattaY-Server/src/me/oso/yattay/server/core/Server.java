package me.oso.yattay.server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import me.oso.yattay.server.file.ConfigParser;
import me.oso.yattay.server.file.LevelParser;
import me.oso.yattay.server.network.Connection;
import me.oso.yattay.server.network.NetListener;
import me.oso.yattay.server.task.Task;
import me.oso.yattay.world.Level;

/**
 * Server.java
 * 
 * @author Ibanez Thomas
 * @date 21 sept. 2016
 */
public class Server {

	private static Logger log;
	private NetListener netListener;
	private ConfigParser config;
	private Game[] games;
	private String[] mapFiles;
	
	private final String CONF_FILE = "server.conf";
	private final String MAP_DIR = "maps/";
	
	private static Queue<Task> todo;
	
	public Server() {
		this.config = new ConfigParser(CONF_FILE);
		this.netListener = new NetListener(this.config.getAttribute("bind-address"), Integer.parseInt(this.config.getAttribute("bind-port")));
		todo = new LinkedList<Task>();
		
		this.games = new Game[Integer.parseInt(this.config.getAttribute("max-instances"))];
		this.mapFiles = this.config.getAttribute("maps").replace(" ", "").split(",");
		Level m1 = LevelParser.load(MAP_DIR+this.mapFiles[0]+".ymf");
		
		for (int i = 0; i < games.length; i++) {
			games[i] = new Game(new Level(m1));
		}
		//start one instance
		games[0].start();
	}

	public void start() {
		log.info("Server started");
		this.netListener.start();
		try {
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			while (true) {
				input = br.readLine();
				if(input.equals("stop")) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		dispose();
	}

	public void dispose() {
		log.info("Shutting down server...");
		this.netListener.setRunning(false);
		for (Connection connection : this.netListener.getConnections()) {
			connection.shutDown();
		}
		try {
			this.netListener.getCoSocket().close();
		} catch (IOException e) {
			//Log nothing because an IOEx at this point is normal
		}
		log.info("Server stopped !");
	}

	public static void main(String[] args) {
		log = Logger.getLogger("YattaY Server");
		log.setLevel(java.util.logging.Level.ALL);
		log.setUseParentHandlers(false);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(java.util.logging.Level.ALL);
		handler.setFormatter(new ServerFormatter());
		log.addHandler(handler);

		new Server().start();
	}

	public static Logger getLog() {
		return log;
	}

	public static Queue<Task> getTodo() {
		return todo;
	}
}
