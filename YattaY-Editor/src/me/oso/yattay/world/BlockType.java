package me.oso.yattay.world;

import me.oso.lib.graphics.*;
import me.oso.yattay.editor.*;

/**
 * BlockType.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public enum BlockType {

	
	AIR(-1, 0, new Texture(Editor.TEX_PATH+"air.png")), STONE(1, 20, new Texture(Editor.TEX_PATH+"stone.png")), DIRT(2, 10, new Texture(Editor.TEX_PATH+"dirt.png")), GRASS(3, 10, new Texture(Editor.TEX_PATH+"grass.png")),
	WOOD(4, 15, new Texture(Editor.TEX_PATH+"wood.png")), BRICK(7, 30, new Texture(Editor.TEX_PATH+"brick.png"));
	
	private int texID;
	private int resistance;
	private Texture uiTexture;
	
	BlockType(int texID, int resistance, Texture UITex) {
		this.texID = texID;
		this.resistance = resistance;
		this.uiTexture = UITex;
	}

	public int getTexture() {
		return texID;
	}

	public int getResistance() {
		return resistance;
	}

	public Texture getUiTexture() {
		return uiTexture;
	}
}
