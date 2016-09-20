package me.oso.yattay.world;

/**
 * Level.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class Level {

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
	
	public void generate() {
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				if(j > level[i].length/10) {
					level[i][j] = new Block(BlockType.DIRT);
				} else {
					level[i][j] = new Block(BlockType.AIR);
				}
			}
		}
		System.out.println("Generated "+level.length+" x "+level[0].length+" level"); 
	}
}
