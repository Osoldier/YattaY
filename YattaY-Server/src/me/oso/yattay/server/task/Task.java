package me.oso.yattay.server.task;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Thomas on 6 oct. 2016
 */
public class Task {
	
	private TaskType type;
	private String[] args;
	
	public Task(TaskType type, String... args) {
		this.type = type;
		this.args = args;
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
		for (String s : args) {
			for (Byte b : s.getBytes()) {
				m.add(b);
			}
		}
		byte[] mes = new byte[m.size()];
		for (int i = 0; i < mes.length; i++) {
			mes[i] = m.get(i);
		}
		return mes;
	}
}
