package me.oso.yattay.world;

import me.oso.lib.graphics.*;

/**
 * BlockType.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public enum BlockType {

	AIR(null, 0), GRASS(new Texture("res/textures/grass.png"), 10), DIRT(new Texture("res/textures/dirt.png"), 10);
	
	private Texture texture;
	private int resistance;
	
	BlockType(Texture tex, int resistance) {
		this.texture = tex;
		this.resistance = resistance;
	}

	public Texture getTexture() {
		return texture;
	}

	public int getResistance() {
		return resistance;
	}
}
