package me.oso.yattay.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL13;

import me.it.lib.graphics.Camera2d;
import me.oso.yattay.world.Level;

/**
 * YattaY.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class YattaY {

	private final int WIDTH = 1280, HEIGHT = 720;
	private Level level;
	private MasterRenderer masterRenderer;
	private Camera2d camera;
	Window window;
	private boolean running;
	
	public YattaY() {
		window = new Window(WIDTH, HEIGHT, "YattaY", 1);
		GL.createCapabilities();
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		glEnable(GL_BLEND);
		glDisable(GL_CULL_FACE);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		glEnable(GL13.GL_MULTISAMPLE);
		glViewport(0, 0, window.getPixWidth(), window.getPixHeight());
		this.masterRenderer = new MasterRenderer(window.getPixWidth(), window.getPixHeight());
		this.camera = new Camera2d();
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		this.masterRenderer.render(camera, level);
		
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
		level = new Level(WIDTH/2, HEIGHT/2);
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
