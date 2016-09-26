package me.oso.lib.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.glfw.*;

/**
 * Window.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class Window {

	private long window;
	private int pixWidth, pixHeight;
	//Fields avoid GC to destroy the handle
	private Input keyCallback;
	private MouseHandler mouseCallback;
	private static DoubleBuffer mx, my;
	private static double mouseX, mouseY;
	
	static {
		mx = BufferUtils.createDoubleBuffer(1);
		my = BufferUtils.createDoubleBuffer(1);
	}
	
	public Window(int width, int height, String name, int samples) {
		if (glfwInit() != GL_TRUE) {
			System.err.println("Could not initialize GLFW!");
			return;
		}
		OSSpecifics.GLFWSpecifics();
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);
		glfwWindowHint(GLFW_SAMPLES, samples);
		window = glfwCreateWindow(width, height, name, NULL, NULL);
		
		if (window == NULL) {
			System.err.println("Could not create GLFW window!");
			return;
		}
		
		ByteBuffer FBW = BufferUtils.createByteBuffer(4), FBH = BufferUtils.createByteBuffer(4); 
		glfwGetFramebufferSize(window, FBW, FBH);
		pixWidth = FBW.getInt(0);
		pixHeight = FBH.getInt(0);
		InitWindow(width, height);
	}
	
	public void release() {
		keyCallback.release();
		mouseCallback.release();
	}
	
	private void InitWindow(int width, int height) {
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		glfwSetKeyCallback(window, keyCallback = new Input());
//		glfwSetWindowSizeCallback(window, sizeCallback = new ResizeHandler());
		glfwSetMouseButtonCallback(window, mouseCallback = new MouseHandler());
//		glfwSetScrollCallback(window, scrollCallback = new ScrollHandler());
		glfwMakeContextCurrent(window);
		glfwSwapInterval(0);
		glfwShowWindow(window);
	}
	
	public void update() {
		glfwSwapBuffers(window);
		glfwGetCursorPos(window, mx, my);
		mouseX = mx.get(0);
		mouseY = my.get(0);
	}


	public int getPixWidth() {
		return pixWidth;
	}


	public void setPixWidth(int pixWidth) {
		this.pixWidth = pixWidth;
	}


	public int getPixHeight() {
		return pixHeight;
	}


	public void setPixHeight(int pixHeight) {
		this.pixHeight = pixHeight;
	}


	public long getID() {
		return window;
	}

	public static double getMouseX() {
		return mouseX;
	}

	public static void setMouseX(double mouseX) {
		Window.mouseX = mouseX;
	}

	public static double getMouseY() {
		return mouseY;
	}

	public static void setMouseY(double mouseY) {
		Window.mouseY = mouseY;
	}
}
