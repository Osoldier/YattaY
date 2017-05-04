package me.oso.yattay.server.task;

/**
 * Created by Thomas on 6 oct. 2016
 */
public enum TaskType {

	//Connection-related tasks
	JOIN_REQUEST(0, 1, false, 1, 0), JOIN_ACCEPT(1, 1, true, 3, 0), JOIN_DENY(2, 1, true, 1, 0), KICK(3, 1, false, 1, 0), QUIT_REQUEST(4, 1, false, 1, 0),
	QUIT_ACK(5, 1, true, 1, 0),
	//Level-related tasks
	LVL_START(10, 2, true, 1, 0), LVL_DIMS(11, 2, true, 3, 0), BLOCK_INFO(12, 2, true, 3, 0), BLUE_SPAWN(13, 2, true, 3, 0), RED_SPAWN(14, 2, true, 3, 0),
	//Server-related tasks
	PING(20, 0, false, 2, 0),
	//Team-related requests
	TEAM_CHANGE_REQUEST(21, 1, false, 1, 0), TEAM_CHANGE_OK(22, 1, true, 1, 0), TEAM_CHANGE_NOPE(23, 1, true, 1, 0);
	
	private int opcode;
	private int priority;
	private boolean fromServer;
	private int minArgs;
	private int perm;
	
	private TaskType(int opcode, int priority, boolean fromServer, int minArgs, int perm) {
		this.priority = priority;
		this.fromServer = fromServer;
		this.minArgs = minArgs;
		this.opcode = opcode;
		this.perm = perm;
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

	public int getPerm() {
		return perm;
	}
	
}
