package me.oso.yattay.world;

/**
 * BlockType.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public enum BlockType {

	
	AIR(-1, 0), STONE(1, 20), DIRT(2, 10), GRASS(3, 10),
	WOOD(4, 15), BRICK(7, 30);
	
	private int texID;
	private int resistance;
	
	BlockType(int texID, int resistance) {
		this.texID = texID;
		this.resistance = resistance;
	}

	public int getTexture() {
		return texID;
	}

	public int getResistance() {
		return resistance;
	}
}
