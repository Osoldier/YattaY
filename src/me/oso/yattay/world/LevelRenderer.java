package me.oso.yattay.world;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import me.it.lib.graphics.Camera2d;
import me.oso.lib.math.ProjectionMatrix;
import me.oso.lib.math.Vector3f;

/**
 * LevelRenderer.java
 * 
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class LevelRenderer {

	private BlockShader bShader;
	private ProjectionMatrix prMat;
	private final Vector3f SCALE_VEC = new Vector3f(Block.SIZE, Block.SIZE, 1);

	public LevelRenderer(ProjectionMatrix prMat) {
		bShader = new BlockShader();
		this.prMat = prMat;
	}

	public void renderLevel(Camera2d c, Level l) {
		bShader.start();
		bShader.setPrMat(prMat);
		bShader.setVwMat(c.getMatrix());
		bShader.loadFrameUniforms();
		glBindVertexArray(Block.model.getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(2);
		for (int i = 0; i < l.getLevel().length; i++) {
			for (int j = 0; j < l.getLevel()[i].length; j++) {
				if (l.getLevel()[i][j].getType().getTexture() != null) {
					l.getLevel()[i][j].getType().getTexture().bind();
					bShader.setTex(l.getLevel()[i][j].getType().getTexture());
					bShader.getMlMat().Transform(new Vector3f(i * Block.SIZE, j * Block.SIZE, 0), 0, 0, 0, SCALE_VEC);
					bShader.loadUniforms();
					glDrawArrays(GL_TRIANGLES, 0, 6);
				}
			}
		}
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
		bShader.stop();
	}

}
