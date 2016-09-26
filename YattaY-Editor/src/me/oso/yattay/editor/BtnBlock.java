package me.oso.yattay.editor;

import me.oso.lib.graphics.Texture;
import me.oso.yattay.ui.Button;

/**
 * Created by Thomas on 26 sept. 2016
 */
public class BtnBlock extends Button {

	public BtnBlock(int x, int y, int width, int height, Texture std) {
		super(x, y, width, height, std);
	}
	
	@Override
	public void onClick() {
		super.onClick();
		System.out.println("llol");
	}
	
	@Override
	public void onMouseHover() {
		super.onMouseHover();
	}
	
}
