package me.oso.yattay.world;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL33.*;

import java.util.*;

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
	private List<Float> positions;
	private int positionVBO;
	private List<Float> textures;
	private int texVBO;
	private static Texture atlas;
	private static final float ATLAS_SIZE = 16.0f;

	public LevelRenderer(ProjectionMatrix prMat) {
		bShader = new BlockShader(Block.SIZE, ATLAS_SIZE);
		this.prMat = prMat;
		this.positions = new LinkedList<Float>();
		positionVBO = glGenBuffers();
		this.textures = new LinkedList<Float>();
		texVBO = glGenBuffers();
		atlas = new Texture("res/textures/blocks.png");
	}

	private void prepare(Camera2d c) {
		bShader.start();
		bShader.setPrMat(prMat);
		bShader.setVwMat(c.getMatrix());
		bShader.loadFrameUniforms();
		atlas.bind();
		positions.clear();
		textures.clear();
		glBindVertexArray(Block.model.getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(2);
		glEnableVertexAttribArray(3);
		glEnableVertexAttribArray(4);
	}

	public void renderLevel(Camera2d c, Level l) {
		prepare(c);
		Vector3f position = new Vector3f();
		int cnt = 0;
		for (int i = 0; i < l.getLevel().length; i++) {
			for (int j = 0; j < l.getLevel()[i].length; j++) {
				int x = i * Block.SIZE;
				int y = j * Block.SIZE;
				position.x = x;
				position.y = y;
				if (l.getLevel()[i][j].getType().getTexture() >= 0 && inView(c, x, y)) {
					cnt++;
					positions.add(new Float(x));
					positions.add(new Float(y));
					textures.add((l.getLevel()[i][j].getType().getTexture() % ATLAS_SIZE) / ATLAS_SIZE);
					textures.add((float) Math.floor(l.getLevel()[i][j].getType().getTexture() / ATLAS_SIZE) / ATLAS_SIZE);
				}
			}
		}
		
		glBindBuffer(GL_ARRAY_BUFFER, positionVBO);
		glBufferData(GL_ARRAY_BUFFER, VBOUtil.createFloatBuffer(VBOUtil.FloatListToArray(positions)), GL_DYNAMIC_DRAW);
		glVertexAttribPointer(3, 2, GL_FLOAT, false, 0, 0);
		glVertexAttribDivisor(3, 1);
		
		glBindBuffer(GL_ARRAY_BUFFER, texVBO);
		glBufferData(GL_ARRAY_BUFFER, VBOUtil.createFloatBuffer(VBOUtil.FloatListToArray(textures)), GL_DYNAMIC_DRAW);
		glVertexAttribPointer(4, 2, GL_FLOAT, false, 0, 0);
		glVertexAttribDivisor(4, 1);
		
		glDrawArraysInstanced(GL_TRIANGLES, 0, 6, cnt);
		clean();
	}
	
	private boolean inView(Camera2d c, int x, int y) {
		 return (x > c.getPosition().x-Block.SIZE/2 && x < c.getPosition().x + YattaY.getWindow().getPixWidth()+Block.SIZE/2) && (y > c.getPosition().y-Block.SIZE/2 && y < c.getPosition().y + YattaY.getWindow().getPixHeight()+Block.SIZE/2);
	}

	private void clean() {
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(2);
		glDisableVertexAttribArray(3);
		glDisableVertexAttribArray(4);
		glBindVertexArray(0);
		bShader.stop();
	}

}
