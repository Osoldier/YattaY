package me.oso.yattay.world;

/**
 * Block.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class Block {
	
	public static final int SIZE = 16;
	private BlockType type;
	private int damages;

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
