package me.oso.yattay.server.file;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import me.oso.yattay.player.*;

/**
 * DataParser.java
 * 
 * @author Ibanez Thomas
 * @date 17 avr. 2017
 */
public class DataParser {

	private File file;
	
	public DataParser(String file) {
		this.file = new File(file);
		try {
			if (!this.file.exists()) {
				this.file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, PlayerStatus> getRegistredUsers() {
		Map<String, PlayerStatus> users = new HashMap<String, PlayerStatus>();
		try {
			for (String s : Files.readAllLines(file.toPath())) {
				String[] line = s.split(" ");
				if(line[0].equals("uk")) {
					users.put(line[1], PlayerStatus.fromID(Integer.parseInt(line[2])));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}
}
