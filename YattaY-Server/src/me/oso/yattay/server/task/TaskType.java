package me.oso.yattay.server.task;

/**
 * Created by Thomas on 6 oct. 2016
 */
public enum TaskType {

	//Connection-related tasks
	JOIN_REQUEST(0, 1, false, 1), JOIN_ACCEPT(1, 1, true, 2), JOIN_DENY(2, 1, true, 1), KICK(3, 1, true, 1), QUIT_REQUEST(4, 1, false, 1),
	QUIT_ACK(5, 1, true, 1),
	//Level-related tasks
	LVL_START(10, 2, true, 1), LVL_DIMS(11, 2, true, 3), BLOCK_INF(12, 2, true, 3), BLUE_SPAWN(13, 2, true, 3), RED_SPAWN(14, 2, true, 3),
	//Server-related tasks
	PING(20, 0, false, 2);
	
	private int opcode;
	private int priority;
	private boolean fromServer;
	private int minArgs;
	
	private TaskType(int opcode, int priority, boolean fromServer, int minArgs) {
		this.priority = priority;
		this.fromServer = fromServer;
		this.minArgs = minArgs;
		this.opcode = opcode;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isFromServer() {
		return fromServer;
	}

	public int getMinArgs() {
		return minArgs;
	}

	public int getOpcode() {
		return opcode;
	}
	
}
