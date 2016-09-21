package me.oso.yattay.world;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;

import me.oso.lib.graphics.*;
import me.oso.lib.math.ProjectionMatrix;
import me.oso.lib.math.Vector3f;
import me.oso.yattay.core.*;

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

	private void prepare(Camera2d c) {
		bShader.start();
		bShader.setPrMat(prMat);
		bShader.setVwMat(c.getMatrix());
		bShader.loadFrameUniforms();
		glBindVertexArray(Block.model.getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(2);
	}

	public void renderLevel(Camera2d c, Level l) {
		prepare(c);
		Vector3f position = new Vector3f();
		for (int i = 0; i < l.getLevel().length; i++) {
			for (int j = 0; j < l.getLevel()[i].length; j++) {
				int x = i * Block.SIZE;
				int y = j * Block.SIZE;
				position.x = x;
				position.y = y;
				if (l.getLevel()[i][j].getType().getTexture() != null && inView(c, x, y)) {
				}
			}
			glDrawArraysInstanced(GL_TRIANGLES, 0, 6, 10000);
		}
		
		clean();
	}
	
	private boolean inView(Camera2d c, int x, int y) {
		 return (x > c.getPosition().x-Block.SIZE/2 && x < c.getPosition().x + YattaY.getWindow().getPixWidth()+Block.SIZE/2) && (y > c.getPosition().y-Block.SIZE/2 && y < c.getPosition().y + YattaY.getWindow().getPixHeight()+Block.SIZE/2);
	}

	private void clean() {
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(2);
		glBindVertexArray(0);
		bShader.stop();
	}

}
