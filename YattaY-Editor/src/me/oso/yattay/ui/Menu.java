package me.oso.yattay.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 26 sept. 2016
 */
public class Menu {
	
	private List<UIComponent> components;
	
	public Menu() {
		this.components = new ArrayList<UIComponent>();
	}

	public List<UIComponent> getComponents() {
		return components;
	}

}
