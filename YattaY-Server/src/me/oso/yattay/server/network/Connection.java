package me.oso.yattay.server.network;

import java.io.*;
import java.net.*;

import me.oso.yattay.server.core.Server;

/**
 * Connexion.java
 * 
 * @author Ibanez Thomas
 * @date 21 sept. 2016
 */
public class Connection extends Thread {

	private Socket socket;
	private BufferedReader inFromClient;
	private DataOutputStream outToClient;

	public Connection(Socket socket) {
		this.socket = socket;
		this.setName("Connection with " + socket.getInetAddress());
	}

	@Override
	public void run() {
		try {
			inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outToClient = new DataOutputStream(socket.getOutputStream());
			String message = "";

			while (!socket.isClosed()) {
				message = inFromClient.readLine();
				if (message == null || message == "" || message == "-1") {
					shutDown();
				} else {
					Server.getLog().info(message + " from " + socket.getInetAddress());
				}
			}
		} catch (IOException e1) {
			// Let the socket close
		}
	}

	public void shutDown() {
		try {
			Server.getLog().info("Client "+socket.getInetAddress()+" disconnected");
			socket.close();
			inFromClient.close();
			outToClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}
}
