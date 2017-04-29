package me.oso.yattay.server.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import me.oso.yattay.server.core.Server;
import me.oso.yattay.server.task.CommandParser;
import me.oso.yattay.server.task.Task;

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
	private Queue<byte[]> messageList;

	public Connection(Socket socket) {
		this.messageList = new LinkedList<>();
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
				if (inFromClient.ready()) {
					message = inFromClient.readLine();
					// if the client closed the connection remotely
					if (message == null || message == "" || message == "-1") {
						shutDown();
					} else {
						// add the incoming command to the todo list (verified
						// by the parser)
						Task t = CommandParser.ParseNetwork(message);
						if (t != null)
							Server.getTodo().add(t);
					}
				}
				
				if(!messageList.isEmpty()) {
					try {
						outToClient.write(messageList.poll());
					} catch(IOException e) {
						shutDown();
					}
					
				}
			}
		} catch (IOException e1) {
			// Let the socket close
		}
	}

	public void shutDown() {
		try {
			socket.close();
			inFromClient.close();
			outToClient.close();
			Server.getLog().info("Client " + socket.getInetAddress() + " disconnected");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Socket getSocket() {
		return socket;
	}

	public Queue<byte[]> getMessageList() {
		return messageList;
	}
}
