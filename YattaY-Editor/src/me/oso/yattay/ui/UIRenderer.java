package me.oso.yattay.ui;

import java.util.ArrayList;
import java.util.List;

import me.oso.lib.math.ProjectionMatrix;

/**
 * Created by Thomas on 24 sept. 2016
 */
public class UIRenderer {

	private List<UIComponent> components;
	private ProjectionMatrix prMat;
	
	public UIRenderer(ProjectionMatrix pr) {
		this.components = new ArrayList<UIComponent>();
		this.prMat = pr;
	}
	
	public void render() {
		
	}

	public List<UIComponent> getComponents() {
		return components;
	}

	public void setComponents(List<UIComponent> components) {
		this.components = components;
	}
	
}
