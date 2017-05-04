package me.oso.yattay.server.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import me.oso.yattay.player.*;
import me.oso.yattay.server.file.*;
import me.oso.yattay.server.network.Connection;
import me.oso.yattay.server.network.NetListener;
import me.oso.yattay.server.task.CommandParser;
import me.oso.yattay.server.task.Task;
import me.oso.yattay.server.task.TaskType;
import me.oso.yattay.world.Level;

/**
 * Server.java
 * 
 * @author Ibanez Thomas
 * @date 21 sept. 2016
 */
public class Server {

	// Logger object used to log messages
	private static Logger log;
	// Listener for incoming connection
	private NetListener netListener;
	// Config file parser
	private ConfigParser config;
	// Server data file
	private DataParser data;
	// All the instances of game being played simultaneously
	private Game[] games;
	// All map files (read from config)
	private String[] mapFiles;

	private boolean running = true;
	// Mutexs
	public static Object connexionsMutex = new Object();

	private int MAX_PLAYERS;
	// Server registered users(IP -> status) (may not be useful)
	private Map<String, PlayerStatus> users;

	private Map<Integer, Connection> usersConnections;
	private List<Integer> usersIDs;
	// Queue of task to be executed
	private static PriorityQueue<Task> todo;

	private final String CONF_FILE = "server.conf";
	private final String MAP_DIR = "maps/";

	public static final double TICKRATE = 128.0;

	public Server() {
		// Init objects
		this.config = new ConfigParser(CONF_FILE);
		this.data = new DataParser("serverdata.dat");
		this.users = this.data.getRegistredUsers();
		this.usersIDs = new ArrayList<>();
		this.netListener = new NetListener(this.config.getAttribute("bind-address"), Integer.parseInt(this.config.getAttribute("bind-port")));
		todo = new PriorityQueue<>((Task o1, Task o2) -> Integer.compare(o1.getType().getPriority(), o2.getType().getPriority()));
		// Init instances
		this.games = new Game[Integer.parseInt(this.config.getAttribute("max-instances"))];
		this.mapFiles = this.config.getAttribute("maps").replace(" ", "").split(",");
		Level m1 = LevelParser.load(MAP_DIR + this.mapFiles[0] + ".ymf");
		int mp = Integer.parseInt(this.config.getAttribute("max-players-team"));
		this.MAX_PLAYERS = Integer.parseInt(this.config.getAttribute("max-instances")) * 2 * Integer.parseInt(this.config.getAttribute("max-players-team"));
		for (int i = 0; i < games.length; i++) {
			games[i] = new Game(new Level(m1), mp);
		}
		// start one instance
		games[0].start();
	}

	public void runTask(Task t) {
		switch(t.getType()) {
			case BLOCK_INFO:
				break;
			case BLUE_SPAWN:
				break;
			case JOIN_ACCEPT:
				break;
			case JOIN_DENY:
				break;
			case JOIN_REQUEST:
				if(netListener.getConnections().size() < MAX_PLAYERS) {
					int sess_id = 0;
					boolean specificJoin = false;
					if(t.getArgs().length == 2) {
						sess_id = t.getArgI(1);
					}
					
					if(sess_id >= 0 && sess_id < games.length) {
						
					}
				}
				break;
			case KICK:
				break;
			case LVL_DIMS:
				break;
			case LVL_START:
				break;
			case PING:
				break;
			case QUIT_ACK:
				break;
			case QUIT_REQUEST:
				break;
			case RED_SPAWN:
				break;
			case TEAM_CHANGE_NOPE:
				break;
			case TEAM_CHANGE_OK:
				break;
			case TEAM_CHANGE_REQUEST:
				break;
			default:
				break;
		}
	}

	/**
	 * Starts the server, opens the socket and parse commands
	 */
	public void start() {
		log.info("Server started");
		this.netListener.start();
		try {
			BufferedReader br = null;
			br = new BufferedReader(new InputStreamReader(System.in));
			String input;
			int pingCnt = 0;
			double serverPeriodNS = ((double) 1e9 / (double) TICKRATE);
			while (running) {
				long start = System.nanoTime();
				pingCnt++;
				if (pingCnt == 5 * TICKRATE) {
					for (Connection cn : netListener.getConnections()) {
						cn.getMessageList().add(new Task(TaskType.PING, String.valueOf(Instant.now().toEpochMilli())).toMessage());
					}
					pingCnt = 0;
				}

				if (br.ready()) {
					input = br.readLine();
					CommandParser.ParseInput(input);
				}

				if (!todo.isEmpty()) {
					Task t = todo.poll();
					runTask(t);
				}

				synchronized (connexionsMutex) {
					Iterator<Connection> iterator = netListener.getConnections().iterator();
					for (; iterator.hasNext();) {
						Connection c = iterator.next();
						if (c.getSocket().isClosed()) {
							iterator.remove();
						}
					}
				}

				try {
					Thread.sleep((long) Math.max((serverPeriodNS - (System.nanoTime() - start)) / 1e6, 0));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		dispose();
	}

	/**
	 * Stops the server, close all connections
	 */
	public void dispose() {
		log.info("Shutting down server...");
		this.netListener.setRunning(false);
		for (Connection connection : this.netListener.getConnections()) {
			connection.shutDown();
		}
		try {
			this.netListener.getCoSocket().close();
		} catch (IOException e) {
			// Log nothing because an IOEx at this point is normal
		}

		for (Game game : games) {
			game.setRunning(false);
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
