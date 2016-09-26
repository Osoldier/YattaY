package me.oso.yattay.ui;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import java.util.List;

import me.oso.lib.math.ProjectionMatrix;

/**
 * Created by Thomas on 24 sept. 2016
 */
public class UIRenderer {

	public static ProjectionMatrix prMat;
	
	public UIRenderer(ProjectionMatrix pr) {
		prMat = pr;
	}
	
	public void render(List<UIComponent> components) {
		for (UIComponent uiComponent : components) {
			uiComponent.getShader().start();
			uiComponent.getShader().loadFrameUniforms();
			uiComponent.getShader().loadUniforms();
			glBindVertexArray(uiComponent.getModel().getVaoID());
			glEnableVertexAttribArray(0);
			glEnableVertexAttribArray(2);
			glDrawArrays(GL_TRIANGLES, 0, uiComponent.getModel().getVertexCount());
			glDisableVertexAttribArray(2);
			glDisableVertexAttribArray(0);
		}
		glBindVertexArray(0);
	}
}
