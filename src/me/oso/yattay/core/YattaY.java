package me.oso.yattay.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;

import me.oso.yattay.world.*;

/**
 * YattaY.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class YattaY {

	private final int WIDTH = 1280, HEIGHT = 720;
	private Level level;
	Window window;
	private boolean running;
	
	public YattaY() {
		window = new Window(WIDTH, HEIGHT, "YattaY", 1);
		GL.createCapabilities();
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		glEnable(GL_BLEND);
		glEnable(GL20.GL_POINT_SPRITE);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		glEnable(GL13.GL_MULTISAMPLE);
		glViewport(0, 0, window.getPixWidth(), window.getPixHeight());
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		int error = glGetError();
		if (error != GL_NO_ERROR)
			System.out.println("Error " + error);
		
		if (glfwWindowShouldClose(window.getID()) == GL_TRUE)
			running  = false;
	}
	
	public void update() {
		glfwPollEvents();

	}
	
	public void start() {
		level = new Level(WIDTH, WIDTH);
		level.generate();
		running = true;
		while(running) {
			render();
			update();
		}
	}
	
	public static void main(String[] args) {
		YattaY game = new YattaY();
		game.start();
	}

}
