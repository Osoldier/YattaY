package me.oso.yattay.editor;


import me.oso.lib.graphics.*;
import me.oso.lib.math.ProjectionMatrix;
import me.oso.yattay.ui.Menu;
import me.oso.yattay.ui.UIRenderer;
import me.oso.yattay.world.*;

/**
 * Created by Thomas on 26 sept. 2016
 */
public class MasterRenderer {

	private UIRenderer uiRenderer;
	private LevelRenderer levelRenderer;
	private ProjectionMatrix pr;
	
	public MasterRenderer() {
		this.pr = new ProjectionMatrix(0, Editor.WIDTH, Editor.HEIGHT, 0, -1, 1);
		this.uiRenderer = new UIRenderer(pr);
		this.levelRenderer = new LevelRenderer(pr);
	}
	
	public void renderUI(Menu menu) {
		uiRenderer.render(menu.getComponents());
	}
	
	public void renderLevel(Camera2d c, Level l) {
		levelRenderer.renderLevel(c, l);
	}
	
}
