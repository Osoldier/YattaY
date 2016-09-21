package me.oso.yattay.core;

import me.oso.lib.graphics.*;
import me.oso.lib.math.ProjectionMatrix;
import me.oso.yattay.world.Level;
import me.oso.yattay.world.LevelRenderer;

/**
 * MasterRenderer.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class MasterRenderer {

	private ProjectionMatrix orthoMat;
	private LevelRenderer levelRenderer;
	
	public MasterRenderer(int width, int height) {
		orthoMat = new ProjectionMatrix(0, width, height, 0, -1, 1);
		this.levelRenderer = new LevelRenderer(orthoMat);
	}
	
	public void render(Camera2d c, Level l) {
		this.levelRenderer.renderLevel(c, l);
	}
}