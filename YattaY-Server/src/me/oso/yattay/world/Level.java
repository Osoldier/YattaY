package me.oso.yattay.world;

/**
 * Level.java
 * 
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class Level {

	private int blueX, blueY, redX, redY;
	
	public Block[][] getLevel() {
		return level;
	}

	public void setLevel(Block[][] level) {
		this.level = level;
	}

	private Block[][] level;

	public Level(int width, int height) {
		this.level = new Block[width][height];
	}
	
	public Level(Level l) {
		this(l.level.length, l.level[0].length);
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[0].length; j++) {
				level[i][j] = new Block(l.level[i][j].getType());
			}
		}
	}

	public void generate() {
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				level[i][j] = new Block(BlockType.AIR);
			}
		}
		System.out.println("Generated " + level.length + " x " + level[0].length + " level");
	}

	public int getBlueX() {
		return blueX;
	}

	public void setBlueX(int blueX) {
		this.blueX = blueX;
	}

	public int getBlueY() {
		return blueY;
	}

	public void setBlueY(int blueY) {
		this.blueY = blueY;
	}

	public int getRedX() {
		return redX;
	}

	public void setRedX(int redX) {
		this.redX = redX;
	}

	public int getRedY() {
		return redY;
	}

	public void setRedY(int redY) {
		this.redY = redY;
	}
}
