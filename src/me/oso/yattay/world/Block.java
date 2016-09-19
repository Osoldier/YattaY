package me.oso.yattay.world;

import me.it.lib.graphics.Loader;
import me.it.lib.graphics.Model;

/**
 * Block.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class Block {
	
	public static final int SIZE = 16;
	private BlockType type;
	private int damages;
	
	public static Model model;
	private static final float[] vertices = {
			-0.5f, -0.5f,
			0.5f, -0.5f,
			0.5f, 0.5f, 
			
			0.5f, 0.5f,
			-0.5f, 0.5f,
			-0.5f, -0.5f 
	};
	
	private static final float[] texCoords = {
			0, 0,
			1, 0, 
			1, 1,
			
			1, 1, 
			0, 1,
			0, 0
	};
	
	static {
		model = Loader.createModelVAO(vertices, 2, texCoords);
	}

	public Block(BlockType type) {
		this.type = type;
		this.damages = 0;
	}

	public BlockType getType() {
		return type;
	}

	public void setType(BlockType type) {
		this.type = type;
	}

	public int getDamages() {
		return damages;
	}

	public void setDamages(int damages) {
		this.damages = damages;
	}	
}
