package me.oso.yattay.server.task;

/**
 * Created by Thomas on 6 oct. 2016
 */
public class Task {
	
	private TaskType type;
	private String[] args;
	
	public Task(TaskType type, String[] args) {
		this.type = type;
		this.args = args;
	}

	public TaskType getType() {
		return type;
	}

	public String[] getArgs() {
		return args;
	}
}
