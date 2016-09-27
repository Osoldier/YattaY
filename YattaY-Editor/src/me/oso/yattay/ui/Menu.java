package me.oso.yattay.ui;

import java.util.*;

import me.oso.lib.core.*;
import me.oso.yattay.editor.*;

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
		if (Editor.getWindow().getMouseX() > x - w / 2 && Editor.getWindow().getMouseX() < x + w / 2) {
			if (Editor.getWindow().getMouseY() > y - h / 2 && Editor.getWindow().getMouseY() < y + h / 2) {
				return true;
			}
		}
		return false;
	}

	public List<UIComponent> getComponents() {
		return components;
	}

}
