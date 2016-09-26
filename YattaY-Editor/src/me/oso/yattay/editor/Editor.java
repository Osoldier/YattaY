package me.oso.yattay.editor;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL13;

import me.oso.lib.core.Input;
import me.oso.lib.core.Window;
import me.oso.lib.graphics.Camera2d;
import me.oso.lib.graphics.Texture;
import me.oso.yattay.ui.Menu;

/**
 * Editor.java
 * @author Ibanez Thomas
 * @date 22 sept. 2016
 */
public class Editor {
	
	public static final int WIDTH = 1365, HEIGHT = 768;
	private Window window;
	private Camera2d camera;
	private boolean running;
	private MasterRenderer masterRenderer;
	private Menu blocMenu;
	
	
	public Editor() {
		window =  new Window(WIDTH, HEIGHT, "YattaY Editor", 1);
		GL.createCapabilities();
		System.out.println("OpenGL: " + glGetString(GL_VERSION));
		glEnable(GL_BLEND);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_FRONT);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE);
		glEnable(GL13.GL_MULTISAMPLE);
		glClearColor(0, 0, 0, 1);
		glViewport(0, 0, window.getPixWidth(), window.getPixHeight());
		this.masterRenderer = new MasterRenderer();
		this.blocMenu = new Menu();
		this.blocMenu.getComponents().add(new BtnBlock(500, 500, 1000, 1000, new Texture("res/textures/blocks.png")));
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		this.masterRenderer.renderUI(blocMenu);
		window.update();
	}
	
	public void update() {
		glfwPollEvents();
		this.blocMenu.update();
		if(Input.isKeyDown(GLFW_KEY_LEFT)) {
			camera.getPosition().x -= 5f;
		} else if(Input.isKeyDown(GLFW_KEY_RIGHT)) {
			camera.getPosition().x += 5f;	
		}
	}
	
	public void start() {
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
			
			if (glfwWindowShouldClose(window.getID()) == GL_TRUE)
				running  = false;
		}

		glfwDestroyWindow(window.getID());
		glfwTerminate();
	}
	
	public static void main(String[] args) {
		new Editor().start();
	}
	
}
