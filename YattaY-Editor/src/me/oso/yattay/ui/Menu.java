package me.oso.yattay.ui;

import java.util.ArrayList;
import java.util.List;

import me.oso.lib.core.MouseHandler;
import me.oso.lib.core.Window;

/**
 * Created by Thomas on 26 sept. 2016
 */
public class Menu {

	private List<UIComponent> components;

	public Menu() {
		this.components = new ArrayList<UIComponent>();
	}

	public void update() {
		for (UIComponent uiComponent : components) {
			if (mouseIn(uiComponent.getPosition().x, uiComponent.getPosition().y, uiComponent.getSize().x, uiComponent.getSize().y)) {
				uiComponent.onMouseHover();
				if (MouseHandler.isButtonDown(0)) {
					uiComponent.onClick();
				}
			} else {
				uiComponent.onMouseOut();
			}
		}
	}

	private boolean mouseIn(float x, float y, float w, float h) {
		if (Window.getMouseX() > x - w / 2 && Window.getMouseX() < x + w / 2) {
			if (Window.getMouseY() > y - h / 2 && Window.getMouseY() < y + h / 2) {
				return true;
			}
		}
		return false;
	}

	public List<UIComponent> getComponents() {
		return components;
	}

}
