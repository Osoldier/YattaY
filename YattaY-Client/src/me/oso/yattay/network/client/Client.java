package me.oso.yattay.network.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Thomas on 24 sept. 2016
 */
public class Client extends Thread {

	private Socket socket;
	private static final int PORT = 5675;

	public Client() {
		this.setName("Client");
	}

	public void ConnectTo(String ip) {
		try {
			InetAddress server = InetAddress.getByName(ip);
			socket = new Socket(server, PORT);
			System.out.println("[Client] Connected with " + ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outToServer.writeBytes("Hey" + '\n');
			String msg = "";
			while (socket.isConnected()) {
				if (inFromServer.ready()) {
					msg = inFromServer.readLine();
					System.out.println("[Client] " + msg + " from server");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
