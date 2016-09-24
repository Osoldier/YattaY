package me.oso.yattay.ui;

import me.oso.lib.graphics.Texture;
import me.oso.lib.math.Vector2f;

/**
 * Created by Thomas on 24 sept. 2016
 */
public abstract class Button extends UIComponent {
	
	private Texture std, hover, click;
	private 
	
	public Button(int x, int y, int width, int height, Texture std) {
		this(x, y, width, height, null, std, null, null);
	}

	public Button(int x, int y, int width, int height, String text, Texture std, Texture hover, Texture click) {
		this.setPosition(new Vector2f(x, y));
		this.setSize(new Vector2f(width, height));
		this.setText(text);
		this.setTexture(std);
		this.std = std;
		this.hover = hover;
		this.click = click;
	}

	@Override
	public void onMouseHover() {
		if(this.hover != null) {
			this.setTexture(hover);
		}
	}
	
	@Override
	public void onClick() {
		if(this.click != null) {
			this.setTexture(click);
		}
	}
	
	@Override
	public void onMouseExit() {		
		this.setTexture(std);
		
	}
}
