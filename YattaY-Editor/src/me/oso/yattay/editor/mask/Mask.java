package me.oso.yattay.editor.mask;

import me.oso.yattay.world.BlockType;

/**
 * Mask.java
 * @author Ibanez Thomas
 * @date 27 sept. 2016
 */
public class Mask {

	private int size;
	private BlockType bt;
	private int x, y;
	
	public Mask() {
		this.size = 1;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public BlockType getBt() {
		return bt;
	}

	public void setBt(BlockType bt) {
		this.bt = bt;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
