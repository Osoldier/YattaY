package me.oso.yattay.editor.mask;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import me.oso.lib.graphics.*;
import me.oso.lib.math.*;
import me.oso.yattay.world.*;

/**
 * MaskRenderer.java
 * @author Ibanez Thomas
 * @date 27 sept. 2016
 */
public class MaskRenderer {

	private MaskShader mShader;
	private Model model;
	
	private static final float[] vertices = {
			-0.5f, -0.5f,
			0.5f, -0.5f,
			0.5f, 0.5f, 
			
			0.5f, 0.5f,
			-0.5f, 0.5f,
			-0.5f, -0.5f 
	};
	
	private static final float[] texCoords = {
			0, 0,
			1, 0, 
			1, 1,
			
			1, 1, 
			0, 1,
			0, 0
	};
	
	public MaskRenderer(ProjectionMatrix pr) {	
		this.mShader = new MaskShader(pr);
		this.model = Loader.createGUIVAO(vertices, texCoords);
	}
	
	public void renderMask(Mask m) {
		
		mShader.setBlockSize(Block.SIZE);
		mShader.setSize(m.getSize());
		mShader.getPosition().x = m.getRenderPosition().x;
		mShader.getPosition().y = m.getRenderPosition().y;
		
		mShader.start();
		m.getBt().getUiTexture().bind();
		mShader.loadFrameUniforms();
		mShader.loadUniforms();
		
		glBindVertexArray(model.getVaoID());
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(2);
		glDrawArrays(GL_TRIANGLES, 0, model.getVertexCount());
		glDisableVertexAttribArray(2);
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
	
}
