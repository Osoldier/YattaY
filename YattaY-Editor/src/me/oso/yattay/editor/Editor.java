package me.oso.yattay.editor;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;

import me.oso.lib.core.*;
import me.oso.lib.graphics.*;
import me.oso.yattay.editor.ui.*;
import me.oso.yattay.ui.*;
import me.oso.yattay.world.*;

/**
 * Editor.java
 * @author Ibanez Thomas
 * @date 22 sept. 2016
 */
public class Editor {
	
	public static final String TEX_PATH = "res/textures/";
	public static final int WIDTH = 1365, HEIGHT = 768;
	public static final int LEVEL_WIDTH = 1000, LEVEL_HEIGHT = 200;
	private static final int BTN_SIZE = 32;
	
	private static Window window;
	private Camera2d camera;
	private boolean running;
	private MasterRenderer masterRenderer;
	private Menu blocMenu;
	private Level level;
	
	
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
		buildMenu();
		this.level = new Level(LEVEL_WIDTH, LEVEL_HEIGHT);
		this.level.generate();
		this.camera = new Camera2d(0, 70*16);
	}
	
	private void buildMenu() {
		int i = 0;
		for (BlockType bt : BlockType.class.getEnumConstants()) {
			int x = i*BTN_SIZE % (4*BTN_SIZE) + BTN_SIZE/2;
			int y = (int)Math.floor(i*BTN_SIZE / (4*BTN_SIZE))*BTN_SIZE + BTN_SIZE/2;
			this.blocMenu.getComponents().add(new BtnBlock(x, y, BTN_SIZE, BTN_SIZE, bt));
			i++;
		}
	}
	
	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		this.camera.lookThrough();
		this.masterRenderer.renderUI(blocMenu);
		this.masterRenderer.renderLevel(camera, level);
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
		if(Input.isKeyDown(GLFW_KEY_UP)) {
			camera.getPosition().y -= 5f;
		} else if(Input.isKeyDown(GLFW_KEY_DOWN)) {
			camera.getPosition().y += 5f;	
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

	public static Window getWindow() {
		return window;
	}
	
}
