package me.oso.yattay.core;

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
	@SuppressWarnings("unused")
	private Input keyCallback;
	
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
	
	
	private void InitWindow(int width, int height) {
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
		glfwSetKeyCallback(window, keyCallback = new Input());
//		glfwSetWindowSizeCallback(window, sizeCallback = new ResizeHandler());
//		glfwSetMouseButtonCallback(window, mouseCallback = new MouseHandler());
//		glfwSetScrollCallback(window, scrollCallback = new ScrollHandler());
		glfwMakeContextCurrent(window);
		glfwSwapInterval(0);
		glfwShowWindow(window);
	}
	
	public void swapBuffers() {
		glfwSwapBuffers(window);
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
}
