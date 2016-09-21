package me.oso.yattay.network.server;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * NetListener.java
 * @author Ibanez Thomas
 * @date 21 sept. 2016
 */
public class NetListener extends Thread {

	private boolean running;
	private ServerSocket coSocket;
	private List<Connection> connections;


	public NetListener() {
		this.setName("Net listener");
	}
	
	@Override
	public void run() {
		running = true;
		
		try {
			coSocket = new ServerSocket(Server.PORT);
			connections = new LinkedList<Connection>();
			running = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (running) {
			try {
				Socket clientSocket = coSocket.accept();
				Connection co = new Connection(clientSocket);
				connections.add(co);
				co.start();
				Server.getLog().info("Connected with " + clientSocket.getInetAddress());
			} catch (IOException e) {
				
			}
		}
	}
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public ServerSocket getCoSocket() {
		return coSocket;
	}
	
}
