package me.oso.yattay.editor;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.channels.FileChannel;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL13;

import me.oso.lib.core.Input;
import me.oso.lib.core.MouseHandler;
import me.oso.lib.core.Window;
import me.oso.lib.graphics.Camera2d;
import me.oso.yattay.editor.mask.Mask;
import me.oso.yattay.editor.ui.BtnBlock;
import me.oso.yattay.file.FileChooser;
import me.oso.yattay.file.Saver;
import me.oso.yattay.ui.Menu;
import me.oso.yattay.world.Block;
import me.oso.yattay.world.BlockType;
import me.oso.yattay.world.Level;
import me.oso.yattay.world.LevelRenderer;

/**
 * Editor.java
 * 
 * @author Ibanez Thomas
 * @date 22 sept. 2016
 */
public class Editor {

	public static final String TEX_PATH = "src/res/textures/";
	public static final int WIDTH = 1365, HEIGHT = 768;
	public static final int LEVEL_WIDTH = 1000, LEVEL_HEIGHT = 200;
	public static final int BTN_SIZE = 32;
	public static final int BTN_PER_LINE = 4;

	public double delta;

	private static Window window;
	private Camera2d camera;
	private boolean running;
	private MasterRenderer masterRenderer;
	private Menu blocMenu;
	private Level level;
	private Mask mask;

	public Editor() {
		window = new Window(WIDTH, HEIGHT, "YattaY Editor", 1, false);
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
		this.camera = new Camera2d(0, 8);
		this.mask = new Mask();
	}

	private void buildMenu() {
		int i = 0;
		for (BlockType bt : BlockType.class.getEnumConstants()) {
			int x = i * BTN_SIZE % (BTN_PER_LINE * BTN_SIZE) + BTN_SIZE / 2;
			int y = (int) Math.floor(i * BTN_SIZE / (BTN_PER_LINE * BTN_SIZE)) * BTN_SIZE + BTN_SIZE / 2;
			this.blocMenu.getComponents().add(new BtnBlock(x, y, BTN_SIZE, BTN_SIZE, bt));
			i++;
		}
	}

	public void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		this.camera.lookThrough();
		this.masterRenderer.renderUI(blocMenu);
		this.masterRenderer.renderLevel(camera, level);
		this.masterRenderer.renderMask(mask);
		window.update();
	}

	boolean released2 = true;
	boolean released1 = true;

	public void update() {
		glfwPollEvents();
		//check for menu events
		this.blocMenu.update();
		// move
		if (Input.isKeyDown(GLFW_KEY_LEFT)) {
			camera.getPosition().x -= 5f;
		} else if (Input.isKeyDown(GLFW_KEY_RIGHT)) {
			camera.getPosition().x += 5f;
		}
		if (Input.isKeyDown(GLFW_KEY_UP)) {
			camera.getPosition().y -= 5f;
		} else if (Input.isKeyDown(GLFW_KEY_DOWN)) {
			camera.getPosition().y += 5f;
		}
		//change brush size
		if (Input.isKeyPressed(GLFW_KEY_2)) {
			if (released2) {
				this.mask.setSize(mask.getSize() + 2);
				released2 = false;
			}
		} else {
			released2 = true;
		}
		if (Input.isKeyPressed(GLFW_KEY_1)) {
			if (released1) {
				this.mask.setSize(Math.max(1, mask.getSize() - 2));
				released1 = false;
			}
		} else {
			released1 = true;
		}
		// update mask info
		this.mask.setX((int) Math.floor((camera.getPosition().x + window.getMouseX() + Block.SIZE / 2) / Block.SIZE));
		this.mask.setY((int) Math.floor((camera.getPosition().y + window.getMouseY() + Block.SIZE / 2) / Block.SIZE));

		this.mask.getRenderPosition().x = mask.getX() * Block.SIZE - camera.getPosition().x;
		this.mask.getRenderPosition().y = mask.getY() * Block.SIZE - camera.getPosition().y;

		this.mask.setBt(BtnBlock.getLastSelectedType());

		// block placement
		if (MouseHandler.isButtonDown(0)) {
			if (window.getMouseX() > LevelRenderer.LEFT_OFFSET) {
				for (int i = -(mask.getSize() / 2); i <= (mask.getSize() / 2); i++) {
					for (int j = -(mask.getSize() / 2); j <= (mask.getSize() / 2); j++) {
						if (mask.getX() + i >= 0 && mask.getX() + i < LEVEL_WIDTH && mask.getY() + j >= 0 && mask.getY() + j < LEVEL_HEIGHT)
							this.level.getLevel()[mask.getX() + i][mask.getY() + j].setType(mask.getBt());
					}
				}
			}
		}
		
		//saving
		if(Input.isKeyPressed(GLFW_KEY_LEFT_CONTROL) && Input.isKeyPressed(GLFW_KEY_S)) {
			Saver.Save(this.level, FileChooser.choose("ymf"));
		}
	}

	public void start() {
		running = true;

		long lastTime = System.nanoTime();
		delta = 0.0;
		double ns = 1000000000.0 / 120.0;
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
				running = false;
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
