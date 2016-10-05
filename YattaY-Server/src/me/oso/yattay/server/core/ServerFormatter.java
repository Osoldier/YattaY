package me.oso.yattay.server.core;

import java.util.logging.*;

/**
 * ServerFormatter.java
 * @author Ibanez Thomas
 * @date 21 sept. 2016
 */
public class ServerFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		return "["+record.getLevel().toString()+"] "+record.getMessage()+"\n";
	}

}
