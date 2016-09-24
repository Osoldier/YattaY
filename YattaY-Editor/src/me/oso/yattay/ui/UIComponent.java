package me.oso.yattay.ui;

import me.oso.lib.graphics.Model;
import me.oso.lib.graphics.Texture;
import me.oso.lib.math.Vector2f;

/**
 * Created by Thomas on 24 sept. 2016
 */
public abstract class UIComponent {
	
	private Texture texture;
	private String text;
	private Vector2f position;
	private Vector2f size;
	private Model model;
	
	public abstract void onMouseHover();
	public abstract void onClick();
	public abstract void onMouseExit();
	
	
	protected Vector2f getPosition() {
		return position;
	}
	protected void setPosition(Vector2f position) {
		this.position = position;
	}
	protected Vector2f getSize() {
		return size;
	}
	protected void setSize(Vector2f size) {
		this.size = size;
	}
	protected Texture getTexture() {
		return texture;
	}
	protected void setTexture(Texture texture) {
		this.texture = texture;
	}
	protected String getText() {
		return text;
	}
	protected void setText(String text) {
		this.text = text;
	}
	public Model getModel() {
		return model;
	}
}
