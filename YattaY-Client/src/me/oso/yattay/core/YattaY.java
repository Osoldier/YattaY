package me.oso.yattay.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL13;

import me.oso.lib.core.Input;
import me.oso.lib.core.Window;
import me.oso.lib.graphics.*;
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
	private static Window window;
	private boolean running;
	
	public YattaY() {
		window = new Window(WIDTH, HEIGHT, "YattaY", 1, false);
		GL.createCapabilities();
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		glEnable(GL_BLEND);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_FRONT);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		glEnable(GL13.GL_MULTISAMPLE);
		glClearColor(0, 0, 0, 1);
		glViewport(0, 0, window.getPixWidth(), window.getPixHeight());
		this.masterRenderer = new MasterRenderer(window.getPixWidth(), window.getPixHeight());
		this.camera = new Camera2d();
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		this.camera.lookThrough();
		this.masterRenderer.render(camera, level);
		window.update();
	}
	
	public void update() {
		glfwPollEvents();
		if(Input.isKeyDown(GLFW_KEY_LEFT)) {
			camera.getPosition().x -= 5f;
		} else if(Input.isKeyDown(GLFW_KEY_RIGHT)) {
			camera.getPosition().x += 5f;	
		}
	}
	
	public void start() {
		level = new Level(500, 255);
		level.generate();
		camera.getPosition().x = 500*16/2;
		camera.getPosition().y = 128*16/2;
		running = true;
		
		long lastTime = System.nanoTime();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1.0) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
			int error = glGetError();
			if (error != GL_NO_ERROR)
				System.out.println("Error " + error);
			
			if (glfwWindowShouldClose(window.getID()))
				running  = false;
		}

		glfwDestroyWindow(window.getID());
		glfwTerminate();
	}
	
	public static void main(String[] args) {
		YattaY game = new YattaY();
		game.start();
	}

	public static Window getWindow() {
		return window;
	}

}
