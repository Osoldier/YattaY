package me.oso.lib.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class MouseHandler extends GLFWMouseButtonCallback {

	private static int[] buttons = new int[5];
	
	@Override
	public void invoke(long window, int button, int action, int mods) {
		buttons[button] = action;
	}
	
	public static boolean isButtonDown(int button) {
		return buttons[button] == GLFW.GLFW_PRESS;
	}

}
