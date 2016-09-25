package me.oso.yattay.ui;

import java.util.ArrayList;
import java.util.List;

import me.oso.lib.math.ProjectionMatrix;
import me.oso.yattay.ui.shaders.*;

/**
 * Created by Thomas on 24 sept. 2016
 */
public class UIRenderer {

	private List<UIComponent> components;
	public static ProjectionMatrix prMat;
	
	public UIRenderer(ProjectionMatrix pr) {
		this.components = new ArrayList<UIComponent>();
		prMat = pr;
	}
	
	public void render() {
		for (UIComponent uiComponent : components) {
			uiComponent.bindShader();
		}
	}

	public List<UIComponent> getComponents() {
		return components;
	}

	public void setComponents(List<UIComponent> components) {
		this.components = components;
	}
	
}
