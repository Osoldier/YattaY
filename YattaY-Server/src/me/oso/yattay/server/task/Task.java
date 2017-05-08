package me.oso.yattay.server.task;

import java.util.LinkedList;
import java.util.List;

import me.oso.yattay.server.network.Connection;

/**
 * Created by Thomas on 6 oct. 2016
 */
public class Task {
	
	private TaskType type;
	private Connection parent;
	private String[] args;
	
	public Task(TaskType type, Connection parent, String... args) {
		this.type = type;
		this.parent = parent;
		this.args = args;
	}
	
	public int getArgI(int i) {
		return Integer.parseInt(args[i]);
	}
	
	public String getArgS(int i) {
		return args[i];
	}

	public TaskType getType() {
		return type;
	}

	public String[] getArgs() {
		return args;
	}
	
	public byte[] toMessage() {
		List<Byte> m = new LinkedList<>();
		m.add((byte)type.getOpcode());
		int i = 0;
		for (String s : args) {
			i++;
			for (Byte b : s.getBytes()) {
				m.add(b);
			}
			if(i < args.length) {
				m.add((byte)';');
			}
		}
		byte[] mes = new byte[m.size()];
		for (i = 0; i < mes.length; i++) {
			mes[i] = m.get(i);
		}
		return mes;
	}

	public Connection getParent() {
		return parent;
	}
}
